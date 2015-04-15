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

import java.util.Date;

import org.simpleframework.xml.Element;

public class Group {
	@Element
	public Integer id;
	
	@Element
	public String name;
	
	@Element(name ="is_default")
	public Boolean isDefault;
	
	@Element(name ="nb_users")
	public Integer nbUsers;
	
	@Element(required=false)
	public Date lastmodified;
}
