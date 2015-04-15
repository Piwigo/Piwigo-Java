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
package org.piwigo.remotesync.api.xml;

import java.util.Date;

import org.apache.commons.lang.NotImplementedException;
import org.piwigo.remotesync.api.util.StringUtil;
import org.simpleframework.xml.transform.Transform;

public class DateTransform implements Transform<Date> {

	@Override
	public Date read(String value) throws Exception {
		return StringUtil.parseDate(value);
	}

	@Override
	public String write(Date value) throws Exception {
		throw new NotImplementedException();
	}
}
