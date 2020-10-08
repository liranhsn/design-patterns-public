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
package com.acme.clone;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class DeepCloneCompany demonstrates a deep clone of an array of objects.
 */
public class DeepCloneCompany implements Cloneable, Serializable {

	private static final long serialVersionUID = 2431221908794462912L;
	private String name;
	private List<DeepCloneEmployee> employees;

	public DeepCloneCompany() {
		super();
	}

	public DeepCloneCompany(String name, List<DeepCloneEmployee> employees) {
		super();
		this.name = name;
		this.employees = employees;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#clone()
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected DeepCloneCompany clone() throws CloneNotSupportedException {
		/*
		 * super clone
		 */
		DeepCloneCompany clone = (DeepCloneCompany) super.clone();
		/*
		 * clone name (String is immutable -this is not important)
		 */
		clone.setName(String.copyValueOf(getName().toCharArray()));
		/*
		 * retrieve the list type name by reflection
		 */
		String listClassName = clone.getEmployees().getClass().getName();
		/*
		 * create a list instance by reflection and populate it with cloned employees from the original list
		 */
		try {
			System.out.printf("DeepCloneCompany.clone() -> cloning list of class %s%n",listClassName);
			/*
			 * retrieve instance of the Class of the list
			 */
			Class<List<DeepCloneEmployee>> employeesListClass = (Class<List<DeepCloneEmployee>>) Class.forName(listClassName);
			/*
			 * retrieve instance of the list object
			 */
			List<DeepCloneEmployee> clonedEmployees = employeesListClass.getDeclaredConstructor(new Class<?>[] {}).newInstance();
			System.out.printf("DeepCloneCompany.clone() -> cloning list of employees%n");
			/*
			 * clone each original employee and add to new list
			 */
			for (DeepCloneEmployee employee : employees)
				clonedEmployees.add(employee.clone());
			/*
			 * set the cloned company's employees list
			 */
			clone.setEmployees(clonedEmployees);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
		return clone;
		// ...or just do this:
		/*
		 * try { return ObjectCloner.deepCopy(this); } catch (Exception e) {
		 * e.printStackTrace(); return null; }
		 */
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DeepCloneEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<DeepCloneEmployee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DeepCloneCompany other = (DeepCloneCompany) obj;
		if (employees == null) {
			if (other.employees != null) {
				return false;
			}
		} else if (!employees.equals(other.employees)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DeepCloneCompany [name=" + name + ", employees=" + employees + "]";
	}

	public static void main(String[] args) throws Exception {
		/*
		 * create employee
		 */
		List<DeepCloneEmployee> sourceEmployees = new LinkedList<DeepCloneEmployee>();
		sourceEmployees.add(new DeepCloneEmployee("Homer", "Simpson", 1975, 6, 1, 12345L));
		sourceEmployees.add(new DeepCloneEmployee("Marge", "Simpson", 1977, 8, 15, 12346L));
		sourceEmployees.add(new DeepCloneEmployee("Lisa", "Simpson", 1989, 12, 16, 12347L));
		sourceEmployees.add(new DeepCloneEmployee("Bart", "Simpson", 1992, 11, 30, 12348L));
		sourceEmployees.add(new DeepCloneEmployee("Maggie", "Simpson", 1998, 2, 12, 12349L));
		/*
		 * create company with employee
		 */
		DeepCloneCompany source = new DeepCloneCompany("Silvermania", sourceEmployees); // create a company
		/*
		 * clone the company
		 */
		DeepCloneCompany clone = source.clone();
		/*
		 * compare values of clone company with source company
		 */
		System.out.println("==============================================================================================");
		System.out.printf("source == clone? %s%n", source == clone);
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.printf("source.getName() == clone.getName()? %s%n", source.getName() == clone.getName());
		System.out.printf("source.getEmployees() == clone.getEmployees()? %s%n", source.getEmployees() == clone.getEmployees());
		/*
		 * compare values of each clone company employee with source company employee
		 */
		for (int i = 0; i < clone.employees.size(); i++) {
			System.out.println("==============================================================================================");
			System.out.printf("source.getEmployees().get(%d) == clone.getEmployees().get(%d)? %s%n", i, i, (source.getEmployees().get(i) == clone.getEmployees().get(i)));
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.printf("source.getEmployees().get(%d).getEmpId() == clone.getEmployees().get(%d).getEmpId()? %s%n", i, i, (source.getEmployees().get(i).getEmpId() == clone.getEmployees().get(i).getEmpId()));
			System.out.printf("source.getEmployees().get(%d).getFirstName() == clone.getEmployees().get(%d).getFirstName()? %s%n", i, i, (source.getEmployees().get(i).getFirstName() == clone.getEmployees().get(i).getFirstName()));
			System.out.printf("source.getEmployees().get(%d).getLastName() == clone.getEmployees().get(%d).getLastName()? %s%n", i, i, (source.getEmployees().get(i).getLastName() == clone.getEmployees().get(i).getLastName()));
			System.out.printf("source.getEmployees().get(%d).getDateOfBirth() == clone.getEmployees().get(%d).getDateOfBirth()? %s%n", i, i, (source.getEmployees().get(i).getDateOfBirth() == clone.getEmployees().get(i).getDateOfBirth()));
		}
	}
}
