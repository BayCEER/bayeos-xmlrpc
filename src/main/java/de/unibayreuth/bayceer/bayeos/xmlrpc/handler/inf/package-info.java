/**
 * Provides the interface classes necessary to access the xml-rpc handlers
 *   
 * All handler interfaces are defined by this package. There are secured and unsecured handlers.
 * Secured handler use http authentication.
 * The handler name can be calculated by the interface name omitting the first character.
 * e.g. ILoginHandler -&gt; LoginHandler
 * 
 * @author Oliver Archner
 * @since 1.6.5
 * 
 * 
 */
package de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf;
