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

public class ClientServerException extends Exception {

	private static final long serialVersionUID = -1982197686506297340L;

	public ClientServerException(String errorMessage) {
		super(errorMessage);
	}

	public ClientServerException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}

}
