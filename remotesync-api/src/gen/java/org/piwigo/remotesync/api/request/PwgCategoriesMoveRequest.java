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
import org.piwigo.remotesync.api.response.PwgCategoriesMoveResponse;

/**
Move album(s).
<br>Set parent as 0 to move to gallery root. Only virtual categories can be moved.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesMoveRequest extends AbstractRequest<PwgCategoriesMoveResponse> {

	protected PwgCategoriesMoveRequest() {}

	public PwgCategoriesMoveRequest(Integer parent, Integer category_id) {
		setParent(parent);
		setCategoryId(category_id);
	}

	public PwgCategoriesMoveRequest(Integer parent, Integer... category_id) {
		setParent(parent);
		setCategoryId(java.util.Arrays.asList(category_id));
	}

	public PwgCategoriesMoveRequest(Integer parent, java.util.List<Integer> category_idList) {
		setParent(parent);
		setCategoryId(category_idList);
	}

 	protected PwgCategoriesMoveRequest setCategoryId(Integer category_id) {
		addParameterValue("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category_id);
		return this;
	}	

	protected PwgCategoriesMoveRequest setCategoryId(Integer... category_id) {
		addParameterValueList("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(category_id));
		return this;
	}

	protected PwgCategoriesMoveRequest setCategoryId(java.util.List<Integer> category_idList) {
		addParameterValueList("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category_idList);
		return this;
	}

 	protected PwgCategoriesMoveRequest setParent(Integer parent) {
		addParameterValue("parent", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, parent);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return true;
	}

	public boolean isAdminOnly() {
		return true;
	};

	public boolean isPostOnly() {
		return true;
	};

	public String getWSMethodName() {
		return "pwg.categories.move";
	}

	public Class<PwgCategoriesMoveResponse> getReturnType() {
		return PwgCategoriesMoveResponse.class;
	}
}
