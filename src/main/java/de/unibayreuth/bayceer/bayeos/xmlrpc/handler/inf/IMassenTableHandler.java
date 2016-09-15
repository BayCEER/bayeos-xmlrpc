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
		

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;

/** 
 * Manage timeseries based measurement value (secured)
 * @author oliver
 *
 */
public interface IMassenTableHandler {

	/** 
     * Add multiple measurements as a byte array, existing records with same sampling time and series id are skipped 
     * @param b byte array composed of 
     * <ol>
     * <li>series id as four bytes integer, high byte first</li>
     * <li>sampling time in number of milliseconds since January 1, 1970, 00:00:00 GM as eight bytes long, high byte first.</li>
     * <li>measurement value as 4-byte float quantity according to the IEEE 754 floating-point "single format" bit layout</li>
     * </ol> 
     * @see <a href="http://download.oracle.com/javase/6/docs/api/java/lang/Float.html#floatToIntBits%28float%29">floatToRawIntBits</a> 
     * 
     * @return Boolean  
     * @since 1.7
     **/
	public Boolean addByteRows(byte[] b) throws XmlRpcException;
	
	
	
	
		
	/** 
     * Add multiple measurements as a byte array, existing records with same sampling time and series id are updated 
     * @param b byte array composed of 
     * <ol>
     * <li>series id as four bytes integer, high byte first</li>
     * <li>sampling time in number of milliseconds since January 1, 1970, 00:00:00 GM as eight bytes long, high byte first.</li>
     * <li>measurement value as 4-byte float quantity according to the IEEE 754 floating-point "single format" bit layout</li>
     * </ol> 
     * @see <a href="http://download.oracle.com/javase/6/docs/api/java/lang/Float.html#floatToIntBits%28float%29">floatToRawIntBits</a> 
     * 
     * @return Boolean  
     * @since 1.8
     **/
	public Boolean upsertByteRows(byte[] b) throws XmlRpcException;



	/** 
     * Add one row to a series. 
     * @param id series identifier
     * @param von sampling time 
     * @param wert measurement value 
     * @param status quality tag of measurement value   
     * @return success
     * @see ILookUpTableHandler#getStatus()
     * 
     *  
     **/
	public Boolean addRow(Integer id, java.util.Date von, Double wert,
			Integer status) throws XmlRpcException;

		
	/**
	 * Update one row of a series.  
	 * @param id series identifier
	 * @param old_von old sampling time 
	 * @param statId status quality tag of measurement value
	 * @param wert measurement value
	 * @param new_von new sampling time
	 * @return success
	 * @throws XmlRpcException
	 * @see ILookUpTableHandler#getStatus()
	 */
	public Boolean updateRow(Integer id, java.util.Date old_von,
			Integer statId, Double wert, java.util.Date new_von)
			throws XmlRpcException;

	/**
	 * Update status flag of several rows of a series.   
	 *
	 * @param id series identifier
	 * @param vonDates sampling times
	 * @param statId status quality tag of measurement values
	 * @return success 
	 * @throws XmlRpcException
	 * @see ILookUpTableHandler#getStatus()
	 */
	public Boolean updateRows(Integer id, Vector vonDates, Integer statId)
			throws XmlRpcException;

	/**
	 * Remove many rows of a series by sampling date. 
	 * @param id series id
	 * @param vonDates Vector of Date 
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean removeRows(Integer id, Vector vonDates)
			throws XmlRpcException;
	
	/**
	 * Remove many rows of a series by date interval. 
	 * @param id series id
	 * @param startDate Date of first measurement
	 * @param endDate Date of last measurement
	 * @return success
	 * @throws XmlRpcException
	 * @since 1.7
	 */
	public Boolean removeRowsByInterval(Integer id, Date startDate, Date endDate)
			throws XmlRpcException;


	/**
	 * Get rows of a series by status and time filter definition 
	 * @param id
	 * @param timeFilter vector of values 
	 * <ol>
	 * <li>start as Date</li>
	 * <li>end as Date</li>
	 * </ol>  
	 * @param statusFilter vector of status values as int 
	 * @return vector composed of two elements 
	 * <ol>
	 * 	<li>vector of column metadata</li>
	 *  <li>vector of row values, a row is composed as a 9 byte array:<p> 
	 *  [0,3] sampling time in number of milliseconds since January 1, 1970, 00:00:00 GM /1000 as int<p>
	 *  [4,8] value as float<p>
	 *  [9] status as short<p> 
	 *  </li>
	 *  
	 * </ol> 
	 * 		 element 1 is a byte array composed of rows, 
	 * 		 byte array format: Sampling Time/1000 as Integer, measurement value as Float, status quality flag as Byte
	 * @see ILookUpTableHandler#getStatus()   
	 * 
	 * @throws XmlRpcException
	 */
	public Vector getRows(Integer id, Vector timeFilter, Vector statusFilter)
			throws XmlRpcException;

	

	
	
	/** 
     * Remove all measurement values  
     * @param id series identifier
     * 
     * @return Boolean  
     * @since 1.7
     **/
	public Boolean removeAllRows(Integer id) throws XmlRpcException;

	/** 
     * Get Matrix of series values
     * @param ids vector of series identifiers as int 
     * @param timeFilter vector of values 
	 * <ol>
	 * <li>start as Date</li>
	 * <li>end as Date</li>
	 * </ol>  
	 * @param statusFilter vector of status values as int
	 * @param withStatus flag to export status information
	 *  
     * @return vector 
     * 
     *    
     * @since 1.7
     **/
	public Vector getMatrix(Vector ids,Vector timeFilter,Vector statusFilter, Boolean withStatus) throws XmlRpcException;
	
	
	
	

}
