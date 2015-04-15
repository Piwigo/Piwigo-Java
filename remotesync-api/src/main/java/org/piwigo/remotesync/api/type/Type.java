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
package org.piwigo.remotesync.api.type;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse.Parameter;

/**

piwigo types :
int
int positive notnull
int positive
float positive
float
mixed
bool
 */
public class Type {
	public static final Type INT = new Type(1, "INT", "int", "Integer");
	public static final Type INT_POSITIVE = new Type(2, "INT_POSITIVE", "int positive", "Integer");
	public static final Type INT_POSITIVE_NOTNULL = new Type(3, "INT_POSITIVE_NOTNULL", "int positive notnull", "Integer");
	public static final Type FLOAT = new Type(4, "FLOAT", "float", "Float");
	public static final Type FLOAT_POSITIVE = new Type(5, "FLOAT_POSITIVE", "float positive", "Float"); 
	public static final Type MIXED = new Type(6, "MIXED",  "mixed", "String");
	public static final Type BOOL = new Type(7, "BOOL", "bool", "Boolean");
	public static final Type FILE = new Type(8, "FILE", "file", "java.io.File");
	public static final Type ENUM = new Type(9, "ENUM", "enum", null);
	
	public final String name;
	public final int id;
	public final String typeName;
	public final String javaType;
	public List<String> values;

	private Type(int id, String name, String typeName, String javaType) {
		this.id = id;
		this.name = name;
		this.typeName = typeName;
		this.javaType=javaType;
	}
	
	public static EnumType createEnumType(Parameter parameter) {
		return new EnumType(parameter);
	}
	
	protected static class EnumType extends Type {

		public EnumType(Parameter parameter) {
			super(ENUM.id, ENUM.name, ENUM.typeName,  parameter.camelCaseName);
			values = new ArrayList<String>();
			for (String value : parameter.info.split(",")) {
				assert(value.toLowerCase().equals(value));
				values.add(value.trim().toUpperCase());
			}
		}
	}
	
	public static Type fromTypeName(String typeName) {
		if (INT.typeName.equals(typeName))
			return INT;
		else if (INT_POSITIVE.typeName.equals(typeName))
			return INT_POSITIVE;
		else if (INT_POSITIVE_NOTNULL.typeName.equals(typeName))
			return INT_POSITIVE_NOTNULL ;
		else if (FLOAT.typeName.equals(typeName))
			return FLOAT;
		else if (FLOAT_POSITIVE.typeName.equals(typeName))
			return FLOAT_POSITIVE;
		else if (MIXED.typeName.equals(typeName))
			return MIXED;
		else if (BOOL.typeName.equals(typeName))
			return BOOL;
		else if (FILE.typeName.equals(typeName))
			return FILE;
		else
			throw new NotImplementedException();
	}
}
