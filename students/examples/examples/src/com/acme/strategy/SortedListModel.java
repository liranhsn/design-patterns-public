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
package com.acme.strategy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

/**
 * The class SortedListModel is an implementation of {@link AbstractListModel}
 * and is based on a {@link TreeSet} which accepts a {@link Comparator} in it's
 * constructor
 *
 * @param <E> the type of elements the list model contains
 */
public class SortedListModel<E> extends AbstractListModel<E> {

	private static final long serialVersionUID = 4058069780090387048L;
	private TreeSet<E> model;
	private Comparator<E> comparator;

	public SortedListModel(Comparator<E> comparator) {
		this.comparator = comparator;
		model = new TreeSet<E>(this.comparator);
	}

	public void setComparator(Comparator<E> comparator) {
		this.comparator = comparator;
		TreeSet<E> temp = new TreeSet<E>(this.comparator);
		temp.addAll(model);
		model = temp;
	}
	// ListModel methods

	@Override
	public int getSize() {
		return model.size();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E getElementAt(int index) {
		return (E) model.toArray()[index];
	}

	// Other methods
	public void add(E element) {
		if (model.add(element)) {
			fireContentsChanged(this, 0, getSize());
		}
	}

	public void addAll(E[] elements) {
		Collection<E> c = Arrays.asList(elements);
		model.addAll(c);
		fireContentsChanged(this, 0, getSize());
	}

	public void clear() {
		model.clear();
		fireContentsChanged(this, 0, getSize());
	}

	public boolean contains(E element) {
		return model.contains(element);
	}

	public E firstElement() {
		// Return the appropriate element
		return model.first();
	}

	public Iterator<E> iterator() {
		return model.iterator();
	}

	public E lastElement() {
		return model.last();
	}

	public boolean removeElement(E element) {
		boolean removed = model.remove(element);
		if (removed) {
			fireContentsChanged(this, 0, getSize());
		}
		return removed;
	}
}