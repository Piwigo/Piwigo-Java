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

import org.piwigo.remotesync.api.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class User {
	
	@Element
	public Integer id;

	@Element
	public String username;

	@Element(required = false)
	public String email;

	@Element
	public Constants.UserType status;
	
	@Element
	public Integer level;
	
	@Element(required = false, name = "nb_image_page")
	public Integer nbImagePage;
	
	@ElementList
	public List<Group> groups;

}
