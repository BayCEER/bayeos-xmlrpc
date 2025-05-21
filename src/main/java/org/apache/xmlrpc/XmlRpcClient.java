package org.apache.xmlrpc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;


/**
 * A multithreaded, reusable XML-RPC client object. Use this if you need a full-grown
 * HTTP client(e.g. for Proxy and Cookies support). If you don't need that, <code>XmlRpcClientLite</code>
 * may work better for you.
 * 
 * @author <a href="mailto:hannes@apache.org">Hannes Wallnoefer</a>
 */
@SuppressWarnings("deprecation")
public class XmlRpcClient  implements XmlRpcHandler
{
    protected URL url;    
    private String Login;
    private String auth;
    private String dbVersion ="";
    
    public String getDbVersion() {
		return dbVersion;
	}



	// pool of worker instances
    protected Stack pool = new Stack();
    protected int workers = 0;
    protected int asyncWorkers = 0;
    
    protected final static Logger logger = LoggerFactory.getLogger("XmlRpcClient.class");
	
    public static final int CONNECT_TIMEOUT = 60*1000;
	public static final int READ_TIMEOUT = 60*1000;

    
        
    /**
     * Construct a XML-RPC client with this URL.
     */
    public XmlRpcClient(URL url)
    {
        this.url = url;
    }
    
    
    public void connect(String userName, String passWord) throws XmlRpcException {		
	    logger.info("Connect to " + url);
	    	    	          
        Vector params = new Vector();
        params.addElement(userName);
        params.addElement(passWord);	        	        
        Vector out = ((Vector)execute("LoginHandler.createSession", params)); 
        assert out!=null; 
        setLogin(userName);
        setAuthentication((Integer)out.elementAt(0), (Integer)out.elementAt(1));
        
        try {
        	dbVersion = (String)execute("LookUpTableHandler.getVersion", new Vector()) ;
        	logger.info("Connected to BayEOS server version:" + dbVersion);
        } catch(XmlRpcException e){
        	logger.warn("Database version information not found.");
        }
        			
		                              			
		
       
   }
        
    
    public Boolean close() throws XmlRpcException {
    		Boolean ret = ((Boolean)execute("LogOffHandler.terminateSession",new Vector()));    		
    		return ret;
    }
    
        
    /**
      * Construct a XML-RPC client for the URL represented by this String.
      */
    public XmlRpcClient(String url) throws MalformedURLException
    {
        this.url = new URL(url);
    }

    /**
      * Construct a XML-RPC client for the specified hostname and port.
      */
    public XmlRpcClient(String hostname,int port) throws MalformedURLException
    {
        this.url = new URL("http://" + hostname + ':' + port + "/RPC2");        
    }

    /**
      * Return the URL for this XML-RPC client.
      */
    public URL getURL()
    {
        return url;
    }
    
    public void setLogin(String Login){
        this.Login = Login;
    }
    
    public String getLogin(){
        return Login;
    }
    
    
       
   
    public void setAuthentication(int sessionId, int userId){
    	auth = new String( Base64.encode( (String.valueOf(sessionId)  + ':' + String.valueOf(userId) ).getBytes() )).trim();
    }
    
    
                    
    public Object execute(byte[] request) throws XmlRpcException, IOException {
    	if (logger.isDebugEnabled()) logger.debug("execute()");
    		Worker worker = getWorker(false);
    			try
    			{
    				return worker.execute(request);
    			}
    			finally
    			{
    				releaseWorker(worker, false);
    			}
    
    }


    public Object execute(String method, Vector params) throws XmlRpcException
    {
        try {
        if (logger.isDebugEnabled()) logger.debug("execute method:" + method);
        	Worker worker = getWorker(false);
        try
        {
            return worker.execute(method, params);
        }
        finally
        {
            releaseWorker(worker, false);
        }
        } catch (IOException e){
        	throw new XmlRpcException(0, e.getMessage());
        }
    }
    
    public InputStream executeStream(String method,Vector params) throws XmlRpcException {
    	try {
        if (logger.isDebugEnabled()) logger.debug("execute method:" + method);
        	Worker worker = getWorker(false);
        try
        {
            return worker.executeStream(method, params);
        }
        finally
        {
            releaseWorker(worker, false);
        }
    } catch (IOException e){
    	throw new XmlRpcException(0, e.getMessage());
    }
        
    }


    synchronized Worker getWorker(boolean async)
        throws IOException
    {        
        try
        {
            Worker w =(Worker) pool.pop();
            workers += 1;
            return w;
        }
        catch(EmptyStackException x)
        {
            if (workers < XmlRpc.getMaxThreads())
            {
                workers += 1;
                return new Worker();
            }
            throw new IOException("XML-RPC System overload");
        }
    }

    /**
       * Release possibly big per-call object references to allow them to be garbage collected
       */
    synchronized void releaseWorker(Worker w, boolean async)
    {
        w.result = null;
        if (pool.size() < 20 && !w.fault)
        {
            pool.push(w);
        }
        workers -= 1;
    }



    class Worker extends XmlRpc 
    {
        boolean fault;
        Object result = null;

        /**
         * The output buffer used in creating a request.
         */
        ByteArrayOutputStream buffer;

        public Worker()
        {
            super();
        }

        

