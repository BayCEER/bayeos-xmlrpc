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
 * Manage measurement series properties (secured)
 * @author oliver
 *
 */
public interface IMessungHandler {

	/**
	 * @param Id series identifier
	 * @return resolution in seconds
	 * @throws XmlRpcException
	 */
	public Integer getResolution(Integer Id) throws XmlRpcException;
	
	/**
	 * @param Ids series identifiers
	 * @return minimum resolution in seconds
	 * @throws XmlRpcException
	 */
	public Integer getMinResolution(Vector Ids) throws XmlRpcException;

	/**
	 * @param ids of series identifiers as integer
	 * @return Axis labels as Vector of Strings
	 * @throws XmlRpcException
	 */
	public Vector getNumberAxisLabels(Vector ids) throws XmlRpcException;

	/**
	 * @param Id series identifier
	 * @return Axis label as String
	 * @throws XmlRpcException
	 */
	public String getNumberAxisLabel(Integer Id) throws XmlRpcException;
	
	
	/**
	 * @param Id series identifier
	 * @return timezone as String
	 * @throws XmlRpcException
	 */
	public String getTimeZone(Integer Id) throws XmlRpcException;

}
