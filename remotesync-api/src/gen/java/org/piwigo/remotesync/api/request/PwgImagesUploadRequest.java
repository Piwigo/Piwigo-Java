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
import org.piwigo.remotesync.api.response.PwgImagesUploadResponse;

/**
Add an image.
<br>Use the <b>$_FILES[image]</b> field for uploading file.
<br>Set the form encoding to "form-data".
<br>You can update an existing photo if you define an existing image_id.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesUploadRequest extends AbstractRequest<PwgImagesUploadResponse> {

	public PwgImagesUploadRequest(java.io.File image) {
		setName(image.getName());
		setFile(image);
	}

	public PwgImagesUploadRequest setCategory(Integer category) {
		addParameterValue("category", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category);
		return this;
	}

	public PwgImagesUploadRequest setCategory(Integer... category) {
		addParameterValueList("category", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null,
				java.util.Arrays.asList(category));
		return this;
	}

	public PwgImagesUploadRequest setCategory(java.util.List<Integer> categoryList) {
		addParameterValueList("category", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, categoryList);
		return this;
	}

	public PwgImagesUploadRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}

	// defaultValue : 0
	// maxValue : 8
	public PwgImagesUploadRequest setLevel(Integer level) {
		addParameterValue("level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 8, level);
		return this;
	}

	protected PwgImagesUploadRequest setFile(java.io.File image) {
		addParameterValue("file", org.piwigo.remotesync.api.type.Type.FILE, null, image);
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
		return "pwg.images.upload";
	}

	public Class<PwgImagesUploadResponse> getReturnType() {
		return PwgImagesUploadResponse.class;
	}
}
