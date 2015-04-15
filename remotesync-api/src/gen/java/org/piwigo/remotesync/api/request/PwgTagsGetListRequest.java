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

import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.response.PwgTagsGetListResponse;

/**
Retrieves a list of available tags.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgTagsGetListRequest extends AbstractRequest<PwgTagsGetListResponse> {

	public PwgTagsGetListRequest() {
	}

	//defaultValue : 0
 	public PwgTagsGetListRequest setSortByCounter(Boolean sort_by_counter) {
		addParameterValue("sort_by_counter", org.piwigo.remotesync.api.type.Type.BOOL, null, sort_by_counter);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return false;
	};

	public boolean isPostOnly() {
		return false;
	};

	public String getWSMethodName() {
		return "pwg.tags.getList";
	}

	public Class<PwgTagsGetListResponse> getReturnType() {
		return PwgTagsGetListResponse.class;
	}
}