         /**
         * Execute an XML-RPC call.
         */
        Object execute(byte[] request) throws XmlRpcException, IOException
        {
            if (logger.isDebugEnabled()) logger.debug("execute()");
            	fault = false;
            try
            {
                
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        logger.warn("Warning: URL Host: "+urlHostName+" vs. "+session.getPeerHost());
                        return true;
                    }
                };               
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
                URLConnection con = url.openConnection();
                              
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setAllowUserInteraction(false);  
                con.setConnectTimeout(CONNECT_TIMEOUT);
                con.setReadTimeout(READ_TIMEOUT);
                con.setRequestProperty("Content-Length", Integer.toString(request.length));
                con.setRequestProperty("Content-Type", "text/xml");
                if (auth != null) con.setRequestProperty("Authentication",auth);
                con.setRequestProperty("Response-Type","text/xml");
                
                OutputStream out = con.getOutputStream();
                out.write(request);
                out.flush();
                out.close();
                parse(con.getInputStream());
            
            } catch(Exception x)
            {
                throw new XmlRpcException(0,x.getMessage());
            }

            if (fault)
            {
                // generate an XmlRpcException
                XmlRpcException exception = null;
                try
                {
                    Hashtable f =(Hashtable) result;
                    String faultString =(String) f.get("faultString");
                    int faultCode = Integer.parseInt(
                            f.get("faultCode").toString());
                    exception = new XmlRpcException(faultCode,
                            faultString.trim());
                }
                catch(Exception x)
                {
                    throw new XmlRpcException(0, "Invalid fault response");
                }
                throw exception;
            }
            return result;
        } 



		/**
         * Execute an XML-RPC call.
         */
        Object execute(String method, Vector params) throws XmlRpcException, IOException
        {           
            fault = false;
            try
            {
                
                if (buffer == null)
                {
                    buffer = new ByteArrayOutputStream();                   
                }
                else
                {
                    buffer.reset();
                }

                XmlWriter writer = new XmlWriter(buffer,"UTF8");
                writeRequest(writer, method, params);
                writer.flush();
                byte[] request = buffer.toByteArray();
                
                HostnameVerifier hv = new HostnameVerifier() {
                    public boolean verify(String urlHostName, SSLSession session) {
                        logger.warn("Warning: URL Host: "+urlHostName+" vs. "+session.getPeerHost());
                        return true;
                    }
                };               
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
                URLConnection con = url.openConnection();
                                
                              
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setAllowUserInteraction(false);
                con.setConnectTimeout(CONNECT_TIMEOUT);
                con.setReadTimeout(READ_TIMEOUT);
                con.setRequestProperty("Content-Length", Integer.toString(request.length));
                con.setRequestProperty("Content-Type", "text/xml");
                if (auth != null) con.setRequestProperty("Authentication",auth);
                con.setRequestProperty("Response-Type","text/xml");
               
                OutputStream out = con.getOutputStream();
                                
                out.write(request);
                out.flush();
                out.close();
                
                parse(con.getInputStream());
            
            } catch(Exception x)
            {
                throw new XmlRpcException(0,x.getMessage());
            }

            if (fault)
            {
                // generate an XmlRpcException
                XmlRpcException exception = null;
                try
                {
                    Hashtable f =(Hashtable) result;
                    String faultString =(String) f.get("faultString");
                    int faultCode = Integer.parseInt(
                            f.get("faultCode").toString());
                    exception = new XmlRpcException(faultCode,
                            faultString.trim());
                }
                catch(Exception x)
                {
                    throw new XmlRpcException(0, "Invalid fault response");
                }
                throw exception;
            }
            return result;
        }
        
        
        /**
         * Execute an XML-RPC call.
         */
        InputStream executeStream(String method, Vector params) throws XmlRpcException, IOException
        {
           
            fault = false;
            
                if (buffer == null)
                {
                    buffer = new ByteArrayOutputStream();
                }
                else
                {
                    buffer.reset();
                }
                
                XmlWriter writer = new XmlWriter(buffer);
                writeRequest(writer, method, params);
                writer.flush();
                byte[] request = buffer.toByteArray();
                URLConnection con = url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setAllowUserInteraction(false);
                con.setConnectTimeout(CONNECT_TIMEOUT);                
                con.setRequestProperty("Content-Length",
                        Integer.toString(request.length));
                con.setRequestProperty("Content-Type", "text/xml");
                if (auth != null) con.setRequestProperty("Authentication",auth);
                con.setRequestProperty("Response-Type","application/octet-stream");
                OutputStream out = con.getOutputStream();
                out.write(request);
                out.flush();
                out.close();                             
                return con.getInputStream();
               

        }


        /**
          * Called when the return value has been parsed.
          */
        void objectParsed(Object what)
        {           
            result = what;
        }

        /**
          * Generate an XML-RPC request from a method name and a parameter vector.
          */
        void writeRequest(XmlWriter writer, String method,Vector params) throws IOException, XmlRpcException
        {
            writer.startElement("methodCall");
            writer.startElement("methodName");
            writer.write(method);
            writer.endElement("methodName");
            writer.startElement("params");
            int l = params.size();
            for (int i = 0; i < l; i++)
            {
                writer.startElement("param");
                writer.writeObject(params.elementAt(i));
                writer.endElement("param");
            }
            writer.endElement("params");
            writer.endElement("methodCall");
        }

       
        @Override
        public void startElement(String name, AttributeList attributes) throws SAXException {
            if ("fault".equals(name))
            {
                fault = true;
            }
            else
            {
                super.startElement(name, attributes);
            }	
        }
        	            
    } // end of inner class Worker



	public Object execute(String method, Object ... objects) throws XmlRpcException {		
		Vector v = new Vector(objects.length);
		for(Object o:objects){										
			if (o instanceof Object[]){
				Object[] os = (Object[])o;
				Vector s = new Vector(os.length);
				for(Object z:os){
					s.add(z);
				}
				v.add(s);				
			} else {
				v.add(o);
			}
		}	
		return execute(method, v);
	}
	
	
	
	
	
	
	

   
}
