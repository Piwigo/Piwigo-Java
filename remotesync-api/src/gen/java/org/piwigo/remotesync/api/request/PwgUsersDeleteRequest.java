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
import org.piwigo.remotesync.api.response.PwgUsersDeleteResponse;

/**
Deletes on or more users. Photos owned by this user are not deleted.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgUsersDeleteRequest extends AbstractRequest<PwgUsersDeleteResponse> {

	protected PwgUsersDeleteRequest() {}

	public PwgUsersDeleteRequest(Integer user_id) {
		setUserId(user_id);
	}

	public PwgUsersDeleteRequest(Integer... user_id) {
		setUserId(java.util.Arrays.asList(user_id));
	}

	public PwgUsersDeleteRequest(java.util.List<Integer> user_idList) {
		setUserId(user_idList);
	}

 	protected PwgUsersDeleteRequest setUserId(Integer user_id) {
		addParameterValue("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_id);
		return this;
	}	

	protected PwgUsersDeleteRequest setUserId(Integer... user_id) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(user_id));
		return this;
	}

	protected PwgUsersDeleteRequest setUserId(java.util.List<Integer> user_idList) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_idList);
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
		return "pwg.users.delete";
	}

	public Class<PwgUsersDeleteResponse> getReturnType() {
		return PwgUsersDeleteResponse.class;
	}
}
