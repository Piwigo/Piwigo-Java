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
import org.piwigo.remotesync.api.response.PwgImagesCheckFilesResponse;

/**
Checks if you have updated version of your files for a given photo, the answer can be "missing", "equals" or "differs".
<br>Don't use "thumbnail_sum" and "high_sum", these parameters are here for backward compatibility.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesCheckFilesRequest extends AbstractRequest<PwgImagesCheckFilesResponse> {

	protected PwgImagesCheckFilesRequest() {}

	public PwgImagesCheckFilesRequest(Integer image_id) {
		setImageId(image_id);
	}

 	protected PwgImagesCheckFilesRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

 	public PwgImagesCheckFilesRequest setFileSum(String file_sum) {
		addParameterValue("file_sum", org.piwigo.remotesync.api.type.Type.MIXED, null, file_sum);
		return this;
	}	

 	public PwgImagesCheckFilesRequest setThumbnailSum(String thumbnail_sum) {
		addParameterValue("thumbnail_sum", org.piwigo.remotesync.api.type.Type.MIXED, null, thumbnail_sum);
		return this;
	}	

 	public PwgImagesCheckFilesRequest setHighSum(String high_sum) {
		addParameterValue("high_sum", org.piwigo.remotesync.api.type.Type.MIXED, null, high_sum);
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
		return "pwg.images.checkFiles";
	}

	public Class<PwgImagesCheckFilesResponse> getReturnType() {
		return PwgImagesCheckFilesResponse.class;
	}
}
