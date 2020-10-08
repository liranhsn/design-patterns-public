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

/**
 * The class Customer is a basic entity representing a customer with a
 * collection of {@link Order} entities
 */
public class Customer implements IVisitable {

	private String name;
	protected ArrayList<Order> orders = new ArrayList<Order>();

	public Customer(String name) {
		this.name = name;
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
		if (orders != null)
			orders.forEach(o -> o.accept(visitor));
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public String toString() {
		return name;
	}
}
