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

import com.acme.utils.SerializationUtils;

/**
 * The class ClassicSingleton demonstrates the problem we have when serializing
 * and then de-serializing a singleton.
 *
 * @see SerializeableSingleton for a solution
 */
public class ClassicSingleton implements Serializable {

	private static final long serialVersionUID = 1800974621804854072L;

	// private constructor prevents instantiation from other classes
	private ClassicSingleton() {
		super();
	}

	/**
	 * ClassicSingletonHolder is loaded on the first execution of
	 * ClassicSingletonHolder.getInstance() or the first access to
	 * ClassicSingletonHolder.INSTANCE, not before.
	 */
	private static class ClassicSingletonHolder {

		private static final ClassicSingleton INSTANCE = new ClassicSingleton();
	}

	public static ClassicSingleton getInstance() {
		return ClassicSingletonHolder.INSTANCE;
	}

	public static void main(String[] args) throws Exception {
		ClassicSingleton source = ClassicSingleton.getInstance();
		ClassicSingleton copy = ClassicSingleton.getInstance();
		System.out.printf("source == copy? %s%n", (source == copy));
		/*
		 * but we do have a problem with serialization...
		 */
		SerializationUtils.serialize(source, "singleton.ser");
		copy = SerializationUtils.deserialize("singleton.ser");
		System.out.printf("after serialization: source == copy? %s%n", (source == copy));
	}
}
