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
package org.piwigo.remotesync.api.request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ComposedResponse;

public class ComposedRequest<T extends BasicResponse> extends AbstractRequest<ComposedResponse<T>> implements Iterable<AbstractRequest<? extends BasicResponse>> {

	protected List<AbstractRequest<? extends BasicResponse>> requests = new ArrayList<AbstractRequest<? extends BasicResponse>>();

	public void addRequest(AbstractRequest<? extends BasicResponse> request) {
		requests.add(request);
	}
	
	@Override
	public Iterator<AbstractRequest<? extends BasicResponse>> iterator() {
		return new ComposedRequestIterator(requests);
	}
	
	@Override
	public String getWSMethodName() {
		throw new IllegalStateException("ComposedRequest are not direct piwigo request");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getReturnType() {
		throw new IllegalStateException("ComposedRequest are not direct piwigo request");
	}
	
	@Override
	public boolean isAdminOnly() {
		throw new IllegalStateException("ComposedRequest are not direct piwigo request");
	}

	@Override
	public boolean isPostOnly() {
		throw new IllegalStateException("ComposedRequest are not direct piwigo request");
	}

	@Override
	public boolean isNeedPwgToken() {
		throw new IllegalStateException("ComposedRequest are not direct piwigo request");
	}
}
