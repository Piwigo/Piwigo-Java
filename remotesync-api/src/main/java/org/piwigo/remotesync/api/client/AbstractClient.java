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

import java.util.Iterator;

import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.request.ComposedRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ComposedResponse;

public abstract class AbstractClient implements Client {

	@Override
	public final <T extends BasicResponse> T sendRequest(AbstractRequest<T> request) throws ClientServerException {
		return doSendRequest(request);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public final <T extends BasicResponse> ComposedResponse<T> sendRequest(ComposedRequest<T> composedRequest) throws ClientServerException {
		ComposedResponse<T> composedResponse = new ComposedResponse<T>();
		Iterator<AbstractRequest<? extends BasicResponse>> iterator = composedRequest.iterator();
		while (iterator.hasNext()) {
			AbstractRequest<? extends BasicResponse> next = iterator.next();
			if (next instanceof ComposedRequest)
				composedResponse.addResponse(sendRequest((ComposedRequest) next));
			else
				composedResponse.addResponse(sendRequest(next));
		}
		return composedResponse;
	}

	protected abstract <T extends BasicResponse> T doSendRequest(AbstractRequest<T> request) throws ClientServerException;
}
