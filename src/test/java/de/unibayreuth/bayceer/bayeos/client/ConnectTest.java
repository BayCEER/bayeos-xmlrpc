package de.unibayreuth.bayceer.bayeos.client;

import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;

public class ConnectTest  extends TestCase{
	private static Logger log; 
	
	String url;
	Properties p;
	String userName;
	String userPassword;
	
	public void setUp() throws IOException{
		BasicConfigurator.configure();
		log = Logger.getRootLogger();
		log.setLevel(Level.DEBUG);
		
		p = new Properties();		
		p.load(getClass().getResourceAsStream("/test.properties"));
													
		url = p.getProperty("bayeos.url");
		log.debug("URL:" + url);		
		userName = p.getProperty("bayeos.user.name");	
		userPassword = p.getProperty("bayeos.user.password");		
	}
	
	
	
	
	public void tearDown() throws XmlRpcException {							
		BasicConfigurator.resetConfiguration();
	}
	
	public void testWrongUser(){		
		Client c =Client.getInstance();		
	    try {
			c.connect(url,"asdf","false");
			fail();
		} catch (XmlRpcException e) {
			log.error(e.getMessage());
		} 
		
	}
	
	
	public void testWrongPassWord(){		
		Client c =Client.getInstance();		
	    try {
			c.connect(url,userName,"wrong");
			fail();
		} catch (XmlRpcException e) {
			log.error(e.getMessage());
		} 
		
	}
	
	
	public void testEmptyPassWord(){		
		Client c =Client.getInstance();		
	    try {
			c.connect(url,userName,"");
			fail();
		} catch (XmlRpcException e) {
			log.error(e.getMessage());
		} 
		
	}

	
	public void testDefaultPassWord(){		
		Client c =Client.getInstance();		
	    try {
			c.connect(url,userName,userPassword);			
		} catch (XmlRpcException e) {			
			fail();
		} finally {
			try {
				c.close();
			} catch (XmlRpcException e) {				
				fail(e.getMessage());
			}
		}
		
	}


}
