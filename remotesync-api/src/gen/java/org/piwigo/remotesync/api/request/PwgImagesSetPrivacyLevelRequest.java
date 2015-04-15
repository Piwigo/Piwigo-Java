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
import org.piwigo.remotesync.api.response.PwgImagesSetPrivacyLevelResponse;

/**
Sets the privacy levels for the images.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesSetPrivacyLevelRequest extends AbstractRequest<PwgImagesSetPrivacyLevelResponse> {

	protected PwgImagesSetPrivacyLevelRequest() {}

	public PwgImagesSetPrivacyLevelRequest(Integer level, Integer image_id) {
		setLevel(level);
		setImageId(image_id);
	}

	public PwgImagesSetPrivacyLevelRequest(Integer level, Integer... image_id) {
		setLevel(level);
		setImageId(java.util.Arrays.asList(image_id));
	}

	public PwgImagesSetPrivacyLevelRequest(Integer level, java.util.List<Integer> image_idList) {
		setLevel(level);
		setImageId(image_idList);
	}

 	protected PwgImagesSetPrivacyLevelRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

	protected PwgImagesSetPrivacyLevelRequest setImageId(Integer... image_id) {
		addParameterValueList("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(image_id));
		return this;
	}

	protected PwgImagesSetPrivacyLevelRequest setImageId(java.util.List<Integer> image_idList) {
		addParameterValueList("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_idList);
		return this;
	}

	//maxValue : 8
 	protected PwgImagesSetPrivacyLevelRequest setLevel(Integer level) {
		addParameterValue("level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 8, level);
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
		return "pwg.images.setPrivacyLevel";
	}

	public Class<PwgImagesSetPrivacyLevelResponse> getReturnType() {
		return PwgImagesSetPrivacyLevelResponse.class;
	}
}
