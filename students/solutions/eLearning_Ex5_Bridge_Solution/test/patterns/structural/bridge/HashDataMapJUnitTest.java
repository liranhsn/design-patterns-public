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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class HashDataMapJUnitTest is a sanity JUnit test case to test the
 * functionality of the default {@link DataMap} implementation (the class
 * {@link HashDataMap} )
 */
public class HashDataMapJUnitTest {

    private DataMap<Integer, String> intMap = null;
    private DataMap<String, String> strMap = null;

    @BeforeEach
    public void beforeEach(TestInfo info) {
	System.out.printf("entering %s%n", info.getDisplayName());
	intMap = new HashDataMap<Integer, String>();
	intMap.put(1, "one");
	intMap.put(2, "two");
	intMap.put(3, "three");
	strMap = new HashDataMap<String, String>();
	strMap.put("1", "one");
	strMap.put("2", "two");
	strMap.put("3", "three");
    }

    @Test
    @DisplayName("test create data map")
    public void testHashDataMap() {
	assertNotNull(intMap);
	assertNotNull(strMap);
    }

    @Test
    @DisplayName("test int map get")
    public void testGetIntMapValue() {
	String expected = "one";
	String actual = intMap.get(1);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test string map get")
    public void testGetStringMapValue() {
	String expected = "one";
	String actual = strMap.get("1");
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test key exists")
    public void testKeyExists() {
	Integer intKey = 1;
	String strKey = "1";
	assertTrue(intMap.keyExists(intKey));
	assertTrue(strMap.keyExists(strKey));
    }

    @Test
    @DisplayName("test key does not exist")
    public void testNoneExistingKeyNotExists() {
	Integer intKey = 10;
	String strKey = "10";
	assertFalse(intMap.keyExists(intKey));
	assertFalse(strMap.keyExists(strKey));
    }

    @Test
    @DisplayName("test put")
    public void testPut() {
	Integer intKey = 11;
	String strKey = "11";
	String value = "eleven";
	intMap.put(intKey, value);
	strMap.put(strKey, value);
	assertTrue(intMap.keyExists(intKey));
	assertTrue(strMap.keyExists(strKey));
	assertNotNull(intMap.get(intKey));
	assertNotNull(strMap.get(strKey));
	assertEquals(value, intMap.get(intKey));
	assertEquals(value, strMap.get(strKey));
    }
}
