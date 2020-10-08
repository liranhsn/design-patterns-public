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
package com.acme.bridge.abstraction;

import com.acme.bridge.impl.IList;

/**
 * The Class AbstractList is the base class of the abstraction hierarchy
 *
 * @param <E> the element type
 */
public abstract class AbstractList<E extends Comparable<E>> {

	protected IList<E> list;

	public AbstractList(IList<E> list) {
		this.list = list;
	}

	protected void insert(E element, int i) {
		list.insert(element, i);
	}

	public int size() {
		return list.getSize();
	}

	public E get(int i) {
		return list.get(i);
	}

	public abstract void add(E element);

	@Override
	public String toString() {
		return String.format("%s [impl=%s]", getClass().getSimpleName(), list);
	}

}