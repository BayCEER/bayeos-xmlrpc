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
 * Manage objekte (secured)
 * @author oliver
 *
 */
public interface IObjektHandler {

	
	/**
	 * Read an objekt including its attribute by id
	 * @param Id objekt identifier
	 * @param objektArt 
	 * @return attribute values of objekt
	 * @throws XmlRpcException
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 */
	public Vector getObjekt(Integer Id, String objektArt)
			throws XmlRpcException;

	/**
	 * Create a reference from one object onto another object
	 * @param vonObjId
	 * @param aufObjId
	 * @param aufArt 
	 * @return Vector of Attributes 
	 * 		<ol>
	 * 			<li>Type as String</li>
	 * 			<li>Description as String</li>
	 * 			<li>Start as Date</li>
	 *			<li>End as Date</li>
	 *			<li>Id_Verweis as Integer</li>
	 *			<li>Id_von as Integer</li>
	 *			<li>Uname as String</li>
	 *			<li>Id_auf as Integer</li> 
	 *      </ol>        
	 * @throws XmlRpcException
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 */
	public Vector createReference(Integer vonObjId, Integer aufObjId,
			String aufArt) throws XmlRpcException;

	/** Get the lowest reference from one objekt onto another objekt
	 * @param Id
	 * @param objektArt 
	 * @return objekt as Vector of Attributes 
	 * @throws XmlRpcException
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 */
	public Vector getLowestRefObjekt(Integer Id, String objektArt)
			throws XmlRpcException;

	/** Get all inherited references off one objekt as a result of the objekt inheritance tree
	 * @param obj_id
	 * @return Vector of Attributes 
	 * 		<ol>
	 * 			<li>Type as String</li>
	 * 			<li>Description as String</li>
	 * 			<li>Start as Date</li>
	 *			<li>End as Date</li>
	 *			<li>Id_Verweis as Integer</li>
	 *			<li>Id_von as Integer</li>
	 *			<li>Uname as String</li>
	 *			<li>Id_auf as Integer</li> 
	 *      </ol>        
	 * @throws XmlRpcException
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 */
	public Vector getInheritedReferences(Integer obj_id) throws XmlRpcException;

	/**
	 * Get all direct references off one objekt 
	 * @param obj_id
	 * @return Vector of Attributes 
	 * 		<ol>
	 * 			<li>Type as String</li>
	 * 			<li>Description as String</li>
	 * 			<li>Start as Date</li>
	 *			<li>End as Date</li>
	 *			<li>Id_Verweis as Integer</li>
	 *			<li>Id_von as Integer</li>
	 *			<li>Uname as String</li>
	 *			<li>Id_auf as Integer</li> 
	 *      </ol>        
	 * @throws XmlRpcException
	 */
	public Vector getDirectReferences(Integer obj_id) throws XmlRpcException;

	/** Delete the reference of one objekt
	 * @param id_ref
	 * @param art 
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 * @return success
	 * @throws XmlRpcException
	 */
	public Boolean deleteReference(Integer id_ref, String art)
			throws XmlRpcException;
	
	/** Update the reference of one objekt
	 * 
	 * @param Id
	 * @param art art as string 
	 * @param von
	 * @param bis
	 * @return success
	 * @throws XmlRpcException
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 */
	public Boolean updateReference(Integer Id, String art , java.util.Date von,java.util.Date bis) 
			throws XmlRpcException;

	/** Update objekt with attributes by id
	 * @param id_obj
	 * @param art 
	 * @param attrib
	 * @return success
	 * @throws XmlRpcException
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 */
	public Boolean updateObjekt(Integer id_obj, String art, Vector attrib)
			throws XmlRpcException;

	/** Get the external (content management) url of one objekt
	 * @param id
	 * @param art 
	 * @return url 
	 * @throws XmlRpcException
	 * @see de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf.IObjektArtHandler#getObjektArts()
	 */
	public String getExternUrl(Integer id, String art) throws XmlRpcException;

}
