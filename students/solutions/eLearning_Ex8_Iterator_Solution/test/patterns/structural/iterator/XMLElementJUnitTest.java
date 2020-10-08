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
	System.out.printf("entering %s%n",info.getDisplayName());
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
