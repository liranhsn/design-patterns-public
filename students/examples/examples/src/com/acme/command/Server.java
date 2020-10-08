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
package com.acme.command;

import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {

	private final BlockingQueue<Command> queue;
	private boolean quit = false;

	Server(BlockingQueue<Command> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " IS RUNNING SERVER");
		while (!quit) {
			Command command;
			try {
				command = queue.take();
				System.out.println(Thread.currentThread().getName() + " server executing " + command.getClass());
				consume(command);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void consume(Command command) {
		if (command == null) {
			shutdown();
		} else {
			command.execute();
		}
	}

	public void shutdown() {
		System.out.println("Server.shutdown() -> shutting down gracefully...");
		quit = true;
	}
}