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
import org.piwigo.remotesync.api.response.PwgImagesSetInfoResponse;

/**
Changes properties of an image.
<br><b>single_value_mode</b> can be "fill_if_empty" (only use the input value if the corresponding values is currently empty) or "replace"
(overwrite any existing value) and applies to single values properties like name/author/date_creation/comment.
<br><b>multiple_value_mode</b> can be "append" (no change on existing values, add the new values) or "replace" and applies to multiple values properties like tag_ids/categories.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesSetInfoRequest extends AbstractRequest<PwgImagesSetInfoResponse> {

	protected PwgImagesSetInfoRequest() {}

	public PwgImagesSetInfoRequest(Integer image_id) {
		setImageId(image_id);
	}

 	protected PwgImagesSetInfoRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

 	public PwgImagesSetInfoRequest setFile(String file) {
		addParameterValue("file", org.piwigo.remotesync.api.type.Type.MIXED, null, file);
		return this;
	}	

 	public PwgImagesSetInfoRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

 	public PwgImagesSetInfoRequest setAuthor(String author) {
		addParameterValue("author", org.piwigo.remotesync.api.type.Type.MIXED, null, author);
		return this;
	}	

 	public PwgImagesSetInfoRequest setDateCreation(String date_creation) {
		addParameterValue("date_creation", org.piwigo.remotesync.api.type.Type.MIXED, null, date_creation);
		return this;
	}	

 	public PwgImagesSetInfoRequest setComment(String comment) {
		addParameterValue("comment", org.piwigo.remotesync.api.type.Type.MIXED, null, comment);
		return this;
	}	

	//info : String list "category_id[,rank];category_id[,rank]".<br>The rank is optional and is equivalent to "auto" if not given.
 	public PwgImagesSetInfoRequest setCategories(String categories) {
		addParameterValue("categories", org.piwigo.remotesync.api.type.Type.MIXED, null, categories);
		return this;
	}	

	//info : Comma separated ids
 	public PwgImagesSetInfoRequest setTagIds(Integer tag_ids) {
		addParameterValue("tag_ids", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, tag_ids);
		return this;
	}	

	//maxValue : 8
 	public PwgImagesSetInfoRequest setLevel(Integer level) {
		addParameterValue("level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 8, level);
		return this;
	}	

	//defaultValue : fill_if_empty
 	public PwgImagesSetInfoRequest setSingleValueMode(String single_value_mode) {
		addParameterValue("single_value_mode", org.piwigo.remotesync.api.type.Type.MIXED, null, single_value_mode);
		return this;
	}	

	//defaultValue : append
 	public PwgImagesSetInfoRequest setMultipleValueMode(String multiple_value_mode) {
		addParameterValue("multiple_value_mode", org.piwigo.remotesync.api.type.Type.MIXED, null, multiple_value_mode);
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
		return "pwg.images.setInfo";
	}

	public Class<PwgImagesSetInfoResponse> getReturnType() {
		return PwgImagesSetInfoResponse.class;
	}
}
