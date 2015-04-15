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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.type.ValueValidator;
import org.piwigo.remotesync.api.type.Type;

public abstract class AbstractRequest<T extends BasicResponse> {

	private Map<String, Object> parameters = new HashMap<String, Object>();

	public abstract String getWSMethodName();

	public abstract Class<T> getReturnType();

	public abstract boolean isAdminOnly();

	public abstract boolean isPostOnly();

	public abstract boolean isNeedPwgToken();

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setPwgToken(String pwg_token) {
		addParameterValue("pwg_token", Type.MIXED, null, pwg_token);
	}

	protected void addParameterValue(String name, Type type, Integer maxValue, Object value) {
		ValueValidator.checkValue(type, maxValue, value);
		parameters.put(name, value);
	}

	protected void addParameterValueList(String name, Type type, Integer maxValue, List<? extends Object> values) {
		for (Object value : values)
			ValueValidator.checkValue(type, maxValue, value);
		parameters.put(name, values);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
