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

import java.util.Iterator;

/**
 * The Class NamedIterator is a custom iterator that will only iterate XML
 * elements that match the given {@code elementName}.
 */
public class NamedIterator implements Iterator<XMLElement> {

	private Iterator<XMLElement> iterator;
	private String elementName;
	private XMLElement current;

	/**
	 * Instantiates a new named iterator.
	 *
	 * @param parent the parent
	 * @param elementName the element name
	 */
	public NamedIterator(XMLElement parent, String elementName) {
		this.elementName = elementName;
		iterator = parent.iterator();
		findNext(); // set cursor at the first element to return
	}

	/**
	 * Find next.
	 */
	private void findNext() {
		/*
		 * while our underlying iterator has more elements and our current element is
		 * null, we iterate through the iterator and try to find elements (next()) whose
		 * name matches the given 'elementName' argument. Once we find a matching child
		 * element, we assign it's reference to the current. Our problem is, if we did
		 * not find any matching elements. In this case we need to iterate through the
		 * elements children and do the search again. You will need to do something like
		 * the following pseudo code:
		 */
		/**
		 * <pre>
		 * current = null;
		 * while (iterator has more elements and current is null) {
		 *    if (iterator next element has the same name as the elementName argument) {
		 *     current = iterator next element;
		 *    }
		 * }
		 * for (iter = element.getChildren();iter has next and current is still null;) {
		 *     if (iter next element has desired name) {
		 *       current = iter next element;
		 *     }
		 * }
		 * </pre>
		 */
		current = null;

		while (iterator.hasNext() && current == null) {
			XMLElement element = iterator.next();
			if (element.getName().equals(elementName))
				current = element;

			for (Iterator<XMLElement> iter = element.iterator(); iter.hasNext() && current == null;) {
				XMLElement next = iter.next();
				if (next.getName().equals(elementName))
					current = next;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return (current != null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#next()
	 */
	@Override
	public XMLElement next() {
		XMLElement result = current;
		findNext();
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("NamedIterator.remove() operation is not supported!");
	}

}
