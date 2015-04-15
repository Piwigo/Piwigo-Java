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
import org.piwigo.remotesync.api.response.PwgGroupsDeleteUserResponse;

/**
Removes one or more users from a group.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgGroupsDeleteUserRequest extends AbstractRequest<PwgGroupsDeleteUserResponse> {

	protected PwgGroupsDeleteUserRequest() {}

	public PwgGroupsDeleteUserRequest(Integer group_id, Integer user_id) {
		setGroupId(group_id);
		setUserId(user_id);
	}

	public PwgGroupsDeleteUserRequest(Integer group_id, Integer... user_id) {
		setGroupId(group_id);
		setUserId(java.util.Arrays.asList(user_id));
	}

	public PwgGroupsDeleteUserRequest(Integer group_id, java.util.List<Integer> user_idList) {
		setGroupId(group_id);
		setUserId(user_idList);
	}

 	protected PwgGroupsDeleteUserRequest setGroupId(Integer group_id) {
		addParameterValue("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_id);
		return this;
	}	

 	protected PwgGroupsDeleteUserRequest setUserId(Integer user_id) {
		addParameterValue("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_id);
		return this;
	}	

	protected PwgGroupsDeleteUserRequest setUserId(Integer... user_id) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(user_id));
		return this;
	}

	protected PwgGroupsDeleteUserRequest setUserId(java.util.List<Integer> user_idList) {
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
		return "pwg.groups.deleteUser";
	}

	public Class<PwgGroupsDeleteUserResponse> getReturnType() {
		return PwgGroupsDeleteUserResponse.class;
	}
}
