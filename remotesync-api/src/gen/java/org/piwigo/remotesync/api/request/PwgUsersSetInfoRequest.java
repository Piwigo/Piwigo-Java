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
import org.piwigo.remotesync.api.response.PwgUsersSetInfoResponse;

/**
Updates a user. Leave a field blank to keep the current value.
<br>"username", "password" and "email" are ignored if "user_id" is an array.
<br>set "group_id" to -1 if you want to dissociate users from all groups
**/
@org.piwigo.remotesync.generator.Generated
public class PwgUsersSetInfoRequest extends AbstractRequest<PwgUsersSetInfoResponse> {

	protected PwgUsersSetInfoRequest() {}

	public PwgUsersSetInfoRequest(Integer user_id) {
		setUserId(user_id);
	}

	public PwgUsersSetInfoRequest(Integer... user_id) {
		setUserId(java.util.Arrays.asList(user_id));
	}

	public PwgUsersSetInfoRequest(java.util.List<Integer> user_idList) {
		setUserId(user_idList);
	}

 	protected PwgUsersSetInfoRequest setUserId(Integer user_id) {
		addParameterValue("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_id);
		return this;
	}	

	protected PwgUsersSetInfoRequest setUserId(Integer... user_id) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(user_id));
		return this;
	}

	protected PwgUsersSetInfoRequest setUserId(java.util.List<Integer> user_idList) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_idList);
		return this;
	}

 	public PwgUsersSetInfoRequest setUsername(String username) {
		addParameterValue("username", org.piwigo.remotesync.api.type.Type.MIXED, null, username);
		return this;
	}	

 	public PwgUsersSetInfoRequest setPassword(String password) {
		addParameterValue("password", org.piwigo.remotesync.api.type.Type.MIXED, null, password);
		return this;
	}	

 	public PwgUsersSetInfoRequest setEmail(String email) {
		addParameterValue("email", org.piwigo.remotesync.api.type.Type.MIXED, null, email);
		return this;
	}	

	//info : guest,generic,normal,admin,webmaster
 	public PwgUsersSetInfoRequest setStatus(Status status) {
		addParameterValue("status", org.piwigo.remotesync.api.type.Type.ENUM, null, status);
		return this;
	}	

	//maxValue : 8
 	public PwgUsersSetInfoRequest setLevel(Integer level) {
		addParameterValue("level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 8, level);
		return this;
	}	

 	public PwgUsersSetInfoRequest setLanguage(String language) {
		addParameterValue("language", org.piwigo.remotesync.api.type.Type.MIXED, null, language);
		return this;
	}	

 	public PwgUsersSetInfoRequest setTheme(String theme) {
		addParameterValue("theme", org.piwigo.remotesync.api.type.Type.MIXED, null, theme);
		return this;
	}	

 	public PwgUsersSetInfoRequest setGroupId(Integer group_id) {
		addParameterValue("group_id", org.piwigo.remotesync.api.type.Type.INT, null, group_id);
		return this;
	}	

	public PwgUsersSetInfoRequest setGroupId(Integer... group_id) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT, null, java.util.Arrays.asList(group_id));
		return this;
	}

	public PwgUsersSetInfoRequest setGroupId(java.util.List<Integer> group_idList) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT, null, group_idList);
		return this;
	}

 	public PwgUsersSetInfoRequest setNbImagePage(Integer nb_image_page) {
		addParameterValue("nb_image_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, nb_image_page);
		return this;
	}	

 	public PwgUsersSetInfoRequest setRecentPeriod(Integer recent_period) {
		addParameterValue("recent_period", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, recent_period);
		return this;
	}	

 	public PwgUsersSetInfoRequest setExpand(Boolean expand) {
		addParameterValue("expand", org.piwigo.remotesync.api.type.Type.BOOL, null, expand);
		return this;
	}	

 	public PwgUsersSetInfoRequest setShowNbComments(Boolean show_nb_comments) {
		addParameterValue("show_nb_comments", org.piwigo.remotesync.api.type.Type.BOOL, null, show_nb_comments);
		return this;
	}	

 	public PwgUsersSetInfoRequest setShowNbHits(Boolean show_nb_hits) {
		addParameterValue("show_nb_hits", org.piwigo.remotesync.api.type.Type.BOOL, null, show_nb_hits);
		return this;
	}	

 	public PwgUsersSetInfoRequest setEnabledHigh(Boolean enabled_high) {
		addParameterValue("enabled_high", org.piwigo.remotesync.api.type.Type.BOOL, null, enabled_high);
		return this;
	}	

	public enum Status {
		GUEST,
		GENERIC,
		NORMAL,
		ADMIN,
		WEBMASTER
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
		return "pwg.users.setInfo";
	}

	public Class<PwgUsersSetInfoResponse> getReturnType() {
		return PwgUsersSetInfoResponse.class;
	}
}
