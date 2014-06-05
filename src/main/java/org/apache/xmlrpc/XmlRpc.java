package org.apache.xmlrpc;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.TimeZone;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.AttributeList;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * This abstract base class provides basic capabilities for XML-RPC, 
 * like parsing of parameters or encoding Java objects into XML-RPC format. 
 * XmlRpcServer and XmlRpcClient are the classes that actually implement an XML-RCP server and client.
 * 
 * @see XmlRpcServer
 * @see XmlRpcClient
 *
 * @author <a href="mailto:hannes@apache.org">Hannes Wallnoefer</a>
 * @author <a href="mailto:dlr@finemaltcoding.com">Daniel Rall</a>
 */
public abstract class XmlRpc extends HandlerBase
{
    /**
     * The version string used in HTTP communication.
     */
    public static final String version = "Apache XML-RPC 1.0";
        
    private static final SAXParserFactory spf;
    
    static {
    	spf = SAXParserFactory.newInstance();    	
    }

   
    /**
     * The maximum number of threads which can be used concurrently.
     */
    private static int maxThreads = 100;
    
    
    
    
    protected final static Logger logger = Logger.getLogger("XmlRpc.class");

    String methodName;

    // the stack we're parsing our values into.
    Stack values;
    Value currentValue;
    
    /**
     * Thread-safe wrapper for the <code>DateFormat</code> object used
     * to format and parse date/time values.
     */
    static Formatter dateformat = new Formatter ();

    /**
     * Used to collect character data (<code>CDATA</code>) of
     * parameter values.
     */
    StringBuffer cdata;
    boolean readCdata;

    // XML RPC parameter types used for dataMode
    public static final int STRING = 0;
    public static final int INTEGER = 1;
    public static final int BOOLEAN = 2;
    public static final int DOUBLE = 3;
    public static final int DATE = 4;
    public static final int BASE64 = 5;
    public static final int STRUCT = 6;
    public static final int ARRAY = 7;
    public static final int NIL = 8;
    
    // Error level + message
    int errorLevel;
    String errorMsg;

    static final int NONE = 0;
    static final int RECOVERABLE = 1;
    static final int FATAL = 2;

    
    static boolean keepalive = false;
    
    static String encoding = "UTF-8";

    /**
     * The list of valid XML elements used for RPC.
     */
    final static String types[] =
    {
        "String",
        "Integer",
        "Boolean",
        "Double",
        "Date",
        "Base64",
        "Struct",
        "Array",
        "Nil"
    };
   
    /**
     *  This method is called when a root level object has been parsed.
     */
   abstract void objectParsed (Object what);
   
   
    /**
     * Gets the maximum number of threads used at any given moment.
     */
    public static int getMaxThreads()
    {
        return maxThreads;
    }

    /**
     * Sets the maximum number of threads used at any given moment.
     */
    public static void setMaxThreads(int maxThreads)
    {
        XmlRpc.maxThreads = maxThreads;
    }
    
    /**
      * Switch HTTP keepalive on/off.
      */
    public static void setKeepAlive (boolean val)
    {
        keepalive = val;
    }

    /**
      * get current HTTP keepalive mode.
      */
    public static boolean getKeepAlive ()
    {
        return keepalive;
    }


    /**
      * Parse the input stream. For each root level object, method
      * <code>objectParsed</code> is called.
      */
    @SuppressWarnings("deprecation")
	synchronized void parse (InputStream is)  throws Exception
        {
        // reset values (XmlRpc objects are reusable)
        errorLevel = NONE;
        errorMsg = null;      
        cdata = null;
        values = null;
        currentValue = null;

        readCdata = false;
        cdata = new StringBuffer(128);
        values = new Stack ();
       
        SAXParser parser = spf.newSAXParser();          
        parser.getParser().setDocumentHandler (this);
        parser.getParser().setErrorHandler (this);
        parser.getParser().parse (new InputSource (new InputStreamReader(is,"UTF-8")));        
                
    }

   

    ////////////////////////////////////////////////////////////////
    // methods called by XML parser

    /**
     * Method called by SAX driver.
     */
    public void characters (char ch[], int start, int length)
        throws SAXException
    {
        if (readCdata)
        {
            cdata.append (ch, start, length);
        }
    }
    
