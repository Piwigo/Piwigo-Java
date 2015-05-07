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

import java.io.File;

import org.piwigo.remotesync.api.Constants;

public class TypeValueValidator extends ValueValidator {
	@Override
	public String caseBool(Object object) {
		return object instanceof Boolean ? OK : "must be a boolean";
	}

	@Override
	public String caseFile(Object object) {
		if (object instanceof File) {
			File file = (File) object;
			if (file.exists()) {
				if (file.isFile()) {
					if (hasImageExtension(file)) {
						return OK;
					}
					return "must be an image";
				}
				return "must be a file (not a directory)";
			}
			return "file must exists";
		}
		return "must be a file";
	}

	protected boolean hasImageExtension(File file) {
		return Constants.IMAGE_EXTENSIONS_FILTER.accept(file);
	}

	@Override
	public String caseFloat(Object object) {
		return object instanceof Float ? OK : "must be a float";
	}

	@Override
	public String caseFloatPositive(Object object) {
		return object instanceof Float ? OK : "must be a float";
	}

	@Override
	public String caseInt(Object object) {
		return object instanceof Integer ? OK : "must be a int";
	}

	@Override
	public String caseIntPositive(Object object) {
		return object instanceof Integer ? OK : "must be a int";
	}

	@Override
	public String caseIntPositiveNotNull(Object object) {
		return object instanceof Integer ? OK : "must be a int";
	}

	@Override
	public String caseMixed(Object object) {
		return object instanceof String ? OK : "must be a string";
	}

	// FIXME not possible with reflective request and already checked in java
	// @Override
	// public String caseEnum(Object object) {
	// return object instanceof Enum ? OK : "must be an enum";
	// }
}
