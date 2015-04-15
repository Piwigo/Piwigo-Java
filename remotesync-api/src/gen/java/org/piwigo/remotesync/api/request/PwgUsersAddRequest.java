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
import org.piwigo.remotesync.api.response.PwgUsersAddResponse;

/**
Registers a new user.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgUsersAddRequest extends AbstractRequest<PwgUsersAddResponse> {

	protected PwgUsersAddRequest() {}

	public PwgUsersAddRequest(String username) {
		setUsername(username);
	}

 	protected PwgUsersAddRequest setUsername(String username) {
		addParameterValue("username", org.piwigo.remotesync.api.type.Type.MIXED, null, username);
		return this;
	}	

 	public PwgUsersAddRequest setPassword(String password) {
		addParameterValue("password", org.piwigo.remotesync.api.type.Type.MIXED, null, password);
		return this;
	}	

 	public PwgUsersAddRequest setPasswordConfirm(String password_confirm) {
		addParameterValue("password_confirm", org.piwigo.remotesync.api.type.Type.MIXED, null, password_confirm);
		return this;
	}	

 	public PwgUsersAddRequest setEmail(String email) {
		addParameterValue("email", org.piwigo.remotesync.api.type.Type.MIXED, null, email);
		return this;
	}	

	//defaultValue : 0
 	public PwgUsersAddRequest setSendPasswordByMail(Boolean send_password_by_mail) {
		addParameterValue("send_password_by_mail", org.piwigo.remotesync.api.type.Type.BOOL, null, send_password_by_mail);
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
		return "pwg.users.add";
	}

	public Class<PwgUsersAddResponse> getReturnType() {
		return PwgUsersAddResponse.class;
	}
}
