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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class ReadOnlyDBTableJUnitTest is a sanity JUnit test case to test the
 * functionality of the {@link ReadOnlyDBTable} class.
 */
public class ReadOnlyDBTableJUnitTest {

    private DataMap<Integer, String> dataMap = null;
    private AbstractDBTable<Integer, String> table = null;

    @BeforeEach
    public void beforeEach(TestInfo info) {
	System.out.printf("entering %s%n", info.getDisplayName());
	dataMap = new HashDataMap<Integer, String>();
	dataMap.put(1, "one");
	dataMap.put(2, "two");
	dataMap.put(3, "three");
	table = new ReadOnlyDBTable<Integer, String>(dataMap);
    }

    @Test
    @DisplayName("test create read only DB table")
    public void testNotNullReadOnlyDBTable() {
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
    @DisplayName("test insert into read obly table and throw")
    public void testInsert() {
	assertThrows(UnsupportedOperationException.class, () -> table.insert(10, "test"));
    }

    @Test
    @DisplayName("test update read obly table and throw")
    public void testUpdate() {
	assertThrows(UnsupportedOperationException.class, () -> table.update(1, "test"));
    }
}
