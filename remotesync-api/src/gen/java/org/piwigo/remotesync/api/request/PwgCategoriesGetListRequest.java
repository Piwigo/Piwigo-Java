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
import org.piwigo.remotesync.api.response.PwgCategoriesGetListResponse;

/**
Returns a list of categories.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesGetListRequest extends AbstractRequest<PwgCategoriesGetListResponse> {

	public PwgCategoriesGetListRequest() {
	}

	//info : Parent category. "0" or empty for root.
 	public PwgCategoriesGetListRequest setCatId(Integer cat_id) {
		addParameterValue("cat_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, cat_id);
		return this;
	}	

	//defaultValue : 0
 	public PwgCategoriesGetListRequest setRecursive(Boolean recursive) {
		addParameterValue("recursive", org.piwigo.remotesync.api.type.Type.BOOL, null, recursive);
		return this;
	}	

	//defaultValue : 0
 	public PwgCategoriesGetListRequest setPublic(Boolean _public) {
		addParameterValue("public", org.piwigo.remotesync.api.type.Type.BOOL, null, _public);
		return this;
	}	

	//defaultValue : 0
 	public PwgCategoriesGetListRequest setTreeOutput(Boolean tree_output) {
		addParameterValue("tree_output", org.piwigo.remotesync.api.type.Type.BOOL, null, tree_output);
		return this;
	}	

	//defaultValue : 0
 	public PwgCategoriesGetListRequest setFullname(Boolean fullname) {
		addParameterValue("fullname", org.piwigo.remotesync.api.type.Type.BOOL, null, fullname);
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
		return "pwg.categories.getList";
	}

	public Class<PwgCategoriesGetListResponse> getReturnType() {
		return PwgCategoriesGetListResponse.class;
	}
}
