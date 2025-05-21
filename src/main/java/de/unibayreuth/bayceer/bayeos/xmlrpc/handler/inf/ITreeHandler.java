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

/** Manage parent child relationship of objekte (secured)
 * @author oliver
 *
 */
public interface ITreeHandler {
	
	/**
	 * Get node of a tree by id 
	 * @param id   
	 * 						
	 * @return the node as vector of values
	 * <ol>
	 * <li>check_write as Boolean</li>
	 * <li>check_exec as Boolean</li>
	 * <li>id as Integer</li>
	 * <li>id_super as Integer</li>
	 * <li>art as String</li>
	 * <li>name as String</li>
	 * <li>rec_start as Date</li>
	 * <li>rec_end as Date</li>
	 * <li>plan_start as Date</li>
	 * <li>plan_end as Date</li>
	 * <li>active as Boolean</li>
	 * <li>recordsMissing as Boolean</li>
	 * <li>hasChild as Boolean</li>
	 * </ol>
	 *   					    						      						
	 * 			
	 * @throws XmlRpcException
	 */
	public Vector getNode(Integer id) throws XmlRpcException;

	/**
	 * Get upper root node of a tree 
	 * @param art object art  
	 * @param activeFilter 
	 * @param missingInterval interval defined by postgresql as String 
	 * @param timeFilter vector
	 * 	<ol>
	 *    <li>start as Date</li>
	 *    <li>end as Date</li>
	 *  </ol> 
	 * 						
	 * @return the root as vector of values
	 * <ol>
	 * <li>check_write as Boolean</li>
	 * <li>check_exec as Boolean</li>
	 * <li>id as Integer</li>
	 * <li>id_super as Integer</li>
	 * <li>art as String</li>
	 * <li>name as String</li>
	 * <li>rec_start as Date</li>
	 * <li>rec_end as Date</li>
	 * <li>plan_start as Date</li>
	 * <li>plan_end as Date</li>
	 * <li>active as Boolean</li>
	 * <li>recordsMissing as Boolean</li>
	 * <li>hasChild as Boolean</li>
	 * </ol>
	 *   					    						  
    						
	 * 			
	 * @throws XmlRpcException
	 */
	public Vector getRoot(String art, Boolean activeFilter,
			String missingInterval, Vector timeFilter) throws XmlRpcException;
	
	
	
	
	/** Get children of a node 
	 * @param SuperId the parent node id to query 
	 * @param art  
	 * @param webFilter websystem objekt art as String   
	 * @param activeFilter filter to elimate inactive nodes. A node is inactive the current date
	 * is not between node.plan_start and node.plan_end
	 * @param missingInterval postgresql interval as String 
	 * @param timeFilter vector of values 
	 * <ol>
	 *    <li>start as Date</li>
	 *    <li>end as Date</li>
	 *  </ol> 
	 * 
	 * @return values as Vector
	 * @see       #getRoot(String, Boolean, String, Vector)
	 * @deprecated Please use getChilds without webFilter parameter instead. The argument webFilter is ignored. 
	 *  				 
	 * @throws XmlRpcException
	 */
	public Vector getChildren(Integer SuperId, String art, Vector webFilter, Boolean activeFilter, String missingInterval, Vector timeFilter)
	throws XmlRpcException;


	
	/** Get childs of a node 
	 * @param SuperId the parent node id to query 
	 * @param art  
	 * @param activeFilter filter to elimate inactive nodes. A node is inactive the current date
	 * is not between node.plan_start and node.plan_end
	 * @param missingInterval postgresql interval as String 
	 * @param timeFilter vector of values 
	 * <ol>
	 *    <li>start as Date</li>
	 *    <li>end as Date</li>
	 *  </ol> 
	 * 
	 * @return values as Vector
	 * @see       #getRoot(String, Boolean, String, Vector)
	 * @since 1.8.0
	 *  				 
	 * @throws XmlRpcException
	 */
	public Vector getChilds(Integer SuperId, String art, Boolean activeFilter, String missingInterval, Vector timeFilter)
			throws XmlRpcException;

