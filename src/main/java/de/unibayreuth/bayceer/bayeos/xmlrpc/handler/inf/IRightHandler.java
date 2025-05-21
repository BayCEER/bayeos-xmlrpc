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
 * Manage rights on objekte (secured)
 * @author oliver
 *
 */
public interface IRightHandler {

	
	/**
	 * @param obj_id objekt identifier
	 * @return vector of rights, each right as a vector of
	 * 				[0] User as String  
	 * 				[1] Read as Boolean  
	 * 				[2]	Write as Boolean
	 * 				[3] Exec as Boolean 
	 * 				[4] Inherit as Boolean
	 * 				[5] Editable as Boolean
	 * 				[6] Uname as String
	 * 				[7] User id as Integer
	 * @throws XmlRpcException
	 */
	public Vector getRights(Integer obj_id) throws XmlRpcException;
	
	/** fullaccess check of user on objekt  
	 * @param id objekt identifier
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean hasFullAccess(Integer id) throws XmlRpcException;
	/** update right for user on objekt 
	 * @param id_obj
	 * @param id_benutzer
	 * @param right read|write|exec|inherit
	 * @param enabled
	 * @return success 
	 * @throws XmlRpcException
	 */
	public Boolean updateRight(Integer id_obj, Integer id_benutzer,	String right, Boolean enabled) throws XmlRpcException;
	
	/** create right on objekt for user 
	 * @param id_obj
	 * @param id_benutzer
	 * @param read
	 * @param write
	 * @param exec
	 * @param inherit
	 * @return success 
	 * @throws XmlRpcException
	 */
	public Boolean createRight(Integer id_obj, Integer id_benutzer,	Boolean read, Boolean write, Boolean exec, Boolean inherit)	throws XmlRpcException;
	/**
	 * delete right on objekt for user 
	 * @param id_obj
	 * @param id_benutzer
	 * @return success 
	 * @throws XmlRpcException
	 */
	public Boolean deleteRight(Integer id_obj, Integer id_benutzer)	throws XmlRpcException;

}
