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

import java.util.Iterator;

/**
 * The Class NamedIterator is a custom iterator that will only iterate XML
 * elements that match the given {@code elementName}
 */
// TODO: 4. remove this annotation when done
@SuppressWarnings("unused")
public class NamedIterator implements Iterator<XMLElement> {

	private Iterator<XMLElement> iterator;
	private String elementName;
	private XMLElement current;

	public NamedIterator(XMLElement parent, String elementName) {
		this.elementName = elementName;
		iterator = parent.iterator();
		findNext(); // set cursor at the first element to return
	}

	private void findNext() {
		/*
		 * TODO: 1. while our underlying iterator has more elements and our current
		 * element is null, we iterate through the iterator and try to find elements
		 * (next()) whose name matches the given 'elementName' argument. Once we find a
		 * matching child element, we assign it's reference to the currentElement. Our
		 * problem is what happens if we did not find any matching elements. In this
		 * case we need to iterate through the elements children and do the search
		 * again. You will need to do something like the following pseudo code:
		 */
		/**
		 * <pre>
		 * current = null;
		 * while (iterator.hasNext() && current == null) {
		 * 	XMLElement element = iterator.next();
		 * 	if (element.getName().equals(elementName))
		 * 		current = element;
		 * 	for (Iterator<XMLElement> iter = element.iterator(); iter.hasNext() && current == null;) {
		 * 		XMLElement next = iter.next();
		 * 		if (next.getName().equals(elementName))
		 * 			current = next;
		 * 	}
		 * }
		 * </pre>
		 */
		throw new UnsupportedOperationException("method ChildrenIterator.findNext() is not implemented yet!");
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
		XMLElement result = current; // keep the reference to 'current'
		findNext(); // after this call, 'current' had changed
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