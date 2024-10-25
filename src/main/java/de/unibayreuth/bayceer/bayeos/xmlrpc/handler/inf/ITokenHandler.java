package de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf;

import org.apache.xmlrpc.XmlRpcException;

/**
 * 
 * Manage tokens
 * 
 * 
 *  @author oliver
 *  
 */
public interface ITokenHandler {
	
	/*
	 * Returns a JWS (with key signed JWT)
	 */
	public String createLoginToken() throws XmlRpcException ;
}