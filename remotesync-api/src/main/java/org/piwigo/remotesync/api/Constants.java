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
package org.piwigo.remotesync.api;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class Constants {
	
	//TODO should be moved to API?

	// TODO ask pierrick :
	//  * isAdmin should be isWebmaster || isAdmin
	public enum UserType {
		webmaster, admin, guest, generic, normal
	}
	
	private static List<String> IMAGE_EXTENSIONS = Arrays.asList(new String[] {"jpg", "jpeg", "png", "gif", "tif", "tiff"});
	
	public static IOFileFilter IMAGE_EXTENSIONS_FILTER = new ExtensionFileFilter();
	
	private static final class ExtensionFileFilter extends AbstractFileFilter {

		@Override
		public boolean accept(File file) {
			return IMAGE_EXTENSIONS.contains(FilenameUtils.getExtension(file.getName()).toLowerCase());
		}

	}

}
