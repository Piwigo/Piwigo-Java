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
import org.piwigo.remotesync.api.response.PwgRatesDeleteResponse;

/**
Deletes all rates for a user.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgRatesDeleteRequest extends AbstractRequest<PwgRatesDeleteResponse> {

	protected PwgRatesDeleteRequest() {}

	public PwgRatesDeleteRequest(Integer user_id) {
		setUserId(user_id);
	}

 	protected PwgRatesDeleteRequest setUserId(Integer user_id) {
		addParameterValue("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_id);
		return this;
	}	

 	public PwgRatesDeleteRequest setAnonymousId(String anonymous_id) {
		addParameterValue("anonymous_id", org.piwigo.remotesync.api.type.Type.MIXED, null, anonymous_id);
		return this;
	}	

 	public PwgRatesDeleteRequest setImageId(Integer image_id) {
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
		return true;
	};

	public String getWSMethodName() {
		return "pwg.rates.delete";
	}

	public Class<PwgRatesDeleteResponse> getReturnType() {
		return PwgRatesDeleteResponse.class;
	}
}
