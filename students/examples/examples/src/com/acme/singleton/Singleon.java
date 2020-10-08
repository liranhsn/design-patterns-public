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
package com.acme.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.acme.utils.SerializationUtils;

/**
 * The class Singleton demonstrates the DCL (double check locking) pattern
 * singleton
 */
public class Singleon implements Serializable {

	private static final long serialVersionUID = 3789513086907164756L;
	private static volatile Singleon INSTANCE = null;

	/*
	 * hidden constructor
	 */
	private Singleon() {
		super();
	}

	public static Singleon getInstance() {
		if (INSTANCE == null) {
			synchronized (Singleon.class) {
				if (INSTANCE == null) {
					INSTANCE = new Singleon();
				}
			}
		}
		return INSTANCE;
	}

	/**
	 * This solves our problem. Serialization has a special hook it uses - a private
	 * method on the class being instantiated called readResolve() - which is meant
	 * to supply a 'hook' for a class developer to ensure that they have a say in
	 * what object is returned by serialization. Oddly enough, readResolve() is not
	 * static, but is instead invoked on the new instance just created by the
	 * serialization.
	 *
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return getInstance();
	}

	public static void main(String[] args) throws Exception {
		ExecutorService pool = Executors.newCachedThreadPool();
		pool.invokeAll(getCallables(Singleon.getInstance(), 10));
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.SECONDS);
		// test serialization / de-serialization
		Singleon source = Singleon.getInstance();
		SerializationUtils.serialize(source, "volatile.ser");
		Singleon copy = SerializationUtils.deserialize("volatile.ser");
		System.out.printf("after serialization: source == copy? %s%n", (source == copy));
	}

	/*
	 * generate a list of Callables where each callable instantiates a singleton and
	 * compares it's reference with the given singleton
	 */
	private static List<Callable<Boolean>> getCallables(final Singleon singleton, int numOfThreads) {
		List<Callable<Boolean>> callables = new LinkedList<>();
		for (int i = 0; i < numOfThreads; i++) {
			callables.add(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					boolean result = false;
					try {
						Thread.sleep(ThreadLocalRandom.current().nextInt(1000) + 1);
						result = Singleon.getInstance() == singleton;
						System.out.println(Thread.currentThread().getName() + " -> Singleon.getInstance() == singleton: " + result);
						if (!result)
							System.err.println("Unexpected non-equal reference!");
					} catch (InterruptedException iex) {
						System.err.println("Error: " + iex.getMessage());
					}
					return result;
				}
			});
		}
		return callables;
	}
}
