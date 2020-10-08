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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class XMLDirectorJUnitTest is a sanity JUnit test for testing the
 * functionality of the {@link FormattedXMLBuilder} class. FYI: The expected
 * output of the tested builder is saved to a file named 'courses.xml' under the
 * source 'test' folder. This file is loaded by one of the tests and compared to
 * the output of the builder.
 */
public class XMLDirectorJUnitTest {

	private XMLDocument document = null;
	private XMLBuilder builder = new FormattedXMLBuilder();
	XMLDirector director = new XMLDirector(builder);

	@BeforeEach
	public void beforeEach(TestInfo info) {
		System.out.printf("entering %s%n", info.getDisplayName());
		XMLElement root = new XMLElement("root");
		// prepare a Course child
		XMLElement course = new XMLElement("course");
		course.addElement(new XMLElement("name").setData("Design Patterns"));
		course.addElement(new XMLElement("duration").setData("5 days"));
		root.addElement(course);
		// prepare another Course element
		course = new XMLElement("course");
		course.addElement(new XMLElement("name").setData("Java Programming"));
		course.addElement(new XMLElement("duration").setData("5 days"));
		root.addElement(course);
		// prepare an XML Document
		document = new XMLDocument(root);
		// set the version
		document.setVersion("1.0");
	}

	@Test
	@DisplayName("test xml objects created")
	public void testXMLDirector() {
		assertNotNull(document);
		assertNotNull(builder);
		assertNotNull(director);
	}

	@Test
	@DisplayName("test build version")
	public void testBuildVersion() {
		String version = "1.0";
		builder.buildVersion(version);
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		String actual = builder.getFormattedXML();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("test build document")
	public void testBuildDocument() {
		director.build(document);
		String actual = builder.getFormattedXML();
		String expected = loadXmlFile("courses.xml");
		System.out.println("============ Comparing ================");
		System.out.println(expected);
		System.out.println("============ With =====================");
		System.out.println(actual);
		System.out.println("=======================================");
		assertEquals(expected, actual);
	}

	/**
	 * Load expected xml.
	 *
	 * @param fileName the file name
	 * @return the string
	 */
	private String loadXmlFile(String fileName) {
		StringBuilder out = new StringBuilder();
		try (InputStream in = XMLDirectorJUnitTest.class.getClassLoader().getResourceAsStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
			String line;
			while ((line = reader.readLine()) != null)
				out.append(line).append("\n");
			reader.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		return out.toString();
	}
}
