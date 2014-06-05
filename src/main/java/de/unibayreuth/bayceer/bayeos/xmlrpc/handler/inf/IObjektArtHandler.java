package de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;

public interface IObjektArtHandler {

	
	/** Get all registered unique ObjektArt types 
	 * @since 1.9.0 
	 * @return Vector of Strings 
	 * @throws XmlRpcException
	 */
	public Vector getObjektArts() throws XmlRpcException;
	
}
