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
package org.piwigo.remotesync.api.exception;

import java.io.IOException;

public class IORuntimeException extends RuntimeException {

	public IORuntimeException(String message, IOException e) {
		super(message, e);
	}

	private static final long serialVersionUID = 8842996765838427856L;

}
