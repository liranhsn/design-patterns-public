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
package com.acme.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class ObjectCloner {

	// private to avoid instantiation
	private ObjectCloner() {
	}

	// returns a deep copy of an object
	@SuppressWarnings("unchecked")
	static public <T> T deepCopy(T obj) throws Exception {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 ObjectOutputStream out = new ObjectOutputStream(bos)) {
			/*
			 * Serialize the object by writing it to an ObjectOutputStream
			 */
			out.writeObject(obj);
			out.flush();
			/*
			 * Wrap the an array of bytes from the ByteArrayOutputStream in a ByteArrayInputStream
			 * and then wrap it in an ObjectInputStream
			 */
			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			/*
			 * De-Serialize the object by reading it from the ObjectInputStream
			 */
			T result = (T) in.readObject(); // de-serialize the object
			in.close();
			return result;
		} catch (Exception e) {
			System.out.println("Exception in ObjectCloner = " + e);
			throw (e);
		}
	}

	/*
	 * object cloner demo - serializes and deserializes an Employee
	 */
	public static void main(String[] args) throws Exception {
		TestEmployee source = new TestEmployee("Tomer", "Silverman", LocalDate.of(1981, 2, 6), 123456L);
		TestEmployee copy = ObjectCloner.deepCopy(source);
		System.out.printf("source == null? %s%n", source == null);
		System.out.printf("copy == null? %s%n", copy == null);
		System.out.printf("copy.getClass() == Employee.class? %s%n", copy.getClass() == TestEmployee.class);
		System.out.printf("source == copy? %s%n", source == copy);
		System.out.printf("source.empId == copy.empId? %s%n", source.empId == copy.empId);
		System.out.printf("source.firstName == copy.firstName? %s%n", source.firstName == copy.firstName);
		System.out.printf("source.lastName == copy.lastName? %s%n", source.lastName == copy.lastName);
		System.out.printf("source.datOfBirth == copy.source.datOfBirth? %s%n", source.dateOfBirth == copy.dateOfBirth);
	}

	static class TestEmployee implements Serializable {

		private static final long serialVersionUID = 5483727734057822995L;
		private String firstName;
		private String lastName;
		private LocalDate dateOfBirth;
		private Long empId;

		public TestEmployee(String firstName, String lastName, LocalDate dateOfBirth, Long empId) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.empId = empId;
		}

	}
}