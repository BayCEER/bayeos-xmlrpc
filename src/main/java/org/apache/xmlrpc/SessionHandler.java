package org.apache.xmlrpc;



public interface SessionHandler {
	
	void checkSession(Integer SessionId, Integer UserId) throws InvalidSessionException;
	
}
