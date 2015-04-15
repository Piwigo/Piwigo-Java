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

import org.piwigo.remotesync.api.model.Group;
import org.piwigo.remotesync.api.model.Paging;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.core.Commit;

public abstract class PwgGroupsCommonResponse extends BasicResponse {
	@Element
	public Paging paging;

	@ElementList
	public List<Group> groups;
	
	@Commit
	private void commit() {
		if (groups.size() == 1)
			id = groups.get(0).id;
	}
	
	public Integer id;
}
