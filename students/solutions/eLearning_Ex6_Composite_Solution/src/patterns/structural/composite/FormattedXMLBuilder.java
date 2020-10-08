/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package patterns.structural.composite;

import java.util.List;

/**
 * The Class FormattedXMLBuilder is an {@link XMLBuilder} that can build a well
 * structured XML document. This class is provided for you. Nothing else to do
 * here, other than review the code
 */
public class FormattedXMLBuilder implements XMLBuilder {

	// Just review this

	public final static String INDENT_STRING = "  ";
	private StringBuilder sb = new StringBuilder();

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * patterns.structural.composite.XMLBuilder#build(patterns.structural.composite
	 * .XMLElement)
	 */
	@Override
	public void build(XMLElement element) { // start at level '0' (no indentation)
		this.buildInternal(element, 0);
	}

	/**
	 * Add an indentation string as many times as required by the given level
	 *
	 * @param level the level of indentation
	 */
	private void indent(int level) {
		for (int i = 0; i < level; ++i)
			this.sb.append(INDENT_STRING);
	}

	/**
	 * Builds the XML document recursively by adding the name of the XMLElement and
	 * iterating through it's children, adding each child's name and data, indenting
	 * as necessary and closing the element's XML tag.
	 *
	 * @param element the XML element
	 * @param level   the level of indentation
	 */
	private void buildInternal(XMLElement element, int level) {
		this.indent(level);
		this.sb.append("<").append(element.getName()).append(">").append("\n");
		List<XMLElement> children = element.getChildren();
		for (XMLElement child : children)
			this.buildInternal(child, level + 1);
		if (element.getData() != null) {
			this.indent(level + 1);
			this.sb.append(element.getData()).append("\n");
		}
		this.indent(level);
		this.sb.append("</").append(element.getName()).append(">").append("\n");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.structural.composite.XMLBuilder#buildVersion(java.lang.String)
	 */
	@Override
	public void buildVersion(String xmlVersion) {
		this.sb.append("<?xml version=\"").append(xmlVersion).append("\" encoding=\"UTF-8\"?>\n");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.structural.composite.XMLBuilder#getFormattedXML()
	 */
	@Override
	public String getFormattedXML() {
		return this.sb.toString();
	}
}
