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
package org.piwigo.remotesync.api.reflection;

import java.util.List;

import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse.Parameter;

@SuppressWarnings("rawtypes")
public class ReflectiveRequest extends AbstractRequest {
	private MethodReflection methodReflection;
	private ReflectionGetMethodDetailsResponse methodDetails;

	public ReflectiveRequest(MethodReflection methodReflection) {
		this.methodReflection = methodReflection;
		methodDetails = methodReflection.getMethodDetails();
	}

	public void addParameterValue(Parameter parameter, Object value) {
		addParameterValue(parameter.name, parameter.type, parameter.maxValue, value);
	}

	@SuppressWarnings("unchecked")
	public void addParameterValueList(Parameter parameter, List<? extends Object> values) {
		addParameterValueList(parameter.name, parameter.type, parameter.maxValue, values);
	}

	@Override
	public String getWSMethodName() {
		return methodReflection.getMethodName();
	}

	@Override
	public Class getReturnType() {
		return methodReflection.getResponseClass();
	}

	@Override
	public boolean isAdminOnly() {
		return methodDetails.options.admin_only;
	}

	@Override
	public boolean isPostOnly() {
		return methodDetails.options.post_only;
	}

	@Override
	public boolean isNeedPwgToken() {
		return methodDetails.needPwgToken;
	}
}
