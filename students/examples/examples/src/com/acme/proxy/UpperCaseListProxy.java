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
package com.acme.proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * The class UpperCaseListProxy demonstrates the old and tedious way of creating
 * proxy classes by wrapping a delegate inside a wrapper.
 */
public class UpperCaseListProxy<E> implements List<E> {

	List<E> list;

	@SuppressWarnings("unchecked")
	public UpperCaseListProxy(List<E> list) {
		super();
		List<E> temp = new ArrayList<>();
		for (E e : list) {
			if (e.getClass() == String.class)
				e = (E) e.toString().toUpperCase();
			temp.add(e);
		}
		list.clear();
		list.addAll(temp);
		this.list = list;
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
	public boolean contains(Object o) {
		return list.contains(o);
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
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		if (e.getClass() == String.class)
			return list.add((E) ((String) e).toUpperCase());
		else
			return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof String)
			return list.remove(o.toString().toUpperCase());
		else
			return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> c) {
		List<E> strings = new ArrayList<>();
		for (E element : c)
			if (element.getClass() == String.class) {
				element = (E) element.toString().toUpperCase();
				strings.add(element);
			}
		return list.addAll(strings);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		for (E element : c)
			if (element.getClass() == String.class)
				element = (E) element.toString().toUpperCase();
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public E get(int index) {
		return list.get(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		if (element.getClass() == String.class) {
			element = (E) element.toString().toUpperCase();
		}
		return list.set(index, element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, E element) {
		if (element.getClass() == String.class)
			element = (E) element.toString().toUpperCase();
		list.add(index, element);
	}

	@Override
	public E remove(int index) {
		return list.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		if (o instanceof String) {
			return list.indexOf(o.toString().toUpperCase());
		} else {
			return list.indexOf(o);
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		if (o instanceof String) {
			return list.lastIndexOf(o.toString().toUpperCase());
		} else {
			return list.lastIndexOf(o);
		}
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
		return list.toString();
	}

	public static void main(String[] args) {
		UpperCaseListProxy<String> proxy = new UpperCaseListProxy<>(new ArrayList<>());
		proxy.add("AlL");
		proxy.add("Various");
		proxy.add("Strings");
		proxy.addAll(List.of("aRe", "Upper", "Case"));
		proxy.remove("VarIous");
		System.out.printf("%s%n", proxy);
	}
}
