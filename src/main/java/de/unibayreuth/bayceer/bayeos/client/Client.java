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

import de.unibayreuth.bayceer.bayeos.objekt.ArraySerializationException;
import de.unibayreuth.bayceer.bayeos.objekt.Art;
import de.unibayreuth.bayceer.bayeos.objekt.MessungOrdner;
import de.unibayreuth.bayceer.bayeos.objekt.Node;
import de.unibayreuth.bayceer.bayeos.objekt.Objekt;
import de.unibayreuth.bayceer.bayeos.objekt.ArrayConverter;

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

	public Node getNode(Integer id) throws XmlRpcException {
		Vector att = (Vector) xmlRpcClient.execute("TreeHandler.getNode", id);
		if (att != null) {
			try {
				return (Node) ArrayConverter.toObject(Node.class, att.toArray());
			} catch (ArraySerializationException e) {
				throw new XmlRpcException(0, e.getMessage());
			}
		}
		return null;
	}

	public Boolean nodeExists(Integer id, String art) throws XmlRpcException {
		Node n = getNode(id);
		if (n != null && n.getArt() == art) {
			return true;
		} else {
			return false;
		}
	}

	public Node getRoot(String art) throws XmlRpcException, IOException {
		Vector att = (Vector) xmlRpcClient.execute("TreeHandler.getRoot", art, null, null, null);
		if (att != null) {
			try {
				return (Node) ArrayConverter.toObject(Node.class, att.toArray());
			} catch (ArraySerializationException e) {
				throw new XmlRpcException(0, e.getMessage());
			}
		}
		return null;
	}

	public Node[] getChilds(Node objektNode) throws XmlRpcException, IOException {
		Vector childs = (Vector) xmlRpcClient.execute("TreeHandler.getChilds", objektNode.getIdSuper(),
				objektNode.getArt(), Boolean.valueOf(false), null, null);
		Node[] out = new Node[childs.size()];
		int i = 0;
		Enumeration<Vector> localEnumeration = childs.elements();
		while (localEnumeration.hasMoreElements()) {
			try {
				out[(i++)] = (Node) ArrayConverter.toObject(Node.class, localEnumeration.nextElement().toArray());
			} catch (ArraySerializationException e) {
				throw new XmlRpcException(0, e.getMessage());
			}
		}
		return out;
	}

	public Node newNode(Integer parentId, String name, String art) throws XmlRpcException, IOException {
		Vector att = (Vector) xmlRpcClient.execute("TreeHandler.newNode", art, name, parentId);
		Node n;
		try {
			n = (Node) ArrayConverter.toObject(Node.class, att.toArray());
		} catch (ArraySerializationException e) {
			throw new XmlRpcException(0, e.getMessage());
		}
		return n;

	}

	public Node findOrCreateNode(Integer parentId, String name, String art) throws XmlRpcException {
		Vector att = (Vector) xmlRpcClient.execute("TreeHandler.findOrSaveNode", art, name, parentId);
		Node n;
		try {
			n = (Node) ArrayConverter.toObject(Node.class, att.toArray());
		} catch (ArraySerializationException e) {
			throw new XmlRpcException(0, e.getMessage());
		}
		return n;
	}

	public Boolean renameNode(Node node, String name) throws XmlRpcException {
		return (Boolean) xmlRpcClient.execute("TreeHandler.renameNode", node.getId(), node.getArt(), name);
	}

	public void deleteNode(Node node) throws XmlRpcException {
		xmlRpcClient.execute("TreeHandler.deleteNode", node.getId());
	}

	public String getNodePath(Integer id) throws XmlRpcException {
		return (String) xmlRpcClient.execute("TreeHandler.getNodePath", id);
	}

	public void setNodeReference(Integer vonId, Integer aufId, String art) throws XmlRpcException {
		xmlRpcClient.execute("ObjektHandler.createReference", vonId, aufId, art);
	}

	public <T extends Objekt> T getNodeObjekt(Node node, Class<T> classz) throws XmlRpcException {

		Vector<Object> o = (Vector<Object>) xmlRpcClient.execute("ObjektHandler.getObjekt", node.getId(),
				node.getArt().toString());
		try {
			return (T) ArrayConverter.toObject(classz, o.toArray());
		} catch (ArraySerializationException e) {
			throw new XmlRpcException(0, e.getMessage());
		}
	}
	
			
	public <T extends Objekt> void updateNodeObjekt(T o) throws XmlRpcException {		
		// Only a subset of attributes are used by the updateObjekt handler !
		// This is is a poor asymmetric design ;.(
		xmlRpcClient.execute("ObjektHandler.updateObjekt", o.getId(), o.getArt(), o.updateVector());
	}

}
