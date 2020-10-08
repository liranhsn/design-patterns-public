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
package patterns.creational.abstractfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import patterns.creational.carparts.Engine;
import patterns.creational.carparts.FamilyCarEngine;
import patterns.creational.carparts.FamilyCarHood;
import patterns.creational.carparts.FamilyCarWheel;
import patterns.creational.carparts.Hood;
import patterns.creational.carparts.MiniVanEngine;
import patterns.creational.carparts.MiniVanHood;
import patterns.creational.carparts.MiniVanWheel;
import patterns.creational.carparts.Wheel;

/**
 * The Class FactoryManagerJUnitTest is a sanity JUnit test for the
 * functionality of the FactoryManager class
 */
public class FactoryManagerJUnitTest {

    @BeforeEach
    public void beforeEach(TestInfo info) throws Exception {
	System.out.printf("entering %s%n", info.getDisplayName());
    }

    @Test
    @DisplayName("test get factory instance")
    public void testGetFactory() {
	assertNotNull(FactoryManager.getInstance().getFactory());
    }

    @Test
    @DisplayName("test factory instance is singleton")
    public void testGetSameFactory() {
	AbstractCarFactory expected = FactoryManager.getInstance().getFactory();
	AbstractCarFactory actual = FactoryManager.getInstance().getFactory();
	assertSame(expected, actual);
    }

    @Test
    @DisplayName("test create car engine")
    public void testCreateEngine() {
	Engine engine = FactoryManager.getInstance().getFactory().createEngine();
	assertNotNull(engine);
	assertNotNull(engine.getId());
	assertTrue(engine instanceof FamilyCarEngine || engine instanceof MiniVanEngine);
    }

    @Test
    @DisplayName("test create car wheel")
    public void testCreateWheel() {
	Wheel wheel = FactoryManager.getInstance().getFactory().createWheel();
	assertNotNull(wheel);
	assertNotNull(wheel.getId());
	assertTrue(wheel instanceof FamilyCarWheel || wheel instanceof MiniVanWheel);
    }

    @Test
    @DisplayName("test create car hood")
    public void testCreateHood() {
	Hood hood = FactoryManager.getInstance().getFactory().createHood("red");
	assertNotNull(hood);
	assertNotNull(hood.getId());
	assertTrue(hood instanceof FamilyCarHood || hood instanceof MiniVanHood);
	assertEquals("red", hood.getColor());
    }
}
