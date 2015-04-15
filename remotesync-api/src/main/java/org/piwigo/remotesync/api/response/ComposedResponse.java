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
package org.piwigo.remotesync.api.response;

import java.util.ArrayList;
import java.util.List;

public class ComposedResponse<T extends BasicResponse> extends BasicResponse {

	protected List<BasicResponse> responses = new ArrayList<BasicResponse>();
	protected T response;

	public void addResponse(BasicResponse response) {
		responses.add(response);
	}

	public List<BasicResponse> getAllResponses() {
		return responses;
	}
	
	@SuppressWarnings("unchecked")
	public T getResponse() {
		return (T) responses.get(responses.size() - 1);
	}
}
