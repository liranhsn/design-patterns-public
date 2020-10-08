/*
 * Copyright (C) 2009 - 2020 T.N.Silverman, All rights reserved
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
package patterns.structural.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class XMLElementJUnitTest is a sanity JUnit test case to test the
 * iteration abilities of the {@link XMLElement} class
 */
public class XMLElementJUnitTest {

	private XMLElement root = null;

	@BeforeEach
	public void beforeEach(TestInfo info) throws Exception {
		System.out.printf("entering %s%n", info.getDisplayName());
		root = new XMLElement("Root").setData("root");
		XMLElement courseElement = new XMLElement("course").setData("Old Course");
		courseElement.addElement(new XMLElement("name").setData("Design Patterns"));
		courseElement.addElement(new XMLElement("duration").setData("5 days"));
		root.addElement(courseElement);
		root.addElement(new XMLElement("dummy").setData("Dummy"));
		courseElement = new XMLElement("course").setData("New Course");
		courseElement.addElement(new XMLElement("name").setData("Java Programming"));
		courseElement.addElement(new XMLElement("duration").setData("5 days"));
		root.addElement(courseElement);
	}

	@Test
	@DisplayName("test get iterator")
	public void testGetIterator() {
		assertNotNull(root);
		Iterator<XMLElement> iter = root.iterator();
		assertNotNull(iter);
		assertTrue(iter.hasNext());
	}

	@Test
	@DisplayName("test get named iterator")
	public void testGetNamedIterator() {
		assertNotNull(root);
		Iterator<XMLElement> iter = root.iterator("course");
		assertNotNull(iter);
		assertTrue(iter.hasNext());
	}

	@Test
	@DisplayName("test iterate root")
	public void testIterateXMLElement() {
		try {
			XMLElement.iterate(root);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	@DisplayName("test iterate by element name")
	public void testIterateXMLElementByName() {
		try {
			XMLElement.iterate(root, "course");
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	@DisplayName("test iterator iteration")
	public void testIteratorIteration() {
		// expecting three elements under root - Course,Dummy,Course
		int expected = 3;
		Iterator<XMLElement> iter = root.iterator();
		int actual = 0;
		for (; iter.hasNext(); actual++) {
			XMLElement element = iter.next();
			assertNotNull(element.getName());
			assertNotNull(element.getData());
		}
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("test named iterator iteration")
	public void testNamedIteratorIteration() {
		// expecting two Course elements under root
		int expected = 2;
		Iterator<XMLElement> iter = root.iterator("course");
		int actual = 0;
		for (; iter.hasNext(); actual++) {
			XMLElement element = iter.next();
			assertNotNull(element.getName());
			assertNotNull(element.getData());
		}
		assertEquals(expected, actual);
	}
}
