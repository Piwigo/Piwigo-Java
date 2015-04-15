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
package org.piwigo.remotesync.api.response;

import java.util.ArrayList;
import java.util.List;

import org.piwigo.remotesync.api.type.Type;
import org.piwigo.remotesync.api.xml.BooleanConverter;
import org.piwigo.remotesync.api.xml.TypeConverter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.core.Commit;

public class ReflectionGetMethodDetailsResponse extends BasicResponse {
	@Element
	public String name;

	@Element(required = false)
	public String description;

	@ElementList(name = "params")
	public List<Parameter> parameters;

	@Element(name = "options")
	public Option options;

	@Root(name = "item")
	public static class Parameter {
		@Element
		public String name;

		@Element
		@Convert(BooleanConverter.class)
		public Boolean optional;

		@Element
		@Convert(BooleanConverter.class)
		public Boolean acceptArray;

		@Element
		@Convert(TypeConverter.class)
		public Type type;

		@Element(required = false)
		public String info;

		@Element(required = false)
		public String defaultValue;

		@Element(required = false)
		public Integer maxValue;

		@Commit
		public void commit() {
			camelCaseName = getCamelCase(name, true);
		}

		public String camelCaseName;
	}

	public static class Option {
		@Element(required = false)
		@Convert(BooleanConverter.class)
		public Boolean admin_only = false;

		@Element(required = false)
		@Convert(BooleanConverter.class)
		public Boolean post_only = false;
	}

	@Commit
	public void commit() {
		camelCaseName = getCamelCase(name, true);
		commonName = getCommonName(name);
		apiName = getApiName(name);

		mandatoryParameters = new ArrayList<Parameter>();
		//add not acceptArray First
		for (Parameter parameter : parameters)
			if (!parameter.optional && !parameter.acceptArray)
				mandatoryParameters.add(parameter);
		//acceptArray is the last one for variable length parameter
		for (Parameter parameter : parameters)
			if (!parameter.optional && parameter.acceptArray) {
				mandatoryParameters.add(parameter);
				hasMandatoryArrayParameters = true;
			}
		hasParameters = !parameters.isEmpty();
		hasMandatoryParameters = !mandatoryParameters.isEmpty();
	}

	public String camelCaseName;

	public String commonName;

	public String apiName;
	
	public boolean hasParameters;

	public boolean hasMandatoryParameters;

	public List<Parameter> mandatoryParameters;

	public boolean hasMandatoryArrayParameters = false;

	public boolean needPwgToken = false;

	private static String getCamelCase(String string, boolean firstUp) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char charAt = string.charAt(i);
			if (i == 0 && firstUp)
				sb.append(Character.toUpperCase(charAt));
			else if (charAt == '.' || charAt == '_')
				sb.append(Character.toUpperCase(string.charAt(++i)));
			else
				sb.append(charAt);
		}
		return sb.toString();
	}

	private String getCommonName(String string) {
		return getCamelCase(string.substring(0, string.lastIndexOf('.')), true);
	}

	private String getApiName(String string) {
		if (string.startsWith("pwg."))
			string = string.substring(string.indexOf('.') + 1);
		if (string.indexOf('.') == -1)
			string = "gallery." + string;
		return getCamelCase(string, false);
	}
}
