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
 * Manage aggregated measurement values (secured)
 * 
 * 
 *  @author oliver
 *  
 */
public interface IAggregationTableHandler {
	
	/** Get aggregated measurement values
	 * @param primaryKey series identifier
	 * @param timeFilter vector of values 
	 * <ol>
	 * <li>start as Date</li>
	 * <li>end as Date</li>
	 * </ol> 
	 * @param aggregateFilter vector of values
	 * <ol>
	 * <li>aggregate function id as integer</li>
	 * <li>aggregate interval id as integer</li>
	 * </ol> 
	 * @return vector of rows 
	 * <ol>
	 * <li>aggregation time as date</li>
	 * <li>aggregation value as double</li>
	 * <li>count of input values in aggregation function as integer</li>
	 * </ol> 
	 * 
	 * @throws XmlRpcException
	 * 
	 * @see ILookUpTableHandler#getAgrFunktionen()
	 * @see ILookUpTableHandler#getAgrIntervalle()
	 * 
	 */
	public Vector getRows(Integer primaryKey, Vector timeFilter, Vector aggregateFilter) throws XmlRpcException;
	
	
	
	/** 
     * Get Matrix of aggregated series values
     * @param ids vector of series identifiers as int 
     * @param timeFilter vector of values 
	 * <ol>
	 * <li>start as Date</li>
	 * <li>end as Date</li>
	 * </ol>  
	 * @param aggregateFilter vector of values: 
	 * <ol>
	 * <li>aggregation function id as int</li>
	 * <li>aggregation interval id as int</li>
	 * </ol>
	 * @param withCounts flag to export number of aggregation function input values 
	 *  
     * @return vector 
     * 
     *    
     * @since 1.7
     **/
	public Vector getMatrix(Vector ids,Vector timeFilter,Vector aggregateFilter, Boolean withCounts) throws XmlRpcException;
	


	
	

}
