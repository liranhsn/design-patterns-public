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
package com.acme.visitor.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Order is an entity representing a simple order with a collection of
 * {@link Item} objects
 */
public class Order implements IVisitable {

	private String name;
	protected List<IVisitable> items = new ArrayList<>();

	public Order(String name) {
		this.name = name;
	}

	public Order(String name, String itemName) {
		this.name = name;
		this.addItem(new Item(itemName));
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
		if (items != null)
			items.forEach(i -> i.accept(visitor));
	}

	public void addItem(IVisitable item) {
		items.add(item);
	}

	@Override
	public String toString() {
		return name;
	}

}