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
package org.piwigo.remotesync.api.model;

import org.simpleframework.xml.Attribute;

/**
 * <paging page="0" per_page="100" count="0" /><images />
 */
public class Paging {
	@Attribute
	public Integer page;

	@Attribute
	public Integer per_page;

	@Attribute
	public Integer count;
	
	@Attribute(required = false, name ="total_count")
	public Integer totalCount;
}
