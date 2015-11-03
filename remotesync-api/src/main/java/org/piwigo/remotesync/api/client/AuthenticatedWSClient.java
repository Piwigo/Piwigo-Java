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
package org.piwigo.remotesync.api.client;

import org.piwigo.remotesync.api.IClientConfiguration;
import org.piwigo.remotesync.api.exception.ClientException;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.request.PwgSessionGetStatusRequest;
import org.piwigo.remotesync.api.request.PwgSessionLoginRequest;
import org.piwigo.remotesync.api.request.PwgSessionLogoutRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.PwgSessionGetStatusResponse;

//TODO handle session timeout
public class AuthenticatedWSClient extends WSClient {

	private PwgSessionGetStatusResponse sessionGetStatusResponse;

	public AuthenticatedWSClient(IClientConfiguration clientConfiguration) {
		super(clientConfiguration);
	}

	@Override
	protected <T extends BasicResponse> void checkRequestAuthorization(AbstractRequest<T> request) throws ClientServerException {
		if (request.isAdminOnly() || request.isNeedPwgToken()) {
			if (sessionGetStatusResponse == null)
				getSessionStatus();
			if (request.isAdminOnly() && !sessionGetStatusResponse.isAdmin())
				throw new ClientException("Not logged in as Admin/Webmaster");
			if (request.isNeedPwgToken())
				request.setPwgToken(sessionGetStatusResponse.pwg_token);
		}
	}

	private void getSessionStatus() throws ClientServerException {
		sessionGetStatusResponse = doSendRequest(new PwgSessionGetStatusRequest());
	}

	@Override
	public AuthenticatedWSClient login() throws ClientServerException {
		doSendRequest(new PwgSessionLoginRequest(clientConfiguration.getUsername(), clientConfiguration.getPassword()));
		return this;
	}

	@Override
	public AuthenticatedWSClient logout() throws ClientServerException {
		doSendRequest(new PwgSessionLogoutRequest());
		return this;
	}

}
