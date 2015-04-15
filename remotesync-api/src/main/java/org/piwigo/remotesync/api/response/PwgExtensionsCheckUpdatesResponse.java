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

import org.piwigo.remotesync.api.xml.BooleanConverter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.convert.Convert;

public class PwgExtensionsCheckUpdatesResponse extends BasicResponse {
	
	@Element
	@Convert(BooleanConverter.class)
	public Boolean piwigo_need_update;

	@Element
	@Convert(BooleanConverter.class)
	public Boolean ext_need_update;
}
