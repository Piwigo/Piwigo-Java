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

import java.util.List;

import org.piwigo.remotesync.legacy.PwgSessionGetStatusResponseLegacy;
import org.simpleframework.xml.ElementList;

public class PwgSessionGetStatusResponse extends PwgSessionGetStatusResponseLegacy {

	/**
	 * Casting our list of available_sizes here as it will not be used by the tool
	 * @since v0.0.14
	 */
	@ElementList(required=false)
	public List<String> available_sizes;

}
