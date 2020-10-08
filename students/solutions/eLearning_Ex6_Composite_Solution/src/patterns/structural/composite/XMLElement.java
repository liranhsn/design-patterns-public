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

    private String name;
    private String data;
    private List<XMLElement> children = new ArrayList<XMLElement>();

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
    public XMLElement addElement(XMLElement element) {
	/*
	 * implement the adding to the list [e.g. children.add(element);] and return a
	 * reference to this element (for chaining methods)
	 */
	children.add(element);
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((children == null) ? 0 : children.hashCode());
	result = prime * result + ((data == null) ? 0 : data.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	XMLElement other = (XMLElement) obj;
	if (children == null) {
	    if (other.children != null)
		return false;
	} else if (!children.equals(other.children))
	    return false;
	if (data == null) {
	    if (other.data != null)
		return false;
	} else if (!data.equals(other.data))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("XMLElement [name=");
	builder.append(name);
	builder.append(", data=");
	builder.append(data);
	builder.append(", children=");
	builder.append(children);
	builder.append("]");
	return builder.toString();
    }
}
