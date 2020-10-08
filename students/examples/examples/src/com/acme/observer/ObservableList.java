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
package com.acme.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class ObservableList<E> extends Observable implements List<E> {

	private List<E> list;

	public ObservableList() {
		super();
		this.list = new ArrayList<E>();
	}

	public ObservableList(List<E> list) {
		super();
		this.list = list;
	}

	/**
	 * This is here, just to demonstrate it exists
	 */
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}

	/*
	 * The 'Subject' notify() method
	 */
	private void reportChanged(String message, Object arg) {
		setChanged();
		notifyObservers(message + " " + arg);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object element) {
		return list.contains(element);
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		return list.toArray(array);
	}

	@Override
	public boolean add(E element) {
		boolean result = list.add(element);
		if (result)
			reportChanged("added", element);
		return result;
	}

	@Override
	public boolean remove(Object element) {
		boolean result = list.remove(element);
		if (result)
			reportChanged("removed", element);
		return result;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		return list.containsAll(collection);
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean result = list.addAll(collection);
		if (result)
			reportChanged("added all", collection);
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		boolean result = list.addAll(index, collection);
		if (result)
			reportChanged("added all at index " + index, collection);
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean result = list.removeAll(collection);
		if (result)
			reportChanged("removed all", collection);
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		boolean result = list.retainAll(collection);
		if (result)
			reportChanged("retained all", collection);
		return result;
	}

	@Override
	public void clear() {
		int size = list.size();
		list.clear();
		int newsize = list.size();
		if (newsize != size)
			reportChanged("cleared all elements", size);
	}

	@Override
	public E get(int index) {
		return list.get(index);
	}

	@Override
	public E set(int index, E element) {
		E result = null;
		if (index > size() - 1) {
			if (list.add(element)) {
				result = element;
				index = list.size() - 1;
			}
		} else {
			result = list.set(index, element);
		}
		if (result != null)
			reportChanged("set at index " + index, element);
		return result;
	}

	@Override
	public void add(int index, E element) {
		list.add(index, element);
		reportChanged("added at " + index, element);
	}

	@Override
	public E remove(int index) {
		E result = list.remove(index);
		if (result != null)
			reportChanged("removed from index ", index);
		return result;
	}

	@Override
	public int indexOf(Object element) {
		return list.indexOf(element);
	}

	@Override
	public int lastIndexOf(Object element) {
		return list.lastIndexOf(element);
	}

	@Override
	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public String toString() {
		return "ObservableList: " + list.toString();
	}

}
