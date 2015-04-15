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
import org.simpleframework.xml.Element;

public abstract class AbstractCategory {

	@Attribute
	public Integer id;

	@Element(required = false)
	public String comment;
	
	@Attribute(required = false)
	public String date_last;
	
	@Element(required = false)
	public Integer id_uppercat;
	
	@Attribute(required = false)
	public String max_date_last;
	
	@Attribute(required = false)
	public Integer nb_categories;
	
	@Attribute(required = false)
	public Integer nb_images;
	
	@Attribute(required = false)
	public String page_url;
	
	@Element(required = false)
	public Integer representative_picture_id;
	
	@Element(required = false)
	public String tn_url;
	
	@Attribute(required = false)
	public Integer total_nb_images;
	
	@Attribute(required = false)
	public String url;

}
