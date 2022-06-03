package de.unibayreuth.bayceer.bayeos.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.unibayreuth.bayceer.bayeos.objekt.ObjektArt;
import de.unibayreuth.bayceer.bayeos.objekt.ObjektNode;

public class Client {

	private static Logger log = LoggerFactory.getLogger(Client.class);
	
	private XmlRpcClient xmlRpcClient = null;

	private static Client client = null;

	private Client() {
		readConfig();
	}

	private void readConfig() {
		Properties p = new Properties();
		try {
			p.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
			p.getProperty("version");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public static Client getInstance() {
		if (client == null)
			client = new Client();
		return client;
	}

	public static XmlRpcClient getXmlRpcClient() {
		return getInstance().xmlRpcClient;
	}

	public void connect(String url, String userName, String passWord) throws XmlRpcException {
		try {
			xmlRpcClient = new XmlRpcClient(url);
		} catch (MalformedURLException e) {
			throw new XmlRpcException(0, "Malformed url.");
		}
		xmlRpcClient.connect(userName, passWord);		
	}

	public boolean isConnected() {
		try {
			if (xmlRpcClient != null) {
				xmlRpcClient.execute("LookUpTableHandler.getVersion", new Vector());
				return true;
			} else {
				return false;
			}
		} catch (XmlRpcException e) {
			return false;
		}
	}


	public void close() throws XmlRpcException {
		if (xmlRpcClient != null) {
			xmlRpcClient.close();
		}
	}
	
	public ObjektNode getNode(Integer id) throws XmlRpcException  {
		Vector att = (Vector) xmlRpcClient.execute("TreeHandler.getNode", id);
		if (att != null) {
			return new ObjektNode(att);
		}
		return null;		
	}
	
	public Boolean nodeExists(Integer id, ObjektArt art) throws XmlRpcException {
		ObjektNode n = getNode(id);
		if (n!=null && n.getArt() == art) {
			return true;
		} else {
			return false;
		}
	}

	public ObjektNode getRoot(ObjektArt art) throws XmlRpcException, IOException {
		Vector att = (Vector) xmlRpcClient.execute("TreeHandler.getRoot", art.toString(), null, null, null);
		return new ObjektNode(att);
	}

	public ObjektNode[] getChilds(ObjektNode objektNode) throws XmlRpcException, IOException {

		Vector localVector = (Vector) xmlRpcClient.execute("TreeHandler.getChilds",
				objektNode.getParentId(), objektNode.getArt().toString(), Boolean.valueOf(false), null, null);
		ObjektNode[] arrayOfObjektNode = new ObjektNode[localVector.size()];
		int i = 0;
		Enumeration localEnumeration = localVector.elements();
		while (localEnumeration.hasMoreElements()) {
			ObjektNode localObjektNode = new ObjektNode((Vector) localEnumeration.nextElement());
			arrayOfObjektNode[(i++)] = localObjektNode;
		}
		return arrayOfObjektNode;
	}

	public ObjektNode newNode(Integer id, String name, ObjektArt art)
			throws XmlRpcException, IOException {
		Vector att = (Vector) xmlRpcClient.execute("TreeHandler.newNode", art.toString(),
				name, id);
		return new ObjektNode(att);
	}

	public Boolean renameNode(ObjektNode node, String name) throws XmlRpcException {
		return (Boolean) xmlRpcClient.execute("TreeHandler.renameNode", node.getId(),
				node.getArt().toString(), name);
	}

	public void deleteNode(ObjektNode node) throws XmlRpcException {
		xmlRpcClient.execute("TreeHandler.deleteNode", node.getId());
	}

	public String getNodePath(Integer id) throws XmlRpcException {
		return (String) xmlRpcClient.execute("TreeHandler.getNodePath", id);
	}

	public void setNodeReference(Integer vonId, Integer aufId, ObjektArt art)
			throws XmlRpcException {
		xmlRpcClient.execute("ObjektHandler.createReference", vonId, aufId,
				art.toString());
	}

}
