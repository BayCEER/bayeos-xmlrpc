package org.apache.xmlrpc;


/**
 * This is thrown by the XmlRpcClient if the remote server reported an error. 
 * If something went wrong at a lower level (e.g. no http connection) an 
 * IOException will be thrown instead.
 *
 * @author <a href="mailto:hannes@apache.org">Hannes Wallnoefer</a>
 */
public class XmlRpcException    
    extends Exception
{
    /**
     * The fault code of the exception. For servers based on this library, this will always be 0.
     * (If there are predefined error codes, they should be in the XML-RPC spec.)
     */
    public final int code;

    public XmlRpcException (int code, String message)
    {
        super (message);
        this.code = code;
    }

}
