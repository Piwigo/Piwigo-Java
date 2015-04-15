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
import org.piwigo.remotesync.api.response.PwgImagesAddFileResponse;

/**
Add or update a file for an existing photo.
<br>pwg.images.addChunk must have been called before (maybe several times).
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesAddFileRequest extends AbstractRequest<PwgImagesAddFileResponse> {

	protected PwgImagesAddFileRequest() {}

	public PwgImagesAddFileRequest(Integer image_id, String sum) {
		setImageId(image_id);
		setSum(sum);
	}

 	protected PwgImagesAddFileRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

	//info : Must be "file", for backward compatiblity "high" and "thumb" are allowed.
	//defaultValue : file
 	public PwgImagesAddFileRequest setType(String type) {
		addParameterValue("type", org.piwigo.remotesync.api.type.Type.MIXED, null, type);
		return this;
	}	

 	protected PwgImagesAddFileRequest setSum(String sum) {
		addParameterValue("sum", org.piwigo.remotesync.api.type.Type.MIXED, null, sum);
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
		return "pwg.images.addFile";
	}

	public Class<PwgImagesAddFileResponse> getReturnType() {
		return PwgImagesAddFileResponse.class;
	}
}
