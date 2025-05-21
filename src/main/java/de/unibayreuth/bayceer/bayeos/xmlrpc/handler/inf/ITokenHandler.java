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
	
	/**
	 * Create a new JWS Token
	 *  
	 * @param userName Username
	 * @param passWord Password
	 * @return JWS token(with key signed JWT)
	 */
	public String createLoginToken(String userName, String passWord) throws XmlRpcException ;
}