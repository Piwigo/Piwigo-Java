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
import org.piwigo.remotesync.api.response.PwgPluginsPerformActionResponse;

/**

**/
@org.piwigo.remotesync.generator.Generated
public class PwgPluginsPerformActionRequest extends AbstractRequest<PwgPluginsPerformActionResponse> {

	protected PwgPluginsPerformActionRequest() {}

	public PwgPluginsPerformActionRequest(Action action, String plugin) {
		setAction(action);
		setPlugin(plugin);
	}

	//info : install, activate, deactivate, uninstall, delete
 	protected PwgPluginsPerformActionRequest setAction(Action action) {
		addParameterValue("action", org.piwigo.remotesync.api.type.Type.ENUM, null, action);
		return this;
	}	

 	protected PwgPluginsPerformActionRequest setPlugin(String plugin) {
		addParameterValue("plugin", org.piwigo.remotesync.api.type.Type.MIXED, null, plugin);
		return this;
	}	

	public enum Action {
		INSTALL,
		ACTIVATE,
		DEACTIVATE,
		UNINSTALL,
		DELETE
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
		return "pwg.plugins.performAction";
	}

	public Class<PwgPluginsPerformActionResponse> getReturnType() {
		return PwgPluginsPerformActionResponse.class;
	}
}
