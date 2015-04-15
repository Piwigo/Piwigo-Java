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
import org.piwigo.remotesync.api.response.PwgCategoriesDeleteResponse;

/**
Deletes album(s).
<br><b>photo_deletion_mode</b> can be "no_delete" (may create orphan photos), "delete_orphans"
(default mode, only deletes photos linked to no other album) or "force_delete" (delete all photos, even those linked to other albums)
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesDeleteRequest extends AbstractRequest<PwgCategoriesDeleteResponse> {

	protected PwgCategoriesDeleteRequest() {}

	public PwgCategoriesDeleteRequest(Integer category_id) {
		setCategoryId(category_id);
	}

	public PwgCategoriesDeleteRequest(Integer... category_id) {
		setCategoryId(java.util.Arrays.asList(category_id));
	}

	public PwgCategoriesDeleteRequest(java.util.List<Integer> category_idList) {
		setCategoryId(category_idList);
	}

 	protected PwgCategoriesDeleteRequest setCategoryId(Integer category_id) {
		addParameterValue("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category_id);
		return this;
	}	

	protected PwgCategoriesDeleteRequest setCategoryId(Integer... category_id) {
		addParameterValueList("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(category_id));
		return this;
	}

	protected PwgCategoriesDeleteRequest setCategoryId(java.util.List<Integer> category_idList) {
		addParameterValueList("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category_idList);
		return this;
	}

	//defaultValue : delete_orphans
 	public PwgCategoriesDeleteRequest setPhotoDeletionMode(String photo_deletion_mode) {
		addParameterValue("photo_deletion_mode", org.piwigo.remotesync.api.type.Type.MIXED, null, photo_deletion_mode);
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
		return "pwg.categories.delete";
	}

	public Class<PwgCategoriesDeleteResponse> getReturnType() {
		return PwgCategoriesDeleteResponse.class;
	}
}
