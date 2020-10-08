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
package com.acme.adapter;

/**
 * The Class EmployeeClassAdapter.
 */
public class EmployeeClassAdapter extends Employee implements INewEmployee {

	public EmployeeClassAdapter(int id, double salary) {
		super(id, salary);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.adapter.INewEmployee#store()
	 */
	@Override
	public void store() {
		saveToDB();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.adapter.INewEmployee#taxInDollars()
	 */
	@Override
	public double taxInDollars() {
		return taxInNIS() * getDollarRate();
	}

	private double getDollarRate() {
		return 1 / 3.57D;
	}

	public static void main(String[] args) {
		EmployeeClassAdapter adapter = new EmployeeClassAdapter(12345, 10000D);
		adapter.setId(12345);
		adapter.store();
		System.out.printf("Tax in dollars: $%.2f%n", adapter.taxInDollars());
	}
}
