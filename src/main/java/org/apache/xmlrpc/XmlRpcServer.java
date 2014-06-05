package org.apache.xmlrpc;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * A multithreaded, reusable XML-RPC server object. The name may be misleading 
 * because this does not open any server sockets. Instead it is fed by passing 
 * an XML-RPC input stream to the execute method. If you want to open a 
 * HTTP listener, use the WebServer class instead.
 *
 * @author <a href="mailto:hannes@apache.org">Hannes Wallnoefer</a>
 * @author <a href="mailto:dlr@finemaltcoding.com">Daniel Rall</a>
 */
public class XmlRpcServer
{
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private Hashtable handlers;
    private Stack pool;
    private int workers;
    
    
    protected final static Logger logger = Logger.getLogger("XmlRpcServer.class");

    /**
     * Construct a new XML-RPC server. You have to register handlers
     * to make it do something useful.
     */
    public XmlRpcServer()
    {
        handlers = new Hashtable();
        pool = new Stack();
        workers = 0;
    }

    /**
     * Register a handler object with this name. Methods of this
     * objects will be callable over XML-RPC as
     * "handlername.methodname". For more information about XML-RPC
     * handlers see the <a href="../index.html#1a">main documentation
     * page</a>.
     *
     * @param handlername The name to identify the handler by.
     * @param handler The handler itself.
     */
    public void addHandler(String handlername, Object handler)
    {        
    	logger.info("Registered " + handlername + " for " + handler.getClass());
        if (handler instanceof XmlRpcHandler)
        {
            handlers.put(handlername, handler);
        }
        else if (handler != null)
        {
            handlers.put(handlername, new Invoker(handler));
        }
    }
        
    public Object getHandler(String handlerName){
    	return handlers.get(handlerName);
    }

    /**
     * Remove a handler object that was previously registered with
     * this server.
     *
     * @param handlername The name identifying the handler to remove.
     */
    public void removeHandler(String handlername)
    {       
        handlers.remove(handlername);
    }

    /**
     * Parse the request and execute the handler method, if one is
     * found. Returns the result as XML.  The calling Java code
     * doesn't need to know whether the call was successful or not
     * since this is all packed into the response.
     */
    public byte[] execute(InputStream is)
    {       
        return execute(is, null, null);
    }

    /**
     * Parse the request and execute the handler method, if one is
     * found. If the invoked handler is SessionXmlRpcHandler,
     * use the credentials to authenticate the user.
     */
    public byte[] execute(InputStream is,Integer sessionId, Integer userId)
    {       
        Worker worker = getWorker();
        byte[] retval = worker.execute(is,sessionId,userId);
        pool.push(worker);
        return retval;
    }

    private final Worker getWorker()
    {
        try
        {
            return(Worker) pool.pop();
        }
        catch(EmptyStackException x)
        {
            int maxThreads = XmlRpc.getMaxThreads();
            if (workers < maxThreads)
            {
                workers += 1;
                if (workers >= maxThreads * .95)
                {
                    logger.warn("95% of XML-RPC server threads in use");
                }
                return new Worker();
            }
            throw new RuntimeException("System overload");
        }
    }

    /**
     * Performs streaming, parsing, and handler execution.
     * Implementation is not thread-safe.
     */
    class Worker extends XmlRpc
    {
        private Vector inParams;
        private ByteArrayOutputStream buffer;
        private XmlWriter writer;

        /**
         * Creates a new instance.
         */
        protected Worker()
        {
            inParams = new Vector();
            buffer = new ByteArrayOutputStream();
        }

        /**
         * Given a request for the server, generates a response.
         */
        public byte[] execute(InputStream is,Integer sessionId,Integer userId)
        {
            try
            {
                // Do the work
                return executeInternal(is,sessionId,userId);
            }
            finally
            {
                // Release most of our resources
                buffer.reset();
                inParams.removeAllElements();
            }
        }

