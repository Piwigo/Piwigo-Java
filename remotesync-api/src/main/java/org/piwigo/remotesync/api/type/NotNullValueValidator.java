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

public class NotNullValueValidator extends ValueValidator {

	@Override
	public void check(Type type, Object object) {
		if (object != null)
			return;
		super.check(type, object);
	}
	
	@Override
	public String caseIntPositiveNotNull(Object object) {
		return "cannot be null";
	}
	
	@Override
	public String caseFile(Object object) {
		return "cannot be null";
	}
	
}
