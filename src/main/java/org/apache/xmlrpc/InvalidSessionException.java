package org.apache.xmlrpc;
public class InvalidSessionException extends XmlRpcException {
    
    public InvalidSessionException(int code, String msg){
        super(code,msg);
    }
    
}
