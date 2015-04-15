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
package org.piwigo.remotesync.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class StringUtil {
	private static final SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String removeFormatting(String string) {
		return string.replace(" ", "").replace("\r", "").replace("\n", "");
	}

	/**
	 * 2005-05-17 20:15:42
	 */
	public static Date parseDate(String value) throws ParseException {
        return date_formatter.parse(value);
	}

	/**
	 * TODO Password not really encrypted/decrypted : warn users
	 */
	public static String fakeEncryptPassword(String value) {
		if (value == null)
			return null;
		return Base64.encodeBase64String(value.getBytes());
	}

	/**
	 * TODO Password not really encrypted/decrypted : warn users
	 */
	public static String fakeDecryptPassword(String value) {
		if (value == null)
			return null;
		return new String(Base64.decodeBase64(value));
	}
}
