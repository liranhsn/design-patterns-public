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
 * The class CustomerGroup represents a collection of {@link Customer} objects
 */
public class CustomerGroup {

	private List<Customer> customers = new ArrayList<Customer>();

	public CustomerGroup(List<Customer> customers) {
		super();
		this.customers = customers;
	}

	public void accept(IVisitor visitor) {
		if (customers != null)
			customers.forEach(c -> c.accept(visitor));
	}
}