	/** Get all children of a node 
	 * @param SuperId the parent node id to query 
	 * @param isExtern  
	 * @param webFilter websystem objekt art as String   
	 * @param antFilter as String
	 * @param artFilter as String
	 * <p>Allows searching the tree with ANT-like wildcard syntax:</p>
	 * <ul>
	 * 	<li>* denotes all characters but not the directory separator</li>
	 *  <li>** denotes all characters including the directory separator</li>
	 *  </ul>
	 * @param maxDepth as int -1==unlimited 
	 * @param activeFilter filter to elimate inactive nodes. A node is inactive the current date
	 * is not between node.plan_start and node.plan_end
	 * @param missingInterval postgresql interval as String 
	 * @param timeFilter vector of values 
	 * <ol>
	 *    <li>start as Date</li>
	 *    <li>end as Date</li>
	 *  </ol> 
	 * 
	 * @return values as Vector with the following entries
	 * <ol>
	 * 	<li>id integer</li>
	 * <li>id_super integer</li>
	 * <li>id_art integer</li>
	 * <li>plan_start timestamp</li>
	 * <li>plan_end timestamp</li>
	 * <li>rec_start timestamp</li>
	 * <li>rec_end timestamp</li>
	 * <li>name text</li>
	 * <li>path text</li>
	 * <li>art text</li>
	 * <li>active boolen</li>
	 * <li>missing boolean</li>
	 * </ol>
	 *  				 
	 * @throws XmlRpcException
	 */
	public Vector getAllChildren(Integer SuperId, Boolean isExtern, Vector webFilter, String antFilter, String artFilter, Integer maxDepth,
			Boolean activeFilter, String missingInterval, Vector timeFilter)
			throws XmlRpcException;

	/** Rename node 
	 * @param obj_id object identifier 
	 * @param art object art
	 * @param name
	 * @return success 
	 * @throws XmlRpcException
	 */
	public Boolean renameNode(Integer obj_id, String art, String name)
			throws XmlRpcException;

	/** Delete a node by id 
	 * @param obj_id
	 * @return success 
	 * @throws XmlRpcException
	 */
	public Boolean deleteNode(Integer obj_id) throws XmlRpcException;

	/**
	 * @param id
	 * @param targetId
	 * @return success 
	 * @throws XmlRpcException
	 */
	public Boolean moveNode(Integer id, Integer targetId)
			throws XmlRpcException;

	/**
	 * @param id_von
	 * @param id_auf
	 * @return success 
	 * @throws XmlRpcException
	 */
	@Deprecated
	public Boolean linkNode(Integer id_von, Integer id_auf)
			throws XmlRpcException;

	/** Copy a node with all childs to a new target
	 * @param id
	 * @param targetId
	 * @return identifier of new objekt  
	 * @throws XmlRpcException
	 */
	public Integer copyNode(Integer id, Integer targetId)
			throws XmlRpcException;

	/**
	 * @param art
	 * @param name
	 * @param parent_id
	 * @return objekt node attributes @see #getRoot(String, Boolean, String, Vector)
	 * @throws XmlRpcException
	 */
	public Vector newNode(String art, String name, Integer parent_id)
			throws XmlRpcException;
	
	/** If a matching persistent node is not found a new node is created, saved and returned.
	 * @param art
	 * @param name
	 * @param parent_id
	 *  	Note: when parent_id is null root node is selected as parent 
	 * @return objekt node attributes @see #getRoot(String, Boolean, String, Vector)
	 * @throws XmlRpcException
	 * @since 1.8.0
	 */
	public Vector findOrSaveNode(String art, String name, Integer parent_id) throws XmlRpcException;
	
	
			
	
	/** Get node path by id  	
	 * @param id
	 * @return String as node path 
	 * @throws XmlRpcException
	 * @since 1.8.0
	 */
	public String getNodePath(Integer id) throws XmlRpcException;		


	/**
	 * @param obj_id
	 * @param art
	 * @return identifiers of parents as vector of integer
	 * @throws XmlRpcException
	 * 
	 */
	public Vector getInheritedParentIds(Integer obj_id, String art)
			throws XmlRpcException;

	/**
	 * @param name
	 * @return objekt node attributes @see #getRoot(String, Boolean, String, Vector)
	 * @throws XmlRpcException
	 */
	public java.util.Vector findNode(java.lang.String name)
			throws XmlRpcException;

}
