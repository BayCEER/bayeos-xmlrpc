package de.unibayreuth.bayceer.bayeos.client;


import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;


import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractClientTest extends TestCase {

	public Client cli;

	Properties p = new Properties();
	
	Logger logger = LoggerFactory.getLogger(AbstractClientTest.class);

	public void setUp() throws XmlRpcException, IOException {

		p.load(getClass().getResourceAsStream("/test.properties"));
		String url = p.getProperty("bayeos.url");
		cli = Client.getInstance();
		cli.connect(url, p.getProperty("bayeos.user.name"), p.getProperty("bayeos.user.password"));
	}

	public void tearDown() throws XmlRpcException {
		cli.close();
	}

}
