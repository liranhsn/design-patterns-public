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
 * The Class DisabledState represents a disabled network device. It's a concrete
 * state in the state design pattern. It can only be enabled
 */
public class DisabledState extends AbstractDeviceState {

	public DisabledState(NetworkDevice device) {
		super(device);
	}

	public void enable() {
		/*
		 * You'll want to implement enable by setting the networkDevice to the enabled
		 * state. Such utility state exists as a member in the NetworkDevice class, so
		 * basically, what you need is to do something like
		 * networkDevice.setState(networkDevice.ENABLED_STATE)
		 */
		networkDevice.setState(networkDevice.ENABLED_STATE);
	}
}
