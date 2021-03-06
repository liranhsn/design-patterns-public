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
package patterns.creational.singelton;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The Class SingletonStaticJUnitTest is a sanity JUnit test to test the
 * functionality of the {@link SingletonStatic} class
 */
public class SingletonStaticJUnitTest {

    @Test
    @DisplayName("test get instance")
    public void testGetInstance() {
	SingletonStatic expected = SingletonStatic.getInstance();
	assertNotNull(expected);
    }

    @Test
    @DisplayName("test get same instance")
    public void testGetSameInstance() {
	SingletonStatic actual = SingletonStatic.getInstance();
	SingletonStatic expected = SingletonStatic.getInstance();
	assertSame(expected, actual);
    }
}
