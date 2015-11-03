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
package org.piwigo.remotesync.api.conf;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

import org.apache.commons.lang.NotImplementedException;
import org.piwigo.remotesync.api.ISyncConfiguration;

public class SyncConfigurationValidator {

	public static final SyncConfigurationValidator INSTANCE = new SyncConfigurationValidator();

	public static enum ValidatorType {
		url, string, integer, dir
	}

	public static enum ValidatorRequired {
		yes, proxy, no;
	}
	
	public static class SyncValidationException extends Exception {

		private static final long serialVersionUID = 2828722578008089789L;

		public String fieldName;

		public SyncValidationException(String fieldName, String message) {
			super(message);
			this.fieldName = fieldName;
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public static @interface Validator {
		ValidatorType type();

		ValidatorRequired required() default ValidatorRequired.no;
	}

	public static void main(String[] args) throws Exception {
		INSTANCE.validate(new SyncConfiguration());
	}
	
	public void validate(ISyncConfiguration syncConfiguration) throws IllegalArgumentException, IllegalAccessException, SyncValidationException {
		for (Field field : SyncConfiguration.class.getDeclaredFields()) {
			String value = (String) field.get(syncConfiguration);
			Boolean usesProxy = syncConfiguration.getUsesProxy();
			
			validate(field, value, usesProxy);
		}
	}

	public void validate(String fieldName, String value, Boolean usesProxy) throws SyncValidationException {
		for (Field field : SyncConfiguration.class.getDeclaredFields()) {
			if (field.getName().equals(fieldName)) {
				validate(field, value, usesProxy);
				return;
			}
		}
		throw new SyncValidationException(fieldName, "doesn't exist");
	}
	
	protected void validate(Field field, String value, Boolean usesProxy) throws SyncValidationException {
		Validator validator = field.getAnnotation(Validator.class);
		if (validator != null) {
			String fieldName = field.getName();
			
			validateRequired(validator, fieldName, value, usesProxy);
			validateType(validator, fieldName, value);
		}
	}

	protected void validateRequired(Validator validator, String fieldName, String value, Boolean proxy) throws SyncValidationException {
		switch (validator.required()) {
		case yes:
			if (value == null || value.length() == 0)
				throw new SyncValidationException(fieldName, "is required");
			break;
		case proxy:
			if (proxy != null && proxy && (value == null || value.length() == 0))
				throw new SyncValidationException(fieldName, "is required");
			break;
		case no:
			break;
		default:
			throw new NotImplementedException();
		}
	}

	protected void validateType(Validator validator, String fieldName, String value) throws SyncValidationException {
		if (value == null || value.length() == 0)
			return;
		
		switch (validator.type()) {
		case url:
			Pattern pattern = Pattern.compile("https?://.+");
			if (!pattern.matcher(value).matches())
				throw new SyncValidationException(fieldName, "is not an url");
			break;
		case dir:
			File file;
			try {
				file = new File(value);
				if (!file.exists())
					throw new SyncValidationException(fieldName, "doesn't exist");
				if (!file.isDirectory())
					throw new SyncValidationException(fieldName, "is not a directory");
			} catch (Exception e) {
				throw new SyncValidationException(fieldName, "is invalid");
			}
			break;
		case integer:
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				throw new SyncValidationException(fieldName, "is not an integer");
			}
			break;
		case string:
			break;
		default:
			throw new NotImplementedException();
		} 
	}
}