        private byte[] executeInternal(InputStream is,Integer sessionId,Integer userId)
        {
            byte[] result;                
            try
            {
                parse(is);
                if (logger.isDebugEnabled()) {
                	logger.debug("method name: " + methodName);
                	logger.debug("inparams: " + inParams);
                }
                // check for errors from the XML parser
                if (errorLevel > NONE)
                {
                    throw new Exception(errorMsg);
                }
                Object handler = null;

                String handlerName = null;
                int dot = methodName.lastIndexOf('.');
                if (dot > -1)
                {
                    handlerName = methodName.substring(0, dot);
                    handler = handlers.get(handlerName);
                    if (handler != null)
                    {
                        methodName = methodName.substring(dot + 1);
                    }
                }

                if (handler == null)
                {
                    if (dot > -1)
                    {
                        throw new Exception("RPC handler object \""+
                                handlerName + "\" not found and no default handler registered.");
                    }
                    else
                    {
                        throw new Exception("RPC handler object not found for \""+
                                methodName + "\": no default handler registered.");
                    }
                }

                
                Object i = ((Invoker)handler).getInvokeTarget();                
                if (i instanceof SessionHandler)
                {
                    if (sessionId == null || userId == null) {
                        throw new IllegalArgumentException("Session_Id or User_Id in header is null");
                    }                    
                    ((SessionHandler)i).checkSession(sessionId, userId);                    
                
                }
                if (logger.isDebugEnabled()){
                	logger.debug("Calling method:" + methodName + ":" + inParams);
                }
                Object outParam =((XmlRpcHandler) handler).execute(methodName, inParams);
                
                
                               
                writer = new XmlWriter(buffer);
                writeResponse(outParam, writer);
                writer.flush();
                result = buffer.toByteArray();
            }
            catch(Exception x)
            {
                logger.error(x.getMessage());
                // Ensure that if there is anything in the buffer, it
                // is cleared before continuing with the writing of exceptions.
                // It is possible that something is in the buffer
                // if there were an exception during the writeResponse()
                // call above.
                buffer.reset();

                writer = null;
                try
                {
                    writer = new XmlWriter(buffer);
                }
                catch(UnsupportedEncodingException encx)
                {
                    logger.error("XmlRpcServer attempted to use " + "unsupported encoding: " + encx);
                    // NOTE: If we weren't already using the default
                    // encoding, we could try it here.
                }
                catch(IOException iox)
                {
                    logger.error("XmlRpcServer experienced I/O error " +
                                       "writing error response: " + iox);
                }
                catch(IllegalArgumentException iax)
                {
                    logger.error("Invalid Header Information " +
                                       "writing error response: " + iax);
                }
                
                
                

                String message = x.toString();
                // Retrieve XmlRpcException error code(if possible).
                int code = x instanceof XmlRpcException ?
                       ((XmlRpcException) x).code : 0;
                try
                {
                    writeError(code, message, writer);
                    writer.flush();
                }
                catch(Exception e)
                {
                    // Unlikely to occur, as we just sent a struct
                    // with an int and a string.
                    logger.error("Unable to send error response to " +
                                       "client: " + e);
                }

                // If we were able to create a XmlWriter, we should
                // have a response.
                if (writer != null)
                {
                    result = buffer.toByteArray();
                }
                else
                {
                    result = EMPTY_BYTE_ARRAY;
                }
            }
            finally
            {
                if (writer != null)
                {
                    try
                    {
                        writer.close();
                    }
                    catch(IOException iox)
                    {
                        // This is non-fatal, but worth logging a
                        // warning for.
                        logger.error("Exception closing output stream: " + iox.getMessage());   
                    }
                }
            }
            
            return result;
        }

        /**
         * Called when an object to be added to the argument list has
         * been parsed.
         */
        void objectParsed(Object what)
        {            
            inParams.addElement(what);
        }

        /**
          * Writes an XML-RPC response to the XML writer.
          */
        void writeResponse(Object param, XmlWriter writer)
            throws XmlRpcException, IOException
        {
            
            writer.startElement("methodResponse");
            // if (param == null) param = ""; // workaround for Frontier bug
            writer.startElement("params");
            writer.startElement("param");
            writer.writeObject(param);
            writer.endElement("param");
            writer.endElement("params");
            writer.endElement("methodResponse");
        }

        /**
         * Writes an XML-RPC error response to the XML writer.
         */
        void writeError(int code, String message, XmlWriter writer)
            throws XmlRpcException, IOException
        {
            logger.error(message + " code:" + code);
            Hashtable h = new Hashtable();
            h.put("faultCode", new Integer(code));
            h.put("faultString", message);
            writer.startElement("methodResponse");
            writer.startElement("fault");
            writer.writeObject(h);
            writer.endElement("fault");
            writer.endElement("methodResponse");
        }

    } // end of inner class Worker

} // XmlRpcServer



