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
	private static final String PE = "]-_";
	private static final String PB = "_-[";
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
		value = PB + rot13(value) + PE;
		return Base64.encodeBase64String(value.getBytes());
	}

	/**
	 * TODO Password not really encrypted/decrypted : warn users
	 * @throws ParseException 
	 */
	public static String fakeDecryptPassword(String value) throws ParseException {
		if (value == null)
			return null;
		value = new String(Base64.decodeBase64(value));
		if (value.startsWith(PB) && value.endsWith(PE))
			return rot13(value.substring(3, value.length() - 3));
		throw new ParseException("Cannot decrypt password", 0);
	}
	
	public static String rot13(String string) {
		StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            builder.append(c);
        }
		return builder.toString();
	}
}
