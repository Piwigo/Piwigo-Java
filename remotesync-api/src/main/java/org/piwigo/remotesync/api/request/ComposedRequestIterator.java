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

import java.util.Iterator;
import java.util.List;

import org.piwigo.remotesync.api.response.BasicResponse;

public class ComposedRequestIterator implements Iterator<AbstractRequest<? extends BasicResponse>> {

	private List<AbstractRequest<? extends BasicResponse>> requests;

	private int index;

	public ComposedRequestIterator(List<AbstractRequest<? extends BasicResponse>> requests) {
		this.requests = requests;
	}

	@Override
	public boolean hasNext() {
		return index < requests.size();
	}

	@Override
	public AbstractRequest<? extends BasicResponse> next() {
		return requests.get(index++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
