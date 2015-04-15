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
package org.piwigo.remotesync.api.xml;

import org.apache.commons.lang.NotImplementedException;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class BooleanConverter implements Converter<Boolean> {

	@Override
	public Boolean read(InputNode node) throws Exception {
		String value = node.getValue();
		if ("1".equals(value))
			return true;
		else if ("0".equals(value))
			return false;	
		
		throw new NotImplementedException();
	}

	@Override
	public void write(OutputNode node, Boolean external) {
		node.setValue(external ? "1" : "0");
	}
}
