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
import org.piwigo.remotesync.api.response.PwgImagesRateResponse;

/**
Rates an image.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesRateRequest extends AbstractRequest<PwgImagesRateResponse> {

	protected PwgImagesRateRequest() {}

	public PwgImagesRateRequest(Integer image_id, Float rate) {
		setImageId(image_id);
		setRate(rate);
	}

 	protected PwgImagesRateRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

 	protected PwgImagesRateRequest setRate(Float rate) {
		addParameterValue("rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, rate);
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
		return "pwg.images.rate";
	}

	public Class<PwgImagesRateResponse> getReturnType() {
		return PwgImagesRateResponse.class;
	}
}
