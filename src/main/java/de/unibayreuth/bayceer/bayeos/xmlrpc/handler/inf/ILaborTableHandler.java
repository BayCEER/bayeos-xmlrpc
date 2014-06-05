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
 * 
 *  Manage laboratory measurement values (secured)
 * 
 * 
 *  @author oliver
 *  
 */
public interface ILaborTableHandler {

	/** Update Row 
	 * @param id
	 * @param oldbis
	 * @param values vector of 
	 * <ol>
	 * <li>status as Integer</li>
	 * <li>start of sampling as Date</li>
	 * <li>end of sampling as Date</li>
	 * <li>measurement value as Double</li>
	 * <li>laboratory number as Strng</li>
	 * <li>precision as Double</li>
	 * <li>loq as Double</li>
	 * <li>notes as String</li>
	 * </ol> 
	 * 					
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean updateRow(Integer id, java.util.Date oldbis, Vector values)
			throws XmlRpcException;

	/**
	 * @param id
	 * @param bisDates Vector\<Date\>
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean removeRows(Integer id, Vector bisDates)
			throws XmlRpcException;

	/**
	 * @param primaryKey series identifier
	@param timeFilter vector of values 
	 * <ol>
	 * <li>start as Date</li>
	 * <li>end as Date</li>
	 * </ol> 
	 * @param statusFilter vector<integer>
	 * @see ILookUpTableHandler#getStatus()   
	 * @return vector of rows
	 * @throws XmlRpcException
	 */
	public Vector getRows(Integer primaryKey, Vector timeFilter, Vector statusFilter) throws XmlRpcException;

	/**
	 * @param id series identifier
	 * @param bisDates Vector<Dates>
	 * @param status flag 
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean updateRows(Integer id, Vector bisDates, Integer status)
			throws XmlRpcException;

	/**
	 * @param id series identifier
	 * @param values Vector of
	 * <ol>
	 * <li>status as Integer</li>
	 * <li>start of sampling as Date</li>
	 * <li>end of sampling as Date</li>
	 * <li>measurement value as Double</li>
	 * <li>laboratory number as String</li>
	 * <li>precision as Double</li>
	 * <li>level of quantification as Double</li>
	 * <li>notes as String</li>
	 * </ol>  
 
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean addRow(Integer id, Vector values) throws XmlRpcException;

	/**
	 * @param Id series identifier
	 * @param rows Vector of Strings 
	 * @param shortCols flag to define format of rows
	 * <ul>
	 * <li>true: "end of sampling, measurement value"</li>
	 * <li>false: "start of sampling as Date, end of sampling as Date,measurement value as Double,laboratory number as String, 
	 * 				precision as Double, loq as Double, notes as String"</li>
	 * </ul>   
	 * All dates are formated as "dd.MM.yyyy HH:mm:ss" !
	 * 					 
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean importFlat(Integer Id, Vector rows, boolean shortCols)
			throws XmlRpcException;
	
	/** 
     * Remove all measurement values  
     * @param id series identifier  
     * 
     * @return Boolean  
     * @since 1.7
     **/
	public Boolean removeAllRows(Integer id) throws XmlRpcException;


}
