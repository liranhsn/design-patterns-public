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

import com.acme.utils.SerializationUtils;

/**
 * The class SerializeableSingleton is a singleton with a tweak to it's
 * serialization process. Using the hook readObject(), we ensure that after
 * serialization and de-serialization, there's still just one instance of this
 * class.
 */
public class SerializeableSingleton implements Serializable {

	private static final long serialVersionUID = 1800974621804854072L;

	/*
	 * private constructor prevents instantiation from other classes
	 */
	private SerializeableSingleton() {
		super();
	}

	/**
	 * ClassicSingletonHolder is loaded on the first execution of
	 * ClassicSingletonHolder.getInstance() or the first access to
	 * ClassicSingletonHolder.INSTANCE - not before.
	 */
	private static class ClassicSingletonHolder {

		private static final SerializeableSingleton INSTANCE = new SerializeableSingleton();
	}

	public static SerializeableSingleton getInstance() {
		return ClassicSingletonHolder.INSTANCE;
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

	/*
	 * run this with readResolve(), then with readResolve() commented out
	 */
	public static void main(String[] args) throws Exception {
		SerializeableSingleton source = SerializeableSingleton.getInstance();
		SerializeableSingleton copy = SerializeableSingleton.getInstance();
		System.out.printf("source == copy? %s%n", (source == copy));
		/*
		 * now we don't have a problem with serialization...
		 */
		SerializationUtils.serialize(source, "singleton.ser");
		copy = SerializationUtils.deserialize("singleton.ser");
		System.out.printf("after serialization: source == copy? %s%n", (source == copy));
	}
}
