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

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The class CustomeSocket is a customized socket. This is usable, but it is an
 * anti pattern as there's no good way to enforce it's usage
 */
public class CustomSocket extends Socket {

	public static final int TIMEOUT = 3000;
	public static final int M = 1024;
	public static final int KB = 1024;
	public static final int BUFFER_SIZE = 16 * KB * M;

	public CustomSocket(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
		setSoTimeout(TIMEOUT);
		setReuseAddress(true);
		setSendBufferSize(BUFFER_SIZE);
	}
}
