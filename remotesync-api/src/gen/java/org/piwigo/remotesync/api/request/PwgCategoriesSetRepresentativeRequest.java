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
import org.piwigo.remotesync.api.response.PwgCategoriesSetRepresentativeResponse;

/**
Sets the representative photo for an album. The photo doesn't have to belong to the album.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesSetRepresentativeRequest extends AbstractRequest<PwgCategoriesSetRepresentativeResponse> {

	protected PwgCategoriesSetRepresentativeRequest() {}

	public PwgCategoriesSetRepresentativeRequest(Integer category_id, Integer image_id) {
		setCategoryId(category_id);
		setImageId(image_id);
	}

 	protected PwgCategoriesSetRepresentativeRequest setCategoryId(Integer category_id) {
		addParameterValue("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category_id);
		return this;
	}	

 	protected PwgCategoriesSetRepresentativeRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
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
		return "pwg.categories.setRepresentative";
	}

	public Class<PwgCategoriesSetRepresentativeResponse> getReturnType() {
		return PwgCategoriesSetRepresentativeResponse.class;
	}
}
