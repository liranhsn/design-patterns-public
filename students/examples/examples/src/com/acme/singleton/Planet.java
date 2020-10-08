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

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.acme.utils.SerializationUtils;

/**
 * The enum Planet demonstrates that Java enum are always singletons
 */
public enum Planet implements Serializable {

	MERCURY(3.303e+23, 2.4397e6), VENUS(4.869e+24, 6.0518e6), EARTH(5.976e+24, 6.37814e6), MARS(6.421e+23, 3.3972e6),
	JUPITER(1.9e+27, 7.1492e7), SATURN(5.688e+26, 6.0268e7), URANUS(8.686e+25, 2.5559e7), NEPTUNE(1.024e+26, 2.4746e7);

	private final double mass; // in kilograms
	private final double radius; // in meters

	Planet(double mass, double radius) {
		// System.out.println("Planet.<init> -> initalizing " + name());
		this.mass = mass;
		this.radius = radius;
	}

	double mass() {
		return mass;
	}

	double radius() {
		return radius;
	}

	// universal gravitational constant (m3 kg-1 s-2)
	public static final double G = 6.67300E-11;

	double surfaceGravity() {
		return G * mass / (radius * radius);
	}

	double surfaceWeight(double otherMass) {
		return otherMass * surfaceGravity();
	}

	public static void main(String[] args) throws Exception {
		String earthMass = "100.00";
		if (args.length != 1) {
			System.out.println("Usage: java Planet <earth_weight>");
			System.out.println("Using default earth mass of " + earthMass + " KG.");
		} else
			earthMass = args[0];
		double earthWeight = Double.parseDouble(earthMass);
		double mass = earthWeight / EARTH.surfaceGravity();
		// loop through the planets and instantiate 10 singletons for comparison
		for (Planet p : Planet.values()) {
			System.out.printf("Your weight on %s is %f%n", p, p.surfaceWeight(mass));
			List<Callable<Boolean>> tasks = getCallables(p, 10);
			ExecutorService pool = Executors.newCachedThreadPool();
			pool.invokeAll(tasks);
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.SECONDS);
		}
		Planet earth = Planet.EARTH;
		SerializationUtils.serialize(earth, "earth.ser");
		Planet parallel = SerializationUtils.deserialize("earth.ser");
		System.out.printf("after serialization: earth == parallel? %s%n", (earth == parallel));
	}

	/*
	 * generate a list of callables where each callable instantiates a singleton
	 * enum and compares it's reference to a given singleton
	 */
	private static List<Callable<Boolean>> getCallables(final Planet singleton, int numOfThreads) {
		final Random rand = new Random(System.currentTimeMillis() / 2);
		List<Callable<Boolean>> callables = new LinkedList<>();
		for (int i = 0; i < numOfThreads; i++) {
			callables.add(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					boolean result = false;
					try {
						Thread.sleep(rand.nextInt(100) + 1);
						Planet instance = Planet.valueOf(singleton.name());
						result = (instance == singleton);
						System.out.println(Thread.currentThread().getName() + " -> instance == singleton: " + result);
						if (!result) {
							System.err.println("Unexpected non-equal reference!");
							System.exit(-19);
						}
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