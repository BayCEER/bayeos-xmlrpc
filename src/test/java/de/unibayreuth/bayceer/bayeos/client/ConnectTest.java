package de.unibayreuth.bayceer.bayeos.client;

import java.io.IOException;
import java.util.Properties;

import org.apache.xmlrpc.XmlRpcException;
import junit.framework.TestCase;

public class ConnectTest  extends TestCase{
	 
	
	String url;
	Properties p;
	String userName;
	String userPassword;
	
	public void setUp() throws IOException{
		
		
		p = new Properties();		
		p.load(getClass().getResourceAsStream("/test.properties"));
													
		url = p.getProperty("bayeos.url");
		System.out.println("URL:" + url);		
		userName = p.getProperty("bayeos.user.name");	
		userPassword = p.getProperty("bayeos.user.password");		
	}
	
	
	
	
	public void tearDown() throws XmlRpcException {							
	}
	
	public void testWrongUser(){		
		Client c =Client.getInstance();		
	    try {
			c.connect(url,"asdf","false");
			fail();
		} catch (XmlRpcException e) {
			System.out.println(e.getMessage());
		} 
		
	}
	
	
	public void testWrongPassWord(){		
		Client c =Client.getInstance();		
	    try {
			c.connect(url,userName,"wrong");
			fail();
		} catch (XmlRpcException e) {
			System.out.println(e.getMessage());
		} 
		
	}
	
	
	public void testEmptyPassWord(){		
		Client c =Client.getInstance();		
	    try {
			c.connect(url,userName,"");
			fail();
		} catch (XmlRpcException e) {
			System.out.println(e.getMessage());
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
