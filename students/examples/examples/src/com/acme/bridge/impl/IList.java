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

/**
 * The Interface IList is the top of the implementation hierarchy of the bridge
 * pattern. It defines the basic contract for all list based data structures.
 *
 * @param <E> the element type
 */
public interface IList<E extends Comparable<E>> {

	/**
	 * Insert elements into the list
	 *
	 * @param element the element
	 * @param index   the index
	 */
	void insert(E element, int index);

	/**
	 * Gets the size of the list.
	 *
	 * @return the size
	 */
	int getSize();

	/**
	 * Gets an element in the list at a given index
	 *
	 * @param index the index
	 * @return the element at the given index
	 */
	E get(int index);

}
