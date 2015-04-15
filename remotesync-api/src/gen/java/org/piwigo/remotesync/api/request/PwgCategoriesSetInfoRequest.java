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
import org.piwigo.remotesync.api.response.PwgCategoriesSetInfoResponse;

/**
Changes properties of an album.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesSetInfoRequest extends AbstractRequest<PwgCategoriesSetInfoResponse> {

	protected PwgCategoriesSetInfoRequest() {}

	public PwgCategoriesSetInfoRequest(Integer category_id) {
		setCategoryId(category_id);
	}

 	protected PwgCategoriesSetInfoRequest setCategoryId(Integer category_id) {
		addParameterValue("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category_id);
		return this;
	}	

 	public PwgCategoriesSetInfoRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

 	public PwgCategoriesSetInfoRequest setComment(String comment) {
		addParameterValue("comment", org.piwigo.remotesync.api.type.Type.MIXED, null, comment);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return true;
	};

	public boolean isPostOnly() {
		return true;
	};

	public String getWSMethodName() {
		return "pwg.categories.setInfo";
	}

	public Class<PwgCategoriesSetInfoResponse> getReturnType() {
		return PwgCategoriesSetInfoResponse.class;
	}
}
