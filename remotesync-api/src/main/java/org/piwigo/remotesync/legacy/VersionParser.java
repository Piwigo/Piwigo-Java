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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

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
		build = StringUtils.isNumeric(parts[2]) ? Integer.parseInt(parts[2]) : parseBetaVersion(parts[2]);
	}
	
	public int parseBetaVersion(String version)
	{
		Matcher matcher = Pattern.compile("\\d+").matcher(version);
		
        if (!matcher.find())
            throw new NumberFormatException("For input string [" + version + "]");
		return Integer.parseInt(matcher.group());
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
