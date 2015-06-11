package de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf;

import java.util.Hashtable;

import org.apache.xmlrpc.XmlRpcException;

public interface IPreferenceHandler {
	
	/**  
	 *  Returns a map of preferences for a user and his application 
	 * 
	 *   
	 * @param application as Integer 
	 * @return Hashtable<String,String>   	 						 
	 *   
	 * @throws XmlRpcException
	 * @since 1.9.3
	 */
    public Hashtable<String, String> getPreferences(String application) throws XmlRpcException;
    
    
    
    /**  
	 *  Set preference value for user and application   
	 * 
	 * @param applicaton 
	 * @param key: not null
	 * @param value: if value is not null then add or overwrite value for key else delete key  
	 * @return Boolean
	 *   
	 * @throws XmlRpcException
	 * @since 1.9.3
	 */
    public Boolean setPreference(String application,String key, String value) throws XmlRpcException;
    
    
    /**
     * Delete all key value pairs entries for application
     * @param application
     * @return Boolean 
     * @throws XmlRpcException
     * @since 1.9.5
     */
    public Boolean deletePreferences(String application) throws XmlRpcException;
        
    
 

}
