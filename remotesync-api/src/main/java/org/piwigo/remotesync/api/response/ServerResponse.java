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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class ServerResponse extends BasicResponse {

	@Element(name = "err", required = false)
	public Error error;

	public static class Error {
		@Attribute
		public Integer code;

		@Attribute(name = "msg")
		public String message;
		
		@Override
		public String toString() {
			return new StringBuilder(message).append(" (code : ").append(code).append(")").toString();
		}
	}
}
