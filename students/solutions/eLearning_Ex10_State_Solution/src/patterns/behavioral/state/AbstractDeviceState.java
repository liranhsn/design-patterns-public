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
 * The Class AbstractDeviceState represents an abstraction of the state in the
 * state design pattern. It defines the default behavior of a network device but
 * leaves concrete implementations to sub-classes
 */
public abstract class AbstractDeviceState implements INetworkDevice {

	protected NetworkDevice networkDevice;

	public AbstractDeviceState(NetworkDevice networkDevice) {
		this.networkDevice = networkDevice;
	}

	public void enable() {
		/*
		 * create a default implementation of the INetworkDevice operation enable. For
		 * example, throw an unsupported operation exception telling the client this is
		 * not a supported operation for the sub-class
		 */
		throw new UnsupportedOperationException("enable not applicable for: " + this.getClass().getSimpleName());
	}

	// create default implementations of the other INetworkDevice
	// operations. They should'nt really do anything except throw an exception
	// indicate to the client that it's useless to use the default methods
	//
	// Note the current implementations are not bad. You can also just leave the
	// methods blank, but in this case clients will not get indications about
	// what happened when an irrelevant method was called on the state

	@Override
	public void disable() {
		throw new UnsupportedOperationException("disable not applicable for: " + this.getClass().getSimpleName());
	}

	@Override
	public void transmit() {
		throw new UnsupportedOperationException("transmit not applicable for: " + this.getClass().getSimpleName());
	}

	@Override
	public void receive() {
		throw new UnsupportedOperationException("receive not applicable for: " + this.getClass().getSimpleName());
	}

	@Override
	public void suspend() {
		throw new UnsupportedOperationException("suspend not applicable for: " + this.getClass().getSimpleName());
	}

	@Override
	public void resume() {
		throw new UnsupportedOperationException("resume not applicable for: " + this.getClass().getSimpleName());
	}
}
