/*******************************************************************************
 * Copyright (c) 2011 University of Bayreuth - BayCEER.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     University of Bayreuth - BayCEER - initial API and implementation
 ******************************************************************************/
package de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;

/**
 * Session handler  (unsecured)
 * 
 * @author oliver
 *
 */
public interface ILoginHandler {

	/** Login user and return information for http authentication header.
	 * <p>
	 * The returned sessionId and userId is used for authentication by secured handlers. 
	 * To access a secured handler the http request needs an authentification header field name "Authentication".
	 * The field value must be a colon separated String of session id and user id.
	 * <p>
	 * Example value:
	 * <p> 
	 * String( Base64.encode( (SessionId.toString() + ':' + UserId.toString() ).getBytes() )).trim();
	 *  
	 * @param Login Username 
	 * @param PassWord Password 
	 * 
	 * @return Vector<Integer> 
	 * <ol>
	 * <li>SessionId as Integer</li>
	 * <li>UserId as Integer</li>
	 * </ol> 
	 *  
	 * @throws XmlRpcException
	 * @see ILogOffHandler
	 * 
	 * 
	 *  
	 *    
	 */
	public Vector createSession(String Login, String PassWord)
			throws XmlRpcException;
	
	
	/** Login user by token and return information for http authentication header.
	 * <p>
	 * The returned sessionId and userId is used for authentication by secured handlers. 
	 * To access a secured handler the http request needs an authentification header field name "Authentication".
	 * The field value must be a colon separated String of session id and user id.
	 * <p>
	 * Example value:
	 * <p> 
	 * String( Base64.encode( (SessionId.toString() + ':' + UserId.toString() ).getBytes() )).trim();
	 *  
	 * @param token JWT Token as String  
	 * 
	 * @return Vector<Integer> 
	 * <ol>
	 * <li>SessionId as Integer</li>
	 * <li>UserId as Integer</li>
	 * </ol> 
	 *  
	 * @throws XmlRpcException
	 * @see ILogOffHandler
	 * @since 2.1.0
	 *  
	 *    
	 */
	public Vector createSessionByToken(String token) throws XmlRpcException;
	

}
