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
package org.piwigo.remotesync.legacy;

import java.util.regex.Pattern;

public class VersionParser 
{
	
	private int major;
	private int minor;
	private int build;
	
	public void parseVersion(String version)
	{
		String[] parts = version.split(Pattern.quote("."));
		
		major = Integer.parseInt(parts[0]);
		minor = Integer.parseInt(parts[1]);
		build = Integer.parseInt(parts[2]);
	}
	
	public int getMajorVersion()
	{
		return (major);
	}
	
	public int getMinorVersion()
	{
		return (minor);
	}

	public int getBuildVersion()
	{
		return (build);
	}
}
