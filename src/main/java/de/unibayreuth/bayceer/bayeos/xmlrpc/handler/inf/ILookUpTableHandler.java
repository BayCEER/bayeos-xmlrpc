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
 * Common lookup functions, usually called by clients only once after login (unsecured)
 * 
 * @author oliver
 *
 */
public interface ILookUpTableHandler {

	/**
	 * @return IntervalTypes as Vector of rows</p> 
	 * 			Row as Vector
	 * 			<ol>
	 * 			<li>id as Integer</li>
	 * 			<li>bezeichnung as String</li>
	 * 			</ol>
	 * 		
	 * @throws XmlRpcException
	 */
	public Vector getIntervalTypes() throws XmlRpcException;

	/**
	 * @return Status as Vector of rows<p> 
	 * 			Row as Vector
	 * 			<ol>
	 * 			<li>id as Integer</li>
	 * 			<li>bezeichnung as String</li>
	 * 			<li>beschreibung as String</li>
	 * 			</ol>
	 * @throws XmlRpcException
	 */
	public Vector getStatus() throws XmlRpcException;

	/**
	 * @return vector of benutzer attributes as row vector
	 * @throws XmlRpcException
	 */
	public Vector getBenutzer() throws XmlRpcException;

	/**
	 * @return vector of group attributes as row vector
	 * @throws XmlRpcException
	 */
	public Vector getGruppen() throws XmlRpcException;

	/**
	 * @return Intervals as Vector<p>
	 * 			<ol>
	 * 			<li>id as Integer</li>
	 * 			<li>name as String</li>
	 * 			<li>seconds of interval as Integer</li>
	 * 			</ol>	
	 * @throws XmlRpcException
	 */
	public Vector getAgrIntervalle() throws XmlRpcException;

	/**
	 @return Intervals as Vector<p>
	 * 			<ol>
	 * 			<li>id as Integer</li>
	 * 			<li>name as String</li>		
	 * 			</ol>
	 * @throws XmlRpcException
	 */
	public Vector getAgrFunktionen() throws XmlRpcException;
	
	/**
	 @return Coordinate Reference Systems as Vector<p>
	 * 			<ol>
	 * 			<li>id as Integer</li>
	 * 			<li>short name as String</li>		
	 * 			<li>EPSG id as Integer</li>
	 * 			</ol>
	 * @throws XmlRpcException
	 */
	public Vector getCRS() throws XmlRpcException;
	
	
	/**
	 * @return TimeZones as Vector<p>
	 * 			<ol>
	 * 			<li>id as Integer</li>
	 * 			<li>name as String</li>	
	 * 			</ol>	
	 * @throws XmlRpcException
	 */
	
	public Vector getTimeZones() throws XmlRpcException;
	
	
	/**
	 * @return Version as String
	 * <p>
	 * Convention: major.minor.maintenance
	 * <p>
	 * For instance: 1.7.0
	 * 				 
	 * 				 
	 * 				
	 * @throws XmlRpcException if version information can't be found
	 * @since 1.8
	 * 
	 */
	
	public String getVersion() throws XmlRpcException;
	
	
	
	
	
	

}
