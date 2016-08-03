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


public class ClientRedirectException extends ClientException {

	private static final long serialVersionUID = -4061416823576651051L;

	private String destination;

	public ClientRedirectException(String message) {
		super(message);
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}
}