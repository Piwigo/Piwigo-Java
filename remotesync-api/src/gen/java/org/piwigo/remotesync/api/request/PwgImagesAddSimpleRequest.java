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
import org.piwigo.remotesync.api.response.PwgImagesAddSimpleResponse;

/**
Add an image.
<br>Use the <b>$_FILES[image]</b> field for uploading file.
<br>Set the form encoding to "form-data".
<br>You can update an existing photo if you define an existing image_id.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesAddSimpleRequest extends AbstractRequest<PwgImagesAddSimpleResponse> {

	protected PwgImagesAddSimpleRequest() {}

	public PwgImagesAddSimpleRequest(java.io.File image) {
		setImage(image);
		setName(image.getName());
	}

 	public PwgImagesAddSimpleRequest setCategory(Integer category) {
		addParameterValue("category", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category);
		return this;
	}	

	public PwgImagesAddSimpleRequest setCategory(Integer... category) {
		addParameterValueList("category", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(category));
		return this;
	}

	public PwgImagesAddSimpleRequest setCategory(java.util.List<Integer> categoryList) {
		addParameterValueList("category", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, categoryList);
		return this;
	}

 	public PwgImagesAddSimpleRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

 	public PwgImagesAddSimpleRequest setAuthor(String author) {
		addParameterValue("author", org.piwigo.remotesync.api.type.Type.MIXED, null, author);
		return this;
	}	

 	public PwgImagesAddSimpleRequest setComment(String comment) {
		addParameterValue("comment", org.piwigo.remotesync.api.type.Type.MIXED, null, comment);
		return this;
	}	

	//defaultValue : 0
	//maxValue : 8
 	public PwgImagesAddSimpleRequest setLevel(Integer level) {
		addParameterValue("level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 8, level);
		return this;
	}	

 	public PwgImagesAddSimpleRequest setTags(String tags) {
		addParameterValue("tags", org.piwigo.remotesync.api.type.Type.MIXED, null, tags);
		return this;
	}	

	public PwgImagesAddSimpleRequest setTags(String... tags) {
		addParameterValueList("tags", org.piwigo.remotesync.api.type.Type.MIXED, null, java.util.Arrays.asList(tags));
		return this;
	}

	public PwgImagesAddSimpleRequest setTags(java.util.List<String> tagsList) {
		addParameterValueList("tags", org.piwigo.remotesync.api.type.Type.MIXED, null, tagsList);
		return this;
	}

 	public PwgImagesAddSimpleRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

 	protected PwgImagesAddSimpleRequest setImage(java.io.File image) {
		addParameterValue("image", org.piwigo.remotesync.api.type.Type.FILE, null, image);
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
		return "pwg.images.addSimple";
	}

	public Class<PwgImagesAddSimpleResponse> getReturnType() {
		return PwgImagesAddSimpleResponse.class;
	}
}
