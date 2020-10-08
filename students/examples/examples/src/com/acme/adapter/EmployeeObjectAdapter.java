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
 * The Class EmployeeObjectAdapter.
 */
public class EmployeeObjectAdapter implements INewEmployee {

	private Employee employee;

	public EmployeeObjectAdapter(Employee employee) {
		super();
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public void store() {
		employee.saveToDB();
	}

	@Override
	public double taxInDollars() {
		return employee.taxInNIS() / getDollarRate();
	}

	private double getDollarRate() {
		return 3.57D;
	}

	public static void main(String[] args) {
		Employee employee = new Employee(12345, 10000D);
		EmployeeObjectAdapter adapter = new EmployeeObjectAdapter(employee);
		adapter.store();
		System.out.printf("tax in dollars: $%.2f%n", adapter.taxInDollars());
	}
}
