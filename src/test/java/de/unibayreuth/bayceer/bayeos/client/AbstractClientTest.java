package de.unibayreuth.bayceer.bayeos.client;


import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;

import de.unibayreuth.bayceer.bayeos.client.Client;

public abstract class AbstractClientTest extends TestCase {

	public Client cli;

	Properties p = new Properties();

	public void setUp() throws XmlRpcException, IOException {

		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		p.load(getClass().getResourceAsStream("/test.properties"));
		String url = p.getProperty("bayeos.url");
		cli = Client.getInstance();
		Client.getInstance().connect(url, p.getProperty("bayeos.user.name"), p.getProperty("bayeos.user.password"));
	}

	public void tearDown() throws XmlRpcException {
		cli.close();
		BasicConfigurator.resetConfiguration();
	}

}
