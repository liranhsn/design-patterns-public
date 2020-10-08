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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

/**
 * The class Test tests the socket factory via a valid server that accepts
 * socket connections and echos messages sent to it by clients
 */
public class Test {

	/**
	 * A function to return a runnable server to listen on a port and echo messages
	 */
	static Function<Integer, Runnable> server = port -> () -> {
		System.out.printf("server listening on port %d%n", port);
		try (ServerSocket server = new ServerSocket(port);
				Socket socket = server.accept();
				DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));) {
			System.out.printf("server accepted connection from %s:%d%n", socket.getLocalAddress(), socket.getLocalPort());
			String line = "";
			while (!line.equals("quit")) { // reads message from client until "quit" is sent
				line = in.readUTF();
				System.out.printf("server echos: %s%n", line);
				if (line.equals("quit"))
					System.out.printf("bye bye%n");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	};

	public static void main(String[] args) throws Exception {
		int port = 5000;
		String address = "127.0.0.1";
		new Thread(server.apply(port)).start(); // start an echo server in it's own thread
		CustomSocketFactory factory = new CustomSocketFactory(address, port);
		try (Socket socket = factory.createSocket();
				/* Socket socket = new CustomSocket("localhost",port); */
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
			String message = "";
			while (!message.equals("quit")) {
				System.out.println("type a message for the server (or 'quit'): ");
				message = input.readLine();
				out.writeUTF(message);
			}
		}
	}
}
