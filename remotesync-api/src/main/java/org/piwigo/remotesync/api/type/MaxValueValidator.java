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

public class MaxValueValidator extends ValueValidator {

	protected Integer maxValue;

	public MaxValueValidator(Integer maxValue) {
		this.maxValue = maxValue;
	}
	
	@Override
	public String caseFloat(Object object) {
		return ((Float) object) <= maxValue ? OK : "must be <= " + maxValue;
	}
	
	@Override
	public String caseFloatPositive(Object object) {
		return ((Float) object) <= maxValue ? OK : "must be <= " + maxValue;
	}
	
	@Override
	public String caseInt(Object object) {
		return ((Integer) object) <= maxValue ? OK : "must be <= " + maxValue;
	}

	@Override
	public String caseIntPositive(Object object) {
		return ((Integer) object) <= maxValue ? OK : "must be <= " + maxValue;
	}
	
	@Override
	public String caseIntPositiveNotNull(Object object) {
		return ((Integer) object) <= maxValue ? OK : "must be <= " + maxValue;
	}
}
