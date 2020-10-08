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
package com.acme.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Class SortingStrategyDemo demonstrates how a {@link TreeSet} keeps
 * sorting order by the comparator it receives in it's constructor
 */
public class SortingStrategyDemo {

	static final boolean ASC = false;
	static final boolean SORT_BY_FIRST_NAME = false;

	/*
	 * A comparator for comparing salaries
	 */
	static final Comparator<Employee> SALARY_COMPARATOR = (emp1, emp2) -> {
		if (ASC)
			return (emp1.getSalary().compareTo(emp2.getSalary()));
		return (emp2.getSalary().compareTo(emp1.getSalary()));
	};

	/*
	 * A comparator for comparing names
	 */
	static final Comparator<Employee> NAME_COMPARATOR = (emp1, emp2) -> {
		if (SORT_BY_FIRST_NAME) {
			if (ASC)
				return (emp1.getFirstName().compareTo(emp2.getFirstName()));
			return (emp2.getFirstName().compareTo(emp1.getFirstName()));
		}
		if (ASC)
			return (emp1.getLastName().compareTo(emp2.getLastName()));
		return (emp2.getLastName().compareTo(emp1.getLastName()));
	};

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		List<Employee> employees = List.of(
				new Employee("Bigboss", "Robert", 30000D),
				new Employee("Newbe", "Joe", 5000D),
				new Employee("Veteran", "Jack", 17000D),
				new Employee("Freelance", "Bill", 15000D));
		// demo 1: sort by salary
		System.out.printf("%nSorting by salary %s:%n%n", (ASC ? "ascending" : "descending"));

		Set<Employee> set = new TreeSet<Employee>(SALARY_COMPARATOR); // sorting strategy in the constructor

		set.addAll(employees);
		set.forEach(System.out::println);
		// demo 2: sort by employee
		System.out.printf("%nSorting by %s:%n%n", (SORT_BY_FIRST_NAME ? "nick" : "last") + "name " + (ASC ? "ascending" : "descending"));

		set = new TreeSet<Employee>(NAME_COMPARATOR); // change sorting strategy

		set.addAll(employees);
		set.forEach(System.out::println);
	}
}
