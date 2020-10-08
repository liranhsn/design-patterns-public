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
 * The Class EnabledState represents a network device in the enabled mode. It's
 * a concrete state in the state design pattern. It can be disabled, suspended
 * transmitted from, and used for receiving
 */
public class EnabledState extends AbstractDeviceState {

	/**
	 * Instantiates a new enabled state.
	 *
	 * @param networkDevice the network device
	 */
	public EnabledState(NetworkDevice networkDevice) {
		super(networkDevice);
	}

	public void disable() {
		/*
		 * You'll want to implement disable by setting the networkDevice to the disabled
		 * state. Such utility state exists as a member in the NetworkDevice class, so
		 * basically, what you need is to do something like
		 * networkDevice.setState(networkDevice.DISABLED_STATE)
		 */
		networkDevice.setState(networkDevice.DISABLED_STATE);
	}

	public void suspend() {
		/*
		 * You'll want to implement suspend by setting the networkDevice to the
		 * suspended state. Such utility state exists as a member in the NetworkDevice
		 * class, so basically, what you need is to do something like
		 * networkDevice.setState(networkDevice.SUSPENDED_STATE)
		 */
		networkDevice.setState(networkDevice.SUSPENDED_STATE);
	}

	public void transmit() {
		/*
		 * You'll want to implement transmit by transmitting some data... e.g. by
		 * printing some data to the console for in this exercise
		 */
		System.out.println(">>> trasmitting... beep... beep... beep...");
	}

	public void receive() {
		/*
		 * You'll want to implement receive by receiving some data... e.g. by printing
		 * some data to the console for in this exercise
		 */
		System.out.println("<<< ...receiving ...beep ...beep ...beep");
	}
}
