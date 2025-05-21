package org.apache.xmlrpc;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * OctetServer.java
 *
 * Created on 2. Juli 2004, 15:09
 */

/**
 *
 * @author  oliver
 */
public class OctetServer {
    
    protected final static Logger logger = LoggerFactory.getLogger(OctetServer.class); 
    
    private Hashtable handlers;
    private Stack pool;
    private int workers;
    
    
    
        /** Creates a new instance of OctetServer */
    public OctetServer() {
        handlers = new Hashtable();
        pool = new Stack();
        workers = 0;
    }

    public void addHandler(String handlername, Object handler)
    {
        logger.debug("addHandler()");
        handlers.put(handlername, handler);
    }
    
    public void removeHandler(String handlername)
    {
        logger.debug("removeHandler()");
        handlers.remove(handlername);
    }
    
    
    /**
     * Parse the request and execute the handler method, if one is
     * found. If the invoked handler is SessionXmlRpcHandler,
     * use the credentials to authenticate the user.
     */
    public void execute(InputStream is,OutputStream out, Integer sessionId, Integer userId) throws OctetServerException
    {
        if (logger.isDebugEnabled()) {
        logger.debug("execute(" + sessionId + "," + userId + ")");
        }
        OctetWorker worker = getWorker();
        worker.execute(is,out,sessionId,userId);
        pool.push(worker);
    }
    
    private final OctetWorker getWorker()
    {
        if (logger.isDebugEnabled()) {
        logger.debug("getWorker()");
        }
        try
        {
            return(OctetWorker) pool.pop();
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
                return new OctetWorker();
            }
            throw new RuntimeException("System overload");
        }
    }

    /**
     * Performs streaming, parsing, and handler execution.
     * Implementation is not thread-safe.
     */
    class OctetWorker extends XmlRpc
    {
        private Vector inParams;
        private XmlWriter writer;
        protected Logger logger = LoggerFactory.getLogger(OctetWorker.class); 

        /**
         * Creates a new instance.
         */
        protected OctetWorker()
        {
            inParams = new Vector();
        }

        /**
         * Given a request for the server, generates a response.
         */
        public void execute(InputStream in, OutputStream out, Integer sessionId,Integer userId) throws OctetServerException 
        {
            try
            {
                // Do the work
                executeInternal(in,out,sessionId,userId);
            }
            finally
            {
                // Release most of our resources
                inParams.removeAllElements();
            }
        }
        
         /**
         * Called when an object to be added to the argument list has
         * been parsed.
         */
        void objectParsed(Object what) {
            if (logger.isDebugEnabled()) {
                logger.debug("objectParsed()");
            }
            inParams.addElement(what);
        }
        

        private void executeInternal(InputStream in,OutputStream out, Integer sessionId,Integer userId) throws OctetServerException  {
            try {
                parse(in);
                // check for errors from the XML parser
                if (errorLevel > NONE) throw new OctetServerException(0, errorMsg);
                Object handler = null;
                String handlerName = null;
                int dot = methodName.lastIndexOf('.');
                if (dot > -1)  {
                    handlerName = methodName.substring(0, dot);
                    handler = handlers.get(handlerName);
                    if (handler != null)
                    {
                        methodName = methodName.substring(dot + 1);
                    }
                }

                if (handler == null) {
                    if (dot > -1) {
                        throw new OctetServerException(0, "OctetHandler object \""+
                                handlerName + "\" not found and no default handler registered.");
                    } else {
                        throw new OctetServerException(0, "OctetHandler object not found for \""+
                                methodName + "\": no default handler registered.");
                    }
                }
                
                               
                if (handler instanceof SessionHandler)
                {
                    if (sessionId == null || userId == null) {
                        throw new IllegalArgumentException("Session_Id or User_Id in header is null");
                    }                    
                    ((SessionHandler)handler).checkSession(sessionId, userId);                    
                
                }
                                
                ((OctetRpcHandler) handler).execute(out,methodName,inParams);
                    
                                
               
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new OctetServerException(0, e.getMessage());
        }
      }
    } // OctetWorker
}