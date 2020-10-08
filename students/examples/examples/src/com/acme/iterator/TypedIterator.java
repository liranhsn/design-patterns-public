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
package com.acme.iterator;

import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The class TypedIterator demonstrates a simple iterator implementation. This
 * iterator only returns elements of the same type as the given class.
 */
class TypedIterator implements Iterator<Serializable> {

	private Iterator<Serializable> impl; // Concrete iterator (for arraylist/linkedlist…)
	private Class<?> clazz; // Return objects of this type (skip the rest)
	private Serializable next; // Object to return when next() is called

	public TypedIterator(Iterator<Serializable> impl, Class<?> clazz) {
		this.impl = impl;
		this.clazz = clazz;
		findNext();
	}

	@Override
	public boolean hasNext() {
		return (next != null);
	}

	@Override
	public Serializable next() {
		Serializable result = next;
		findNext();
		return result;
	}

	/*
	 * find the next element in the underlying iterator that is of the same class as
	 * the desired class
	 */
	private void findNext() {
		next = null;
		while (impl.hasNext()) {
			Serializable current = impl.next();
			if (clazz.isAssignableFrom(current.getClass())) {
				next = current;
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// build an heterogeneous collection
		List<Serializable> list = new LinkedList<>();
		list.add("1");
		list.add(2.25D);
		list.add(3.2F);
		list.add(Math.PI);
		list.add(98765432123456789L);
		list.add('c');
		list.add((byte) 0x7F);
		list.add("Tomer");
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(new File("temp.txt"));

		// wrap the list iterator inside our TypedIterator

		Iterator<Serializable> it = new TypedIterator(list.iterator(), String.class);
		it.forEachRemaining(e -> System.out.printf("%s%n", e));

		/*
		ListIterator<Serializable> iter = list.listIterator();

		while (iter.hasNext()) {
			System.out.println(">> " + iter.next());
			Thread.sleep(250);
			if (!iter.hasNext()) {
				System.out.printf("%n<< reversed%n%n");
				while (iter.hasPrevious())
					System.out.println("<< " + iter.previous());
				System.out.printf("%n>> forward%n%n");
			}
		}
		*/

	}
}
