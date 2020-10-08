/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation  and/or other materials provided with the distribution.
 * 3. Neither the names of the copyright holders nor the names of any
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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
