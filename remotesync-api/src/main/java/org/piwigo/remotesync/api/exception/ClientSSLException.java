/*******************************************************************************
 * Copyright (c) 2014 Matthieu Helleboid.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Matthieu Helleboid - initial API and implementation
 ******************************************************************************/
package org.piwigo.remotesync.api.exception;

import javax.net.ssl.SSLException;


public class ClientSSLException extends ClientException {

	private static final long serialVersionUID = -2534675861595722310L;

	public ClientSSLException(String message, SSLException exception) {
		super(message, exception);
	}
}
