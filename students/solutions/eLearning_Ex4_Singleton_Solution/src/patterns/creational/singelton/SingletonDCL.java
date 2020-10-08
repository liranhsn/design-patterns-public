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
package patterns.creational.singelton;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Class SingletonDCL is the singleton pattern that uses the 'volatile'
 * reserved word to declare a private static singleton instance that can be used
 * in conjunction with the DCL (double checked locking) mechanism to synchronize
 * the instance while it's being initialized
 */
public class SingletonDCL {

    private static volatile SingletonDCL INSTANCE;

    /**
     * private constructor
     */
    private SingletonDCL() {
	super();
    }

    /**
     * Gets the single instance of SingletonDCL.
     *
     * @return single instance of SingletonDCL
     */
    public static SingletonDCL getInstance() {
	// Ask the first question in the DCL pattern: is this instance null?
	if (INSTANCE == null) {
	    // if the answer is true, it's time to synchronize on the class
	    synchronized (SingletonDCL.class) {
		// according to the DCL pattern, you now ask the question again
		if (INSTANCE == null) {
		    // if the answer is true - initialize the instance
		    System.out.println("SingletonDCL.getInstance() -> Initializing SingletonDCL");
		    INSTANCE = new SingletonDCL();
		}
	    }
	}
	// return the instance
	return INSTANCE;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	List<Thread> threads = getThreadList(100);
	for (Thread thread : threads) {
	    thread.start();
	}
    }

    /**
     * Gets the thread list.
     *
     * @param numOfThreads the num of threads
     * @return the thread list
     */
    private static List<Thread> getThreadList(int numOfThreads) {
	List<Thread> threads = new LinkedList<Thread>();
	for (int i = 0; i < numOfThreads; i++) {
	    Thread thread = new Thread(() -> {
		try {
		    Thread.sleep(ThreadLocalRandom.current().nextInt(1000) + 1);
		} catch (InterruptedException iex) {
		    System.err.println("Error: " + iex.getMessage());
		}
		System.out.printf("%s running...%n",Thread.currentThread().getName());
		SingletonDCL singletonDCL = SingletonDCL.getInstance();
		if (singletonDCL == null) {
		    System.err.println("Null Instance!!!");
		}
	    });
	    threads.add(thread);
	}
	return threads;
    }
}
