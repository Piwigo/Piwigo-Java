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
import org.piwigo.remotesync.api.response.PwgImagesGetInfoResponse;

/**
Returns information about an image.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesGetInfoRequest extends AbstractRequest<PwgImagesGetInfoResponse> {

	protected PwgImagesGetInfoRequest() {}

	public PwgImagesGetInfoRequest(Integer image_id) {
		setImageId(image_id);
	}

 	protected PwgImagesGetInfoRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

	//defaultValue : 0
 	public PwgImagesGetInfoRequest setCommentsPage(Integer comments_page) {
		addParameterValue("comments_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, comments_page);
		return this;
	}	

	//defaultValue : 10
	//maxValue : 20
 	public PwgImagesGetInfoRequest setCommentsPerPage(Integer comments_per_page) {
		addParameterValue("comments_per_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 20, comments_per_page);
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
		return "pwg.images.getInfo";
	}

	public Class<PwgImagesGetInfoResponse> getReturnType() {
		return PwgImagesGetInfoResponse.class;
	}
}
