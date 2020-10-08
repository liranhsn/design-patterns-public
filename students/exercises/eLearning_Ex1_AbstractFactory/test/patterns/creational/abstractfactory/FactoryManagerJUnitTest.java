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
