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

import org.piwigo.remotesync.api.type.Type;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class TypeConverter implements Converter<Type> {

	@Override
	public Type read(InputNode node) throws Exception {
		return Type.fromTypeName(node.getValue());
	}

	@Override
	public void write(OutputNode node, Type external) {
		node.setValue(external.typeName);
	}
}
