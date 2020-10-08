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
 * The Class SingletonStatic is a vanilla singleton pattern. It works on all
 * JDK version and is the oldest way to generate a singleton. The principle is
 * to create a static inner class holding a reference to the singleton INSTANCE.
 * The INSTANCE is initialized the first time it's accessed or the first time
 * the wrapper class is accessed.
 */
public class SingletonStatic {

    /**
     * Gets the single instance of SingletonStatic.
     *
     * @return single instance of SingletonStatic
     */
    public static SingletonStatic getInstance() {
        return SingletonClassicHolder.INSTANCE;
    }

    /**
     * Instantiates a new singleton classic.
     */
    private SingletonStatic() {
        super();
    }

    /**
     * The Class SingletonClassicHolder.
     */
    static class SingletonClassicHolder {
        private static SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static void main(String[] args) {
	getThreadList(100).forEach(Thread::start);
    }

    /**
     * Gets a thread list.
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
                SingletonStatic singletonStatic = SingletonStatic.getInstance();
                if (singletonStatic == null) {
                    System.err.println("Null Instance!!!");
                }
            });
            threads.add(thread);
        }
        return threads;
    }
}
