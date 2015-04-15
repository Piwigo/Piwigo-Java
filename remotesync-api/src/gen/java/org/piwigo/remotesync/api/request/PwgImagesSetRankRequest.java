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
import org.piwigo.remotesync.api.response.PwgImagesSetRankResponse;

/**
Sets the rank of a photo for a given album.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesSetRankRequest extends AbstractRequest<PwgImagesSetRankResponse> {

	protected PwgImagesSetRankRequest() {}

	public PwgImagesSetRankRequest(Integer image_id, Integer category_id, Integer rank) {
		setImageId(image_id);
		setCategoryId(category_id);
		setRank(rank);
	}

 	protected PwgImagesSetRankRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

 	protected PwgImagesSetRankRequest setCategoryId(Integer category_id) {
		addParameterValue("category_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, category_id);
		return this;
	}	

 	protected PwgImagesSetRankRequest setRank(Integer rank) {
		addParameterValue("rank", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, rank);
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
		return "pwg.images.setRank";
	}

	public Class<PwgImagesSetRankResponse> getReturnType() {
		return PwgImagesSetRankResponse.class;
	}
}
