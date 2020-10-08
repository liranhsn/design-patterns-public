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
package com.acme.bridge.impl;

import java.util.ArrayList;

/**
 * The Class ArrayListImpl is an IList implementation based on an ArrayList
 *
 * @param <E> the element type
 */
public class ArrayListImpl<E extends Comparable<E>> implements IList<E> {

	private ArrayList<E> list;

	public ArrayListImpl() {
		super();
		list = new ArrayList<>();
	}

	public ArrayListImpl(ArrayList<E> list) {
		super();
		this.list = list;
	}

	@Override
	public void insert(E element, int index) {
		System.out.println("ArrayListImpl.insert() -> Adding " + element + " at index " + index + "...");
		list.add(index, element);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public E get(int index) {
		return list.get(index);
	}

	@Override
	public String toString() {
		return "ArrayListImpl [list=" + list + "]";
	}

}
