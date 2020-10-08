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
 * The Interface INetworkDevice defines the basic functionality for a network
 * device machine. (enable, disable, transmit, receive, suspend, resume)
 */
public interface INetworkDevice {

	/**
	 * Enable a network device
	 *
	 * Note: An enabled device will support disable, receive, transmit and suspend
	 */
	public void enable();

	/**
	 * Disable a network device
	 *
	 * Note: A disabled device will only support enable
	 */
	public void disable();

	/**
	 * Transmit to a network device
	 */
	public void transmit();

	/**
	 * Receive from network device
	 */
	public void receive();

	/**
	 * Suspend a network device
	 *
	 * Note: A suspended network device will only support resume and disable
	 */
	public void suspend();

	/**
	 * Resume a network device
	 */
	public void resume();
}
