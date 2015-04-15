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
import org.piwigo.remotesync.api.response.PwgSessionLoginResponse;

/**
Tries to login the user.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgSessionLoginRequest extends AbstractRequest<PwgSessionLoginResponse> {

	protected PwgSessionLoginRequest() {}

	public PwgSessionLoginRequest(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

 	protected PwgSessionLoginRequest setUsername(String username) {
		addParameterValue("username", org.piwigo.remotesync.api.type.Type.MIXED, null, username);
		return this;
	}	

 	protected PwgSessionLoginRequest setPassword(String password) {
		addParameterValue("password", org.piwigo.remotesync.api.type.Type.MIXED, null, password);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return false;
	};

	public boolean isPostOnly() {
		return true;
	};

	public String getWSMethodName() {
		return "pwg.session.login";
	}

	public Class<PwgSessionLoginResponse> getReturnType() {
		return PwgSessionLoginResponse.class;
	}
}
