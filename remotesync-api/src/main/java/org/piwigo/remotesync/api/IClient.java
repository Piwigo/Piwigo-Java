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

import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.request.ComposedRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ComposedResponse;

public interface IClient {
	public <T extends BasicResponse> T sendRequest(AbstractRequest<T> request) throws ClientServerException;

	public <T extends BasicResponse> ComposedResponse<T> sendRequest(ComposedRequest<T> composedRequest) throws ClientServerException;

	public IClient login() throws ClientServerException;

	public IClient logout() throws ClientServerException;

}
