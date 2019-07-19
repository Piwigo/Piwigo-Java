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

import org.simpleframework.xml.Element;

public class UploadCategory {

    @Element(required = false)
	public Integer id;

	@Element(required = false)
	public String nb_photos;
	
	@Element(required = false)
	public String label;
}
