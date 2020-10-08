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

/**
 * The Class NetworkDevice plays the role of the context in the state design
 * pattern. It holds an AbstractDeviceState {@code currentState} to which it
 * delegates the inherited operations of {@code INetworkDevice}
 */
public class NetworkDevice implements INetworkDevice {

	protected AbstractDeviceState currentState; // The current state.
	public final AbstractDeviceState DISABLED_STATE = new DisabledState(this);
	public final AbstractDeviceState ENABLED_STATE = new EnabledState(this);
	public final AbstractDeviceState SUSPENDED_STATE = new SuspendedState(this);

	public NetworkDevice() {
		super();
		currentState = DISABLED_STATE;
	}

	public void setState(AbstractDeviceState newState) {
		currentState = newState;
		System.out.println("State changed to: " + newState.getClass().getSimpleName());
	}

	@Override
	public void enable() {
		// enable the current state - e.g. currentState.enable();
		currentState.enable();
	}

	@Override
	public void disable() {
		// disable the current state
		currentState.disable();
	}

	@Override
	public void transmit() {
		// transmit from the current state
		currentState.transmit();
	}

	@Override
	public void receive() {
		// receive from the current state
		currentState.receive();
	}

	@Override
	public void suspend() {
		// suspend the current state
		currentState.suspend();
	}

	@Override
	public void resume() {
		// resume the current state
		currentState.resume();
	}

	/**
	 * Test the device.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			NetworkDevice device = new NetworkDevice();
			device.enable();
			device.transmit();
			device.suspend();
			device.receive(); // Is this right? What do you get here???
			device.resume();
			device.disable();
			System.out.println("--- Done ---");
		} catch (Exception ex) {
			System.err.printf("Error: %s%n", ex.getMessage());
			ex.printStackTrace(System.err);
		}
	}
}
