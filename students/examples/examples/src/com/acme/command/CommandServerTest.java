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

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

public class CommandServerTest {

	public static int NUM_OF_CLIENTS = 3;
	public static int NUM_OF_COMMANDS = 10;

	@SuppressWarnings("unused")
	private static Function<String, Command> echoCommandFunc = msg -> () -> System.out
			.println(Thread.currentThread().getName() + " printing " + msg);

	public static void main(String[] args) throws Exception {
		ExecutorService threadPool = null;
		BlockingQueue<Command> queue = new LinkedBlockingQueue<Command>();
		Server server = new Server(queue);
		threadPool = Executors.newCachedThreadPool();
		threadPool.submit(server);
		for (int i = 0; i < NUM_OF_CLIENTS; i++) {
			String name = String.format("Client-%s", i + 1);
			Client client = new Client(name, getRandomCommandsList(name), queue);
			threadPool.submit(client);
		}
		threadPool.shutdown();
	}

	private static List<Command> getRandomCommandsList(String name) {
		Random random = new Random(System.currentTimeMillis() / 2);
		List<Command> commands = new LinkedList<Command>();
		commands.add(new BellCommand("churchbell.wav", name));
		for (int i = 0; i < NUM_OF_COMMANDS; i++) {
			Command command;
			if (random.nextInt(2) > 0) {
				String fileName = "file" + i + "ToDeleteBy" + name + ".txt";
				File file = new File(fileName);
				try {
					file.createNewFile();
				} catch (IOException ioex) {
					ioex.printStackTrace();
				}
				command = new DelCommand(fileName);
			} else {
				// Using a lambda example
				/*
				 * command = echoCommandFunc.apply(String.format("document-%d for '%s'...",(i +
				 * 1),name));
				 */
				command = new PrintCommand("document-" + (i + 1) + " for '" + name + "'...");
			}
			commands.add(command);
		}
		Collections.shuffle(commands);
		return commands;
	}
}
