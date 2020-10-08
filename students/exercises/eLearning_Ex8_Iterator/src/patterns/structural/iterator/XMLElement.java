/*
 * Copyright (C) 2009 - 2020 T.N.Silverman, All rights reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation  and/or other materials provided with the distribution.
 * 3. Neither the names of the copyright holders nor the names of any
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package patterns.structural.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class XMLElement is a simple XML Element composite data structure with a
 * given {@code name} and {@code data} properties. The xml element can also
 * store a reference to a list of child XML elements.
 *
 * In this exercise, the XML element also provides two types of iterator
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

	/**
	 * Iterate recursively through the entire given {@code element} list of children
	 * and print every child
	 *
	 * @param element the element to iterate
	 */
	public static void iterate(XMLElement element) {
		/*
		 * TODO: 2. loop through the entire collection of the given element children.
		 * Use an iterator obtained via the iterator() method. For each element, print
		 * the element and then recursively iterate through it. You should do something
		 * like the following example in pseudo code:
		 *
		 * Note: since JDK8 we can use element.iterator().forEachRemaining(e -> {print
		 * e; iterate(e)});
		 */
		/**
		 * <pre>
		 * for (declare iterator;iter.hasNext();) {
		 *   print next element;
		 *   iterate(next element);
		 * }
		 * </pre>
		 */
		throw new UnsupportedOperationException("method XMLElement.iterate(XMLElement element) is not implemented yet!");
	}

	/**
	 * Iterate recursively through the entire given {@code parent} element list of
	 * children and print every child whose {@code name} matches the given
	 * {@code childName}
	 *
	 * @param parent    the parent element to iterate
	 * @param childName the name of the elements to iterate
	 */
	public static void iterate(XMLElement parent, String childName) {
		/*
		 * TODO: 3. use your custom iterator (via the call parent.iterator(childName))
		 * to obtain a custom iterator and iterate through all the children elements
		 * whose name matches the given childName argument. You should do something very
		 * similar to the example in pseudo code:
		 */
		/**
		 * <pre>
		 * for (declare iterator using parent.getChildren(childName);iter.hasNext();) {
		 *   print next element;
		 * }
		 *
		 * or use stream features:
		 *
		 * parent.iterator(childName).forEachRemaining(e -> print e);
		 *
		 *
		 * </pre>
		 */
		throw new UnsupportedOperationException("method iterate(XMLElement parent, String childName) is not implemented yet!");
	}

	/**
	 * Instantiates a new XML element.
	 *
	 * @param name the name of the XML element
	 */
	public XMLElement(String name) {
		this.name = name;
		children = new ArrayList<XMLElement>();
	}

	/**
	 * Adds the element to the list of children
	 *
	 * @param element the element to add to the list of children
	 * @return this XML element for chaining purpose.
	 */
	public XMLElement addElement(XMLElement element) {
		children.add(element);
		return this;
	}

	/**
	 * Gets the list of children's iterator
	 *
	 * @return the children's iterator
	 */
	public Iterator<XMLElement> iterator() {
		return children.iterator();
	}

	/**
	 * Gets the children's custom iterator
	 *
	 * @param name the name of the elements the custom iterator will iterate
	 * @return the children's custom iterator
	 */
	public Iterator<XMLElement> iterator(String name) {
		return new NamedIterator(this, name);
	}

	/**
	 * Gets the data (value) of this XML element
	 *
	 * @return Returns the data (value) of this XML element
	 */
	public String getData() {
		return data;
	}

	/**
	 * Gets the name of this XML element
	 *
	 * @return Returns the name of this XML element
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the data of this XML element.
	 *
	 * @param data The data to set on this XML element
	 * @return the XML element for chaining purpose
	 */
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

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			XMLElement root = new XMLElement("root").setData("Root");
			XMLElement courseElement1 = new XMLElement("course").setData("Old Course");
			courseElement1.addElement(new XMLElement("name").setData("Design Patterns"));
			courseElement1.addElement(new XMLElement("duration").setData("4 days"));
			root.addElement(courseElement1);
			root.addElement(new XMLElement("dummy").setData("Dummy"));
			XMLElement courseElement2 = new XMLElement("course").setData("New Course");
			courseElement2.addElement(new XMLElement("name").setData("Java Programming"));
			courseElement2.addElement(new XMLElement("duration").setData("5 days"));
			root.addElement(courseElement2);
			iterate(root, "Blah");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}