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
package patterns.structural.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class XMLElement is a simple XML Element composite data structure with a
 * given {@code name} and {@code data} properties. The xml element can also
 * store a reference to a list of child xml elements.
 *
 * In this exercise, the xml element also provides two types of iterator
 * implementations. The first is the regular iterator that is obtained via the
 * {@link #iterator()} method.
 *
 * The other iterator is obtained via the {@link #iterator(String)} method and
 * only returns children (or children of children) whose names are equal to the
 * given string argument.
 */
public class XMLElement {

	private String name;
	private String data;
	private List<XMLElement> children;

	public XMLElement(String name) {
		this.name = name;
		children = new ArrayList<XMLElement>();
	}

	/**
	 * Iterate recursively through the entire given {@code element} list of children
	 * and print every child
	 *
	 * @param element the element to iterate
	 */
	public static void iterate(XMLElement element) {
		/*
		 * loop through the entire list of the given element children. Use an iterator
		 * obtained via the getChildren() method. For each element, print the element
		 * and recursively iterate through it. You should do something like the
		 * following example in pseudo code:
		 */
		/**
		 * <pre>
		 * for (declare iterator;iter.hasNext();) {
		 *   print next element;
		 *   iterate(next element);
		 * }
		 * </pre>
		 */
		element.iterator().forEachRemaining(child -> {
			System.out.printf("%s%n", child);
			iterate(child);
		});
	}

	/**
	 * Iterate recursively through the entire given {@code parent} element list of
	 * children and print every child whose {@code name} matches the given
	 * {@code childName}
	 *
	 * @param parent the parent element to iterate
	 * @param childName the name of the elements to iterate
	 */
	public static void iterate(XMLElement parent, String childName) {
		/*
		 * Use your custom iterator (via the call parent.iterator(childName)) to obtain
		 * a custom iterator and iterate through all the children elements whose name
		 * matches the given childName argument. You should do something very similar to
		 * the example in pseudo code:
		 */
		/**
		 * <pre>
		 * for (declare iterator using parent.iterator(childName);iter.hasNext();) {
		 *   print next element;
		 * }
		 * </pre>
		 */
		parent.iterator(childName).forEachRemaining(System.out::println);
	}

	public XMLElement addElement(XMLElement element) {
		children.add(element);
		return this;
	}

	public Iterator<XMLElement> iterator() {
		return children.iterator();
	}

	public Iterator<XMLElement> iterator(String name) {
		return new NamedIterator(this, name);
	}

	public String getData() {
		return data;
	}

	public String getName() {
		return name;
	}

	public XMLElement setData(String data) {
		this.data = data;
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[XML Element: name=" + getName() + ", data=" + getData() + "]";
	}

	public static void main(String[] args) {

		try {
			XMLElement root = new XMLElement("root").setData("root");
			XMLElement courseElement1 = new XMLElement("course").setData("Old Course");
			courseElement1.addElement(new XMLElement("name").setData("Design Patterns"));
			courseElement1.addElement(new XMLElement("duration").setData("5 days"));
			root.addElement(courseElement1);
			root.addElement(new XMLElement("dummy").setData("dummy"));
			XMLElement courseElement2 = new XMLElement("course").setData("New Course");
			courseElement2.addElement(new XMLElement("name").setData("Java Programming"));
			courseElement2.addElement(new XMLElement("duration").setData("5 days"));
			root.addElement(courseElement2);
			iterate(root, "blah");
			System.out.println("====================================================");
			iterate(root, "course");
			System.out.println("====================================================");
			iterate(root, "name");
			System.out.println("====================================================");
			iterate(root, "duration");
			System.out.println("====================================================");
			iterate(root, "dummy");
			System.out.println("====================================================");
			iterate(courseElement1, "name");
			System.out.println("====================================================");
			iterate(courseElement1, "duration");
			System.out.println("====================================================");
			iterate(courseElement2, "name");
			System.out.println("====================================================");
			iterate(courseElement2, "duration");
			System.out.println("====================================================");
			iterate(root);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
