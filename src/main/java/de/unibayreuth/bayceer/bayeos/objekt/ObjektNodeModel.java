package de.unibayreuth.bayceer.bayeos.objekt;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;

import de.unibayreuth.bayceer.bayeos.client.Client;

public class ObjektNodeModel
{
  protected static final Logger logger = Logger.getLogger(ObjektNodeModel.class);
  
  public ObjektNode getRoot(ObjektArt paramObjektArt)
    throws XmlRpcException, IOException
  {
	Vector att =  (Vector)Client.getXmlRpcClient().execute("TreeHandler.getRoot",paramObjektArt.toString(), null, null, null) ;
    return new ObjektNode(att);
  }
  
 
  public ObjektNode[] getChilds(ObjektNode paramObjektNode)
    throws XmlRpcException, IOException
  {
	  
	  
    Vector localVector = (Vector)Client.getXmlRpcClient().execute("TreeHandler.getChilds",paramObjektNode.getParentId(), paramObjektNode.getArt().toString(), Boolean.valueOf(false), null, null);
    ObjektNode[] arrayOfObjektNode = new ObjektNode[localVector.size()];
    int i = 0;
    Enumeration localEnumeration = localVector.elements();
    while (localEnumeration.hasMoreElements())
    {
      ObjektNode localObjektNode = new ObjektNode((Vector)localEnumeration.nextElement());
      arrayOfObjektNode[(i++)] = localObjektNode;
    }
    return arrayOfObjektNode;
  }

  public ObjektNode newNode(Integer paramInteger, String paramString, ObjektArt paramObjektArt)
    throws XmlRpcException, IOException
  {
	Vector att = (Vector)Client.getXmlRpcClient().execute("TreeHandler.newNode",paramObjektArt.toString(), paramString, paramInteger);
    return new ObjektNode(att);
  }

  public Boolean renameNode(ObjektNode paramObjektNode, String paramString)
    throws XmlRpcException
  {
	return (Boolean)Client.getXmlRpcClient().execute("TreeHandler.renameNode", paramObjektNode.getId(), paramObjektNode.getArt().toString(), paramString);    
  }
  
  public void deleteNode(ObjektNode node) throws XmlRpcException{	  
	  Client.getXmlRpcClient().execute("TreeHandler.deleteNode", node.getId());	  
  }
  
  
  public String getNodePath(Integer paramInteger)
    throws XmlRpcException
  {
	  return (String)Client.getXmlRpcClient().execute("TreeHandler.getNodePath", paramInteger);
  }

  public void setNodeReference(Integer paramInteger1, Integer paramInteger2, ObjektArt paramObjektArt)
    throws XmlRpcException
  {	  
	 Client.getXmlRpcClient().execute("ObjektHandler.createReference", paramInteger1, paramInteger2, paramObjektArt.toString());    
  }
}
