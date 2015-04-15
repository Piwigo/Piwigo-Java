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
package org.piwigo.remotesync.api.type;

public class ValueValidator extends TypeSwitch<String, Object> {
	protected static final String OK = "OK";

	public void check(Type type, Object object) {
		String message = doSwitch(type, object);
		if (OK.equals(message))
			return;
		else
			throw new RuntimeException(message);
	}		
	
	@Override
	public String defaultCase(Object object) {
		return OK;
	}
	
	public static void checkValue(Type type, Integer maxValue, Object value) {
		if (value == null) {
			new NotNullValueValidator().check(type, value);
			return;
		}
		new TypeValueValidator().check(type, value);
		new PositiveValueValidator().check(type, value);
		if (maxValue != null)
			new MaxValueValidator(maxValue).check(type, value);
	}
}
