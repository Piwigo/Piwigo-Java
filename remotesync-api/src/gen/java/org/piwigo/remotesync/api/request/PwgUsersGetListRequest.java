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
import org.piwigo.remotesync.api.response.PwgUsersGetListResponse;

/**
Retrieves a list of all the users.<br>
<br>
<b>display</b> controls which data are returned, possible values are:<br>
all, basics, none,<br>
username, email, status, level, groups,<br>
language, theme, nb_image_page, recent_period, expand, show_nb_comments, show_nb_hits,<br>
enabled_high, registration_date, registration_date_string, registration_date_since, last_visit, last_visit_string, last_visit_since<br>
<b>basics</b> stands for "username,email,status,level,groups"
**/
@org.piwigo.remotesync.generator.Generated
public class PwgUsersGetListRequest extends AbstractRequest<PwgUsersGetListResponse> {

	public PwgUsersGetListRequest() {
	}

 	public PwgUsersGetListRequest setUserId(Integer user_id) {
		addParameterValue("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_id);
		return this;
	}	

	public PwgUsersGetListRequest setUserId(Integer... user_id) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(user_id));
		return this;
	}

	public PwgUsersGetListRequest setUserId(java.util.List<Integer> user_idList) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_idList);
		return this;
	}

	//info : Use "%" as wildcard.
 	public PwgUsersGetListRequest setUsername(String username) {
		addParameterValue("username", org.piwigo.remotesync.api.type.Type.MIXED, null, username);
		return this;
	}	

	//info : guest,generic,normal,admin,webmaster
 	public PwgUsersGetListRequest setStatus(Status status) {
		addParameterValue("status", org.piwigo.remotesync.api.type.Type.ENUM, null, status);
		return this;
	}	

	public PwgUsersGetListRequest setStatus(Status... status) {
		addParameterValueList("status", org.piwigo.remotesync.api.type.Type.ENUM, null, java.util.Arrays.asList(status));
		return this;
	}

	public PwgUsersGetListRequest setStatus(java.util.List<Status> statusList) {
		addParameterValueList("status", org.piwigo.remotesync.api.type.Type.ENUM, null, statusList);
		return this;
	}

	//defaultValue : 0
	//maxValue : 8
 	public PwgUsersGetListRequest setMinLevel(Integer min_level) {
		addParameterValue("min_level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 8, min_level);
		return this;
	}	

 	public PwgUsersGetListRequest setGroupId(Integer group_id) {
		addParameterValue("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_id);
		return this;
	}	

	public PwgUsersGetListRequest setGroupId(Integer... group_id) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(group_id));
		return this;
	}

	public PwgUsersGetListRequest setGroupId(java.util.List<Integer> group_idList) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_idList);
		return this;
	}

	//defaultValue : 100
	//maxValue : 1000
 	public PwgUsersGetListRequest setPerPage(Integer per_page) {
		addParameterValue("per_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 1000, per_page);
		return this;
	}	

	//defaultValue : 0
 	public PwgUsersGetListRequest setPage(Integer page) {
		addParameterValue("page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, page);
		return this;
	}	

	//info : id, username, level, email
	//defaultValue : id
 	public PwgUsersGetListRequest setOrder(Order order) {
		addParameterValue("order", org.piwigo.remotesync.api.type.Type.ENUM, null, order);
		return this;
	}	

	//info : Comma saparated list (see method description)
	//defaultValue : basics
 	public PwgUsersGetListRequest setDisplay(String display) {
		addParameterValue("display", org.piwigo.remotesync.api.type.Type.MIXED, null, display);
		return this;
	}	

	public enum Status {
		GUEST,
		GENERIC,
		NORMAL,
		ADMIN,
		WEBMASTER
	}

	public enum Order {
		ID,
		USERNAME,
		LEVEL,
		EMAIL
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
		return "pwg.users.getList";
	}

	public Class<PwgUsersGetListResponse> getReturnType() {
		return PwgUsersGetListResponse.class;
	}
}
