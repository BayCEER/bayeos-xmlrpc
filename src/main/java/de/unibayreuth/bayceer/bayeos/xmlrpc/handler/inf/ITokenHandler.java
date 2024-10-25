/*******************************************************************************
 * Copyright (c) 2024 University of Bayreuth - BayCEER.
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

public interface ITokenHandler {
	
	/**
	 * @return JWT 
	 * @throws XmlRpcException
	 * @since 2.1.0
	 * @see ILoginHandler
	 * @see https://datatracker.ietf.org/doc/html/rfc7519
	 */
	public String createLoginToken() throws XmlRpcException;

}
