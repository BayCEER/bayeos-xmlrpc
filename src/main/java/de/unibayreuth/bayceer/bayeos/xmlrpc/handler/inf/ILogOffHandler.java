/*******************************************************************************
 * Copyright (c) 2011 University of Bayreuth - BayCEER.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     University of Bayreuth - BayCEER - initial API and implementation
 ******************************************************************************/
package de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf;

import org.apache.xmlrpc.XmlRpcException;

/**
 * Session Handler (secured)
 * @author oliver
 *
 */
public interface ILogOffHandler {

	/** Terminate a user session by sessionId and userId transfered by authentication header 
	 * @return success
	 * @throws XmlRpcException
	 * @see  ILoginHandler
	 * 
	 */
	public Boolean terminateSession() throws XmlRpcException;
		

}
