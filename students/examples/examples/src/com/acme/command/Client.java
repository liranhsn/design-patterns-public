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

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Client implements Runnable {

	private final String name;
	private final BlockingQueue<Command> queue;
	private List<Command> commands;
	private Random random = new Random(System.currentTimeMillis() / 2);
	private int cmdIdx = 0;

	Client(String name, List<Command> commands, BlockingQueue<Command> queue) {
		this.queue = queue;
		this.name = name;
		this.commands = commands;
		cmdIdx = commands.size() - 1;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		try {
			while (!commands.isEmpty()) {
				Thread.sleep(random.nextInt(500) + 1);
				Command command = cmdIdx >= 0 ? commands.remove(cmdIdx--) : null;
				System.out.println(Thread.currentThread().getName() + " queing " + command.getClass().getSimpleName() + "...");
				queue.put(command);
			}
		} catch (InterruptedException ignore) {
		}
	}

}
