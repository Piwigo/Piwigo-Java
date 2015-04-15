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

public abstract class TypeSwitch<T extends Object, U extends Object> {
	public T doSwitch(Type type, U object) {
		return internalDoSwitch(type, object);
	}

	protected T internalDoSwitch(Type type, U object) {
		T result = null;
		switch (type.id) {
		case 1:
			result = caseInt(object);
	        break;
		case 2:
			result = caseIntPositive(object);
	        break;
		case 3:
			result = caseIntPositiveNotNull(object);
	        break;
		case 4:
			result = caseFloat(object);
	        break;
		case 5:
			result = caseFloatPositive(object);
	        break;
		case 6:
			result = caseMixed(object);
	        break;
		case 7:
			result = caseBool(object);
	        break;
		case 8:
			result = caseFile(object);
	        break;
		case 9:
			result = caseEnum(object);
	        break;
		}

        if (result == null) result = defaultCase(object);
        return result;
	}

	public T caseFile(U object) {
		return null;
	}

	public T caseBool(U object) {
		return null;
	}

	public T caseMixed(U object) {
		return null;
	}

	public T caseFloatPositive(U object) {
		return null;
	}

	public T caseFloat(U object) {
		return null;
	}

	public T caseIntPositiveNotNull(U object) {
		return null;
	}

	public T caseIntPositive(U object) {
		return null;
	}

	public T caseInt(U object) {
		return null;
	}

	public T caseEnum(U object) {
		return null;
	}

	public T defaultCase(U object) {
		return null;
	}
}
