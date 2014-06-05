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

import org.apache.xmlrpc.XmlRpcException;

/** 
 * Container for tool functions (secured)
 * @author oliver
 *
 */
public interface IToolsHandler {

	 
	
	/**
	 * Update user password 	 
	 * @param oldPassword old password 
	 * @param newPassword new password 
	 * @return true|false  
	 * @throws XmlRpcException
	 */
	public Boolean changePassword(String oldPassword, String newPassword) throws XmlRpcException;

	
	/**
	 * @param id series identifier
	 * @param art objektArt <'messung_massendaten'|'messung_labordaten'>
	 * @param vonDate start of sampling
	 * @param bisDate end of sampling 
	 * @param statusId flag of status 
	 * @return number of rows updated 
	 * @throws XmlRpcException
	 */
	public Integer updateRows(Integer id, String art, java.util.Date vonDate,
			java.util.Date bisDate, Integer statusId) throws XmlRpcException;
	


}
