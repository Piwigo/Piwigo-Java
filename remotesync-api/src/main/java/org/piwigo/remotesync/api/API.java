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
package org.piwigo.remotesync.api;

import org.piwigo.remotesync.api.client.AuthenticatedWSClient;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.PwgImagesAddAllChunksRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddFileWithChunkRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddWithChunkRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ComposedResponse;
import org.piwigo.remotesync.api.response.PwgImagesAddFileResponse;
import org.piwigo.remotesync.api.response.PwgImagesAddResponse;

public class API extends AbstractAPI {

	protected IClient client;

	public API(IClient client) {
		this.client = client;
	}

	public API(IClientConfiguration clientConfiguration) {
		this.client = new AuthenticatedWSClient(clientConfiguration);
	}
	
	public void login() throws ClientServerException {
		client.login();
	}

	public void logout() throws ClientServerException {
		client.logout();
	}

	@Override
	protected IClient getClient() {
		return client;
	}
	
	public BasicResponse imagesAddAllChunksRequest(PwgImagesAddAllChunksRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public ComposedResponse<PwgImagesAddFileResponse> imagesAddFileWithChunkRequest(PwgImagesAddFileWithChunkRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public ComposedResponse<PwgImagesAddResponse> imagesAddWithChunkRequest(PwgImagesAddWithChunkRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}


}
