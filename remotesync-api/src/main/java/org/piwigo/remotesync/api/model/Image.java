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
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class Image {

	@Attribute
	public Integer id;

	@Attribute
	public Integer width;

	@Attribute
	public Integer height;

	@Attribute
	public Integer hit;

	@Attribute(name = "file")
	public String filename;

	@Attribute(name = "date_creation")
	public Date creationDate;

	@Attribute(name = "date_available")
	public Date availabilityDate;

	@Attribute(name = "page_url")
	public String pageURL;

	@Attribute(name = "element_url")
	public String elementURL;
	
	@Element
	public String name;
	
	@Element
	public Derivatives derivatives;

	@ElementList
	public List<Category> categories;

}
