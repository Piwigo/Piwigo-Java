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
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse;

/**

**/
@org.piwigo.remotesync.generator.Generated
public class ReflectionGetMethodDetailsRequest extends AbstractRequest<ReflectionGetMethodDetailsResponse> {

	protected ReflectionGetMethodDetailsRequest() {}

	public ReflectionGetMethodDetailsRequest(String methodName) {
		setMethodName(methodName);
	}

 	protected ReflectionGetMethodDetailsRequest setMethodName(String methodName) {
		addParameterValue("methodName", org.piwigo.remotesync.api.type.Type.MIXED, null, methodName);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return false;
	};

	public boolean isPostOnly() {
		return false;
	};

	public String getWSMethodName() {
		return "reflection.getMethodDetails";
	}

	public Class<ReflectionGetMethodDetailsResponse> getReturnType() {
		return ReflectionGetMethodDetailsResponse.class;
	}
}