    @Override
    public void endElement(String name)	throws SAXException {
    
        // finalize character data, if appropriate
        if (currentValue != null && readCdata)
        {
            currentValue.characterData (cdata.toString ());
            cdata.setLength (0);
            readCdata = false;
        }

        if ("value".equals (name))
        {
            // Only handle top level objects or objects contained in
            // arrays here.  For objects contained in structs, wait
            // for </member> (see code below).
            int depth = values.size ();
            if (depth < 2 || values.elementAt(depth - 2).hashCode() != STRUCT)
            {
                Value v = currentValue;
                values.pop ();
                if (depth < 2)
                {
                    // This is a top-level object
                    objectParsed (v.value);
                    currentValue = null;
                }
                else
                {
                    // Add object to sub-array; if current container
                    // is a struct, add later (at </member>).
                    currentValue = (Value) values.peek ();
                    currentValue.endElement (v);
                }
            }
        }

        // Handle objects contained in structs.
        if ("member".equals (name))
        {
            Value v = currentValue;
            values.pop ();
            currentValue = (Value) values.peek ();
            currentValue.endElement (v);
        }

        else if ("methodName".equals (name))
        {
            methodName = cdata.toString ();
            cdata.setLength (0);
            readCdata = false;
        }
    }

    @Override
    public void startElement(String name, AttributeList attributes) throws SAXException {
    	    
    
        if ("value".equals (name))
        {
            // System.err.println ("starting value");
            Value v = new Value ();
            values.push (v);
            currentValue = v;
            // cdata object is reused
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("methodName".equals (name))
        {
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("name".equals (name))
        {
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("string".equals (name))
        {
            // currentValue.setType (STRING);
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("i4".equals (name) || "int".equals (name))
        {
            currentValue.setType (INTEGER);
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("boolean".equals (name))
        {
            currentValue.setType (BOOLEAN);
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("double".equals (name))
        {
            currentValue.setType (DOUBLE);
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("dateTime.iso8601".equals (name))
        {
            currentValue.setType (DATE);
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("base64".equals (name))
        {
            currentValue.setType (BASE64);
            cdata.setLength(0);
            readCdata = true;
        }
        else if ("struct".equals (name))
        {
            currentValue.setType (STRUCT);
        }
        else if ("array".equals (name))
        {
            currentValue.setType (ARRAY);
        }
        else if ("nil".equals (name))
        {
            currentValue.setType (NIL);
        }
       
        
        
        
    }

    public void error (SAXParseException e) throws SAXException
    {
        logger.error("Error parsing XML: "+e);
        errorLevel = RECOVERABLE;
        errorMsg = e.toString ();
    }

    public void fatalError(SAXParseException e) throws SAXException
    {
        logger.error ("Fatal error parsing XML: "+e);
        errorLevel = FATAL;
        errorMsg = e.toString ();
    }

    /**
     * This represents a XML-RPC value parsed from the request.
     */
    class Value
    {
        int type;
        Object value;
        // the name to use for the next member of struct values
        String nextMemberName;

        Hashtable struct;
        Vector array;

        /**
         * Constructor.
         */
        public Value ()
        {
            this.type = STRING;
        }

        /**
          * Notification that a new child element has been parsed.
          */
        public void endElement (Value child)
        {
            switch (type)
            {
            case ARRAY:
                array.addElement (child.value);
                break;
            case STRUCT:
                struct.put (nextMemberName, child.value);
            }
        }

        /**
         * Set the type of this value. If it's a container, create the
         * corresponding java container.
         */
        public void setType (int type)
        {
            // System.err.println ("setting type to "+types[type]);
            this.type = type;
            switch (type)
            {
            case ARRAY:
                value = array = new Vector ();
                break;
            case STRUCT:
                value = struct = new Hashtable ();
                break;
            }
        }

        /**
         * Set the character data for the element and interpret it
         * according to the element type.
         */
        public void characterData (String cdata)
        {
            switch (type)
            {
                case NIL:
                    value = null;
                    break;
                case INTEGER:
                    value = new Integer (cdata.trim ());
                    break;
                case BOOLEAN:
                    value = ("1".equals (cdata.trim ()) ?
                             Boolean.TRUE : Boolean.FALSE);
                    break;
                case DOUBLE:
                    value = new Double (cdata.trim ());
                    break;
                case DATE:
                    try
                    {
                        value = dateformat.parse (cdata.trim ());
                    }
                    catch (ParseException p)
                    {
                        throw new RuntimeException (p.getMessage ());
                    }
                    break;
                case BASE64:
                    value = Base64.decode (cdata.getBytes());
                    break;
                case STRING:
                    value = cdata;
                    break;
                case STRUCT:
                    // this is the name to use for the next member of this struct
                    nextMemberName = cdata;
                    break;
            }
        }

        /**
         * This is a performance hack to get the type of a value
         * without casting the Object.  It breaks the contract of
         * method hashCode, but it doesn't matter since Value objects
         * are never used as keys in Hashtables.
         */
        public int hashCode ()
        {
            return type;
        }

        public String toString ()
        {
            return (types[type] + " element " + value);
        }
    }


    /**
     * A quick and dirty XML writer.  If you feed it a
     * <code>ByteArrayInputStream</code>, it may be necessary to call
     * <code>writer.flush()</code> before calling
     * <code>buffer.toByteArray()</code> to get the data written to
     * your byte buffer.
     */
    class XmlWriter
        extends OutputStreamWriter
    {
        protected static final String PROLOG_START =
            "<?xml version=\"1.0\" encoding=\"";
        protected static final String PROLOG_END = "\"?>";
        protected static final String CLOSING_TAG_START = "</";
        protected static final String SINGLE_TAG_END = "/>";
        protected static final String LESS_THAN_ENTITY = "&lt;";
        protected static final String GREATER_THAN_ENTITY = "&gt;";
        protected static final String AMPERSAND_ENTITY = "&amp;";

        public XmlWriter (OutputStream out) throws UnsupportedEncodingException, IOException
        {
            // The default encoding used for XML-RPC is ISO-8859-1.
            this (out, encoding);
        }

        public XmlWriter (OutputStream out, String enc) throws UnsupportedEncodingException, IOException
        {
            super(out, enc);

            // Add the XML prolog (which includes the encoding)
            write(PROLOG_START);
            write(encoding);
            write(PROLOG_END);
        }

        /**
         * Writes the XML representation of a supported Java object
         * type.
         *
         * @param obj The <code>Object</code> to write.
         */
        public void writeObject (Object obj)
            throws IOException
        {
            startElement ("value");
            if (obj == null)
            {
                emptyElement("nil");
            }
            else if (obj instanceof String)
            {
                chardata(obj.toString());
            }
            else if (obj instanceof Integer)
            {
                startElement("int");
                write(obj.toString());
                endElement("int");
            }
            else if (obj instanceof Boolean)
            {
                startElement("boolean");
                write(((Boolean) obj).booleanValue() ? "1" : "0");
                endElement("boolean");
            }
            else if (obj instanceof Double || obj instanceof Float)
            {
                startElement("double");
                write(obj.toString());
                endElement("double");
            }
            else if (obj instanceof Date)
            {
                startElement("dateTime.iso8601");
                Date d = (Date) obj;
                write(dateformat.format(d));
                endElement("dateTime.iso8601");
            }
            else if (obj instanceof byte[])
            {
                startElement("base64");
                // FIXME: Yucky! Find a better way!
                write(new String(Base64.encode((byte[]) obj)).toCharArray());
                endElement("base64");
            }
            else if (obj instanceof Vector)
            {
                startElement("array");
                startElement("data");
                Vector array = (Vector) obj;
                int size = array.size();
                for (int i = 0; i < size; i++)
                {
                    writeObject(array.elementAt(i));
                }
                endElement("data");
                endElement("array");
            }
            else if (obj instanceof Hashtable)
            {
                startElement("struct");
                Hashtable struct = (Hashtable) obj;
                for (Enumeration e = struct.keys(); e.hasMoreElements(); )
                {
                    String nextkey = (String) e.nextElement();
                    Object nextval = struct.get(nextkey);
                    startElement("member");
                    startElement("name");
                    write(nextkey);
                    endElement("name");
                    writeObject(nextval);
                    endElement("member");
                }
                endElement("struct");
            }
            else
            {
                throw new RuntimeException("unsupported Java type: " +
                                            obj.getClass());
            }
            endElement("value");
        }

        protected void startElement (String elem)
            throws IOException
        {
            write('<');
            write(elem);
            write('>');
        }

        protected void endElement (String elem)
            throws IOException
        {
            write(CLOSING_TAG_START);
            write(elem);
            write('>');
        }

        protected void emptyElement (String elem)
            throws IOException
        {
            write('<');
            write(elem);
            write(SINGLE_TAG_END);
        }

        protected void chardata (String text)
            throws IOException
        {
            int l = text.length ();
            for (int i = 0; i < l; i++)
            {
                char c = text.charAt (i);
                switch (c)
                {
                case '<':
                    write(LESS_THAN_ENTITY);
                    break;
                case '>':
                    write(GREATER_THAN_ENTITY);
                    break;
                case '&':
                    write(AMPERSAND_ENTITY);
                    break;
                default:
                    write(c);
                }
            }
        }
    }
}


/**
 * Wraps a <code>DateFormat</code> instance to provide thread safety.
 */
class Formatter
{
    private DateFormat f;

    /**
     * Uses the <code>DateFormat</code> string
     * <code>yyyyMMdd'T'HH:mm:ss</code>.
     */
    public Formatter ()
    {
        f = new SimpleDateFormat ("yyyyMMdd'T'HH:mm:ss");
        // Eingesetzt da ansonsten die Zeitzone der Virtuellen Maschine benutzt wird
        // wir bekommen hier aber einen GMT+1 Wert !
        f.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        
    }

    public synchronized String format (Date d)
    {
        return f.format (d);
    }

    public synchronized Date parse (String s)
        throws ParseException
    {
        return f.parse (s);
    }
}
