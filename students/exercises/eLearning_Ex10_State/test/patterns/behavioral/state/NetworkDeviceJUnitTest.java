/*
 * Copyright (C) 2009 - 2020 T.N.Silverman, All rights reserved.
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
	@DisplayName("test abnormal state transition")
	public void testAbnormalStateTransition() {
		device.enable();
		device.transmit();
		device.suspend();
		// this should throw
		assertThrows(UnsupportedOperationException.class, () -> device.receive());
	}
}
