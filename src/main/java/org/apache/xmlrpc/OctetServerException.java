package org.apache.xmlrpc;

/*
 * OctetServerException.java
 *
 * Created on 2. Juli 2004, 15:22
 */

/**
 *
 * @author  oliver
 */
public class OctetServerException extends XmlRpcException {
    
    
    public OctetServerException(int code, String msg) {
        super(code,msg);
    }
}
