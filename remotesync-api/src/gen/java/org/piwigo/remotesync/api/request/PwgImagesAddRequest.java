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
import org.piwigo.remotesync.api.response.PwgImagesAddResponse;

/**
Add an image.
<br>pwg.images.addChunk must have been called before (maybe several times).
<br>Don't use "thumbnail_sum" and "high_sum", these parameters are here for backward compatibility.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesAddRequest extends AbstractRequest<PwgImagesAddResponse> {

	protected PwgImagesAddRequest() {}

	public PwgImagesAddRequest(String original_sum) {
		setOriginalSum(original_sum);
	}

 	public PwgImagesAddRequest setThumbnailSum(String thumbnail_sum) {
		addParameterValue("thumbnail_sum", org.piwigo.remotesync.api.type.Type.MIXED, null, thumbnail_sum);
		return this;
	}	

 	public PwgImagesAddRequest setHighSum(String high_sum) {
		addParameterValue("high_sum", org.piwigo.remotesync.api.type.Type.MIXED, null, high_sum);
		return this;
	}	

 	protected PwgImagesAddRequest setOriginalSum(String original_sum) {
		addParameterValue("original_sum", org.piwigo.remotesync.api.type.Type.MIXED, null, original_sum);
		return this;
	}	

 	public PwgImagesAddRequest setOriginalFilename(String original_filename) {
		addParameterValue("original_filename", org.piwigo.remotesync.api.type.Type.MIXED, null, original_filename);
		return this;
	}	

 	public PwgImagesAddRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

 	public PwgImagesAddRequest setAuthor(String author) {
		addParameterValue("author", org.piwigo.remotesync.api.type.Type.MIXED, null, author);
		return this;
	}	

 	public PwgImagesAddRequest setDateCreation(String date_creation) {
		addParameterValue("date_creation", org.piwigo.remotesync.api.type.Type.MIXED, null, date_creation);
		return this;
	}	

 	public PwgImagesAddRequest setComment(String comment) {
		addParameterValue("comment", org.piwigo.remotesync.api.type.Type.MIXED, null, comment);
		return this;
	}	

	//info : String list "category_id[,rank];category_id[,rank]".<br>The rank is optional and is equivalent to "auto" if not given.
 	public PwgImagesAddRequest setCategories(String categories) {
		addParameterValue("categories", org.piwigo.remotesync.api.type.Type.MIXED, null, categories);
		return this;
	}	

	//info : Comma separated ids
 	public PwgImagesAddRequest setTagIds(Integer tag_ids) {
		addParameterValue("tag_ids", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, tag_ids);
		return this;
	}	

	//defaultValue : 0
	//maxValue : 8
 	public PwgImagesAddRequest setLevel(Integer level) {
		addParameterValue("level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 8, level);
		return this;
	}	

	//defaultValue : 1
 	public PwgImagesAddRequest setCheckUniqueness(Boolean check_uniqueness) {
		addParameterValue("check_uniqueness", org.piwigo.remotesync.api.type.Type.BOOL, null, check_uniqueness);
		return this;
	}	

 	public PwgImagesAddRequest setImageId(Integer image_id) {
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
		return false;
	};

	public String getWSMethodName() {
		return "pwg.images.add";
	}

	public Class<PwgImagesAddResponse> getReturnType() {
		return PwgImagesAddResponse.class;
	}
}
