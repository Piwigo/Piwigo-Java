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
import org.piwigo.remotesync.api.response.PwgExtensionsIgnoreUpdateResponse;

/**
<b>Webmaster only.</b> Ignores an extension if it needs update.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgExtensionsIgnoreUpdateRequest extends AbstractRequest<PwgExtensionsIgnoreUpdateResponse> {

	public PwgExtensionsIgnoreUpdateRequest() {
	}

	//info : plugins, languages, themes
 	public PwgExtensionsIgnoreUpdateRequest setType(Type type) {
		addParameterValue("type", org.piwigo.remotesync.api.type.Type.ENUM, null, type);
		return this;
	}	

 	public PwgExtensionsIgnoreUpdateRequest setId(String id) {
		addParameterValue("id", org.piwigo.remotesync.api.type.Type.MIXED, null, id);
		return this;
	}	

	//info : If true, all ignored extensions will be reinitilized.
	//defaultValue : 0
 	public PwgExtensionsIgnoreUpdateRequest setReset(Boolean reset) {
		addParameterValue("reset", org.piwigo.remotesync.api.type.Type.BOOL, null, reset);
		return this;
	}	

	public enum Type {
		PLUGINS,
		LANGUAGES,
		THEMES
	}

	public boolean isNeedPwgToken() {
		return true;
	}

	public boolean isAdminOnly() {
		return true;
	};

	public boolean isPostOnly() {
		return false;
	};

	public String getWSMethodName() {
		return "pwg.extensions.ignoreUpdate";
	}

	public Class<PwgExtensionsIgnoreUpdateResponse> getReturnType() {
		return PwgExtensionsIgnoreUpdateResponse.class;
	}
}
