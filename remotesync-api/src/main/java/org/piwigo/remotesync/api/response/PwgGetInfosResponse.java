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

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

//TODO strict = false, if piwigo version change then check new attributes
@Root(name = "rsp", strict=false)
public class PwgGetInfosResponse extends BasicResponse {

	@Element(name="value")
	@Path("infos/item[1]")
	public String version;

	@Element(name="value")
	@Path("infos/item[2]")
	public Integer nb_elements;

	@Element(name="value")
	@Path("infos/item[3]")
	public Integer nb_categories;

	@Element(name="value")
	@Path("infos/item[4]")
	public Integer nb_virtual;

	@Element(name="value")
	@Path("infos/item[5]")
	public Integer nb_physical;

	@Element(name="value")
	@Path("infos/item[6]")
	public Integer nb_image_category;

	@Element(name="value")
	@Path("infos/item[7]")
	public Integer nb_tags;

	@Element(name="value")
	@Path("infos/item[8]")
	public Integer nb_image_tag;

	@Element(name="value")
	@Path("infos/item[9]")
	public Integer nb_users;

	@Element(name="value")
	@Path("infos/item[10]")
	public Integer nb_groups;

	@Element(name="value")
	@Path("infos/item[11]")
	public Integer nb_comments;

}
