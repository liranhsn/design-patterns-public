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
package com.acme.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * The Class SerializationUtils is a utility class with two simple methods to
 * serialize and de-serialize objects to a file
 */
public class SerializationUtils {

	/**
	 * Hidden constructor
	 */
	private SerializationUtils() {
		super();
	}

	/**
	 * Serialize.
	 *
	 * @param <T>    the generic type
	 * @param object the object
	 * @param file   the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static <T> void serialize(T object, String file) throws IOException {
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(object);
		out.close();
	}

	/**
	 * Deserialize.
	 *
	 * @param <T>  the generic type
	 * @param file the file
	 * @return the t
	 * @throws IOException            Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static <T> T deserialize(String file) throws IOException, ClassNotFoundException {
		ObjectInput in = new ObjectInputStream(new FileInputStream(file));
		@SuppressWarnings("unchecked")
		T object = (T) in.readObject();
		in.close();
		return object;
	}
}
