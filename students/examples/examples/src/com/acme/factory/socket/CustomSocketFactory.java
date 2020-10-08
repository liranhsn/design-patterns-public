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
package com.acme.factory.socket;

import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * A factory for creating Socket objects.
 */
public class CustomSocketFactory {

	public static final int TIMEOUT = 3000;
	public static final int BUFFER_SIZE = 16 * 1024 * 1024;
	private String address;
	private int port;;

	/*
	 * Instantiates a new socket factory.
	 */
	public CustomSocketFactory(String address, int port) {
		super();
		this.address = address;
		this.port = port;
	}

	/*
	 * Creates a new Socket object.
	 */
	public Socket createSocket() throws SocketException {
		Socket socket = null;
		System.out.printf("factory creating socket for address %s:%d...%n", address, port);
		try {
			socket = new Socket(InetAddress.getByName(address), port);
		} catch (Exception ex) {
			throw new SocketException(ex.getMessage());
		}
		socket.setSoTimeout(TIMEOUT);
		socket.setReuseAddress(true);
		socket.setSendBufferSize(BUFFER_SIZE);
		return socket;
	}
}
