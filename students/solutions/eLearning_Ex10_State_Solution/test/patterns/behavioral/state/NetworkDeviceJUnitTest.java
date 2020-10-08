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
package patterns.behavioral.state;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class NetworkDeviceJUnitTest is a sanity JUnit test case for the
 * {@link NetworkDevice} class functionality.
 */
public class NetworkDeviceJUnitTest {

	private NetworkDevice device = null;

	@BeforeEach
	public void beforeEach(TestInfo info) throws Exception {
		System.out.printf("entering %s%n", info.getDisplayName());
		device = new NetworkDevice();
	}

	@Test
	@DisplayName("test enabled state")
	public void testEnable() {
		device.enable();
		assertEquals(EnabledState.class, device.currentState.getClass());
	}

	@Test
	@DisplayName("test disabled state")
	public void testDisable() {
		device.enable();
		device.disable();
		assertEquals(DisabledState.class, device.currentState.getClass());
	}

	@Test
	@DisplayName("test transmit state")
	public void testTransmit() {
		device.enable();
		device.transmit();
		assertEquals(EnabledState.class, device.currentState.getClass());
	}

	@Test
	@DisplayName("test receive state")
	public void testReceive() {
		device.enable();
		device.receive();
		assertEquals(EnabledState.class, device.currentState.getClass());
	}

	@Test
	@DisplayName("test suspend state")
	public void testSuspend() {
		device.enable();
		device.suspend();
		assertEquals(SuspendedState.class, device.currentState.getClass());
	}

	@Test
	@DisplayName("test resume state")
	public void testResume() {
		device.enable();
		device.suspend();
		device.resume();
		assertEquals(EnabledState.class, device.currentState.getClass());
	}

	@Test
	@DisplayName("test norma state transition")
	public void testNormalStateTransition() {
		device.enable();
		assertEquals(EnabledState.class, device.currentState.getClass());
		device.transmit();
		assertEquals(EnabledState.class, device.currentState.getClass());
		device.suspend();
		assertEquals(SuspendedState.class, device.currentState.getClass());
		device.resume();
		assertEquals(EnabledState.class, device.currentState.getClass());
		device.receive();
		assertEquals(EnabledState.class, device.currentState.getClass());
		device.disable();
		assertEquals(DisabledState.class, device.currentState.getClass());
	}

	@Test
	@DisplayName("test abnorma state transition")
	public void testAbnormalStateTransition() {
		device.enable();
		device.transmit();
		device.suspend();
		// this should throw
		assertThrows(UnsupportedOperationException.class, () -> device.receive());
	}
}
