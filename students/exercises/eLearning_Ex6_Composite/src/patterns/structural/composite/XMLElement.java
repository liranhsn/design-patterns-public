/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
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
package patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class XMLElement is a composite containing a list of XMLElement children
 * and utility methods to add and retrieve them. Note: This is the only class in
 * this exercise that actually adheres to the 'Composite' design pattern. It is
 * a very simple design pattern and we'll be using components from the 'Builder'
 * design pattern to actually do something with it.
 */
public class XMLElement {

	private String name; // name of the XMLElement
	private String data; // data of the XMLElement
	private List<XMLElement> children = new ArrayList<XMLElement>(); // list of child elements

	public XMLElement(String name) {
		super();
		this.name = name;
	}

	/**
	 * Add an XMLElement to the list of elements in this one.
	 *
	 * @param element the element
	 * @return the XML element
	 */
	// TODO: 3. remove this annotation when done
	@SuppressWarnings("unused")
	public XMLElement addElement(XMLElement element) {
		/*
		 * TODO: 2. implement adding an element to the children list and return a
		 * reference to this element (for chainability)
		 */
		if (true)
			throw new UnsupportedOperationException("method XMLElement.addElement(XMLElement element) is not implemented yet!");
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public XMLElement setData(String data) {
		this.data = data;
		return this;
	}

	public List<XMLElement> getChildren() {
		return children;
	}

	public void setChildren(List<XMLElement> children) {
		this.children = children;
	}

}
