package de.unibayreuth.bayceer.bayeos.client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class Client {

	private static Logger log = Logger.getLogger(Client.class);
	private static String clientVersion = "unknown";

	private String bayEOSVersion = "";
	private XmlRpcClient xmlRpcClient = null;
	
	
	

	private static Client client = null;
	
	private Client(){
		readConfig();
	}
	
	
	
	private void readConfig() {
		Properties p = new Properties();
		try {			
			p.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
			clientVersion = p.getProperty("version");			
		} catch (IOException e) {
			log.error(e.getMessage());
		}				
	}



	public static Client getInstance(){
		if (client == null) client = new Client();
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
			if (!xmlRpcClient.getDbVersion().equals(clientVersion)){
				log.warn("Version missmatch between client and server.");
			}	
	}
	
	public boolean isConnected(){		
		try {
			if (xmlRpcClient!=null){
				xmlRpcClient.execute("LookUpTableHandler.getVersion", new Vector());
				return true;
			} else {
				return false;
			}									
		} catch (XmlRpcException e){
			return false;
		}				
	}
	
	
	
	
	
	
	
	public String getBayEOSVersion() {
		return bayEOSVersion;
	}


	public void close() throws XmlRpcException {
		if (xmlRpcClient != null) {
			xmlRpcClient.close();						
		}
	}

}
