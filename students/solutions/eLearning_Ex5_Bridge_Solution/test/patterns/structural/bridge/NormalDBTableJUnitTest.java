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
package patterns.structural.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class NormalDBTableJUnitTest is a sanity JUnit test case to test the
 * functionality of the {@link NormalDBTable} class.
 */
public class NormalDBTableJUnitTest {

    private DataMap<Integer, String> dataMap = null;
    private AbstractDBTable<Integer, String> table = null;

    /**
     * Setup.
     */
    @BeforeEach
    public void beforeEach(TestInfo info) {
	System.out.printf("entering %s%n", info.getDisplayName());
	dataMap = new HashDataMap<>();
	dataMap.put(1, "one");
	dataMap.put(2, "two");
	dataMap.put(3, "three");
	table = new NormalDBTable<>(dataMap);
    }

    @Test
    @DisplayName("test create normal DB table")
    public void testNotNullNormalDBTable() {
	assertNotNull(dataMap);
	assertNotNull(table);
    }

    @Test
    @DisplayName("test get implementation")
    public void testGetImpl() {
	DataMap<Integer, String> actual = table.getImpl();
	assertNotNull(actual);
	assertEquals(dataMap, actual);
	assertSame(dataMap, actual);
    }

    @Test
    @DisplayName("test select existing key")
    public void testSelectExistingKey() {
	Integer id = 1;
	String expected = "one";
	String actual = table.select(id);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test select none existing key")
    public void testSelectNoneExistingKey() {
	Integer id = 4;
	String expected = null;
	String actual = table.select(id);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test insert none existing key")
    public void testInsertNoneExistingKey() {
	Integer id = 4;
	String expected = "four";
	table.insert(id, expected);
	String actual = table.select(id);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test insert existing key")
    public void testInsertExistingKey() {
	Integer id = 1;
	String expected = "four";
	table.insert(id, expected);
	String actual = table.select(id);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test update existing key")
    public void testUpdateExistingKey() {
	Integer id = 1;
	String data = "two";
	String expected = "three";
	table.insert(id, data);
	table.update(id, expected);
	String actual = table.select(id);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test update none existing key")
    public void testUpdateNoneExistingKey() {
	Integer id = 5;
	String data = "five";
	String expected = "six";
	table.insert(id, data);
	table.update(id, expected);
	String actual = table.select(id);
	assertEquals(expected, actual);
    }
}
