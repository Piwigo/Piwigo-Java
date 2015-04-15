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

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "category")
public class Permission {
	@Attribute(name = "id")
	public Integer categoryId;
	
	@ElementList(name = "users")
	public List<Integer> userIds;

	@ElementList(name = "groups")
	public List<Integer> groupIds;

	@ElementList(name = "users_indirect")
	public List<Integer> userIndirectIds;
}
