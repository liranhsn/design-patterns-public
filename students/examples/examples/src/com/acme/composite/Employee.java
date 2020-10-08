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
package com.acme.composite;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.acme.utils.SerializationUtils;

/**
 * The class Employee is a Composite Person. It holds a reference to an internal
 * collection of employees, each of the same type as the aggregating class.
 */
public class Employee extends Person {

	private static final long serialVersionUID = 4047293420445987082L;
	private Double salary;
	private Long empId;
	private List<Employee> employees;

	public Employee(String firstName, String lastName, LocalDateTime dateOfBirth, String gid, Double salary, Long empId, Employee... emps) {
		super(firstName, lastName, dateOfBirth, gid);
		this.salary = Double.valueOf(salary.doubleValue());
		this.empId = Long.valueOf(empId.longValue());
		if (emps != null && emps.length > 0) {
			this.employees = new LinkedList<>();
			employees.forEach(e -> employees.add(e.clone()));
		}
	}

	public Employee(String firstName, String lastName, LocalDateTime dateOfBirth, String gid, Double salary, Long empId) {
		super(firstName, lastName, dateOfBirth, gid);
		this.salary = Double.valueOf(salary.doubleValue());
		this.empId = Long.valueOf(empId.longValue());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee clone() {
		Employee clone = null;
		try {
			clone = (Employee) super.clone();
			clone.setGid(new String(getGid()));
			clone.setFirstName(new String(getFirstName()));
			clone.setLastName(new String(getLastName()));
			clone.setSalary(Double.valueOf(salary.doubleValue()));
			clone.setEmpId(Long.valueOf(empId.longValue()));
			if (this.employees != null) {
				try {
					clone.employees = (clone.employees.getClass().getDeclaredConstructor(new Class<?>[] {}).newInstance(new Object[] {}));
					if (!employees.isEmpty()) {
						for (Employee employee : employees) {
							Employee emp = employee.clone();
							clone.employees.add(emp);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}

	public boolean addEmployee(Employee employee) {
		return employees.add(employee);
	}

	public boolean contains(Employee employee) {
		return employees.contains(employee);
	}

	public Employee get(Employee employee) {
		if (contains(employee)) {
			return employees.get(employees.indexOf(employee));
		}
		return null;
	}

	public boolean remove(Employee employee) {
		return employees.remove(employee);
	}

	public Iterator<Employee> iterator() {
		return employees.iterator();
	}

	public boolean hasEmployees() {
		return employees != null && !employees.isEmpty();
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result =
	 * super.hashCode(); result = prime * result + ((empId == null) ? 0 :
	 * empId.hashCode()); result = prime * result + ((employees == null) ? 0 :
	 * employees.hashCode()); result = prime * result + ((salary == null) ? 0 :
	 * salary.hashCode()); return result; }
	 *
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (!super.equals(obj)) return false; if (getClass() != obj.getClass())
	 * return false; Employee other = (Employee) obj; if (empId == null) { if
	 * (other.empId != null) return false; } else if (!empId.equals(other.empId))
	 * return false; if (employees == null) { if (other.employees != null) return
	 * false; } else if (!employees.equals(other.employees)) return false; if
	 * (salary == null) { if (other.salary != null) return false; } else if
	 * (!salary.equals(other.salary)) return false; return true; }
	 */

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee: ");
		builder.append(getFirstName());
		builder.append(" ");
		builder.append(getLastName());
		builder.append(", Birth Date: ");
		builder.append(getDateOfBirth());
		builder.append(", Salary: ");
		builder.append(getSalary());
		builder.append(", gid: ");
		builder.append(getGid());
		builder.append(", empId: ");
		builder.append(getEmpId());
		if (employees != null && !employees.isEmpty()) {
			builder.append(", subordinates:\n\n");
			employees.forEach(e -> builder.append("\t").append(e).append(",\n"));
			builder.delete(builder.length() - 2, builder.length() - 1);
		}
		return builder.toString();
	}

	private static void printEmployeeCloneRef(Employee emp) throws Exception {
		Employee clone = emp.clone();
		System.out.printf("source == clone? %s%n", emp == clone);
		System.out.printf("source.getFirstName() == clone.getFirstName()? %s%n", emp.getFirstName() == clone.getFirstName());
		System.out.printf("source.getLastName() == clone.getLastName()?  %s%n", emp.getLastName() == clone.getLastName());
		System.out.printf("source.getGid() == clone.getGid()?  %s%n", emp.getGid() == clone.getGid());
		System.out.printf("source.getDateOfBirth() == clone.getDateOfBirth()?  %s%n", emp.getDateOfBirth() == clone.getDateOfBirth());
		System.out.printf("source.getSalary() == clone.getSalary()?  %s%n", emp.getSalary() == clone.getSalary());
		System.out.printf("source.getEmpId() == clone.getEmpId()?  %s%n", emp.getEmpId() == clone.getEmpId());
		System.out.printf("source.getEmployees() == clone.getEmployees()?  %s%n", emp.getEmployees() == clone.getEmployees());
	}

	public static void main(String[] args) throws Exception {
		Employee homer = new Employee("Homer", "Simpson", LocalDateTime.parse("1978-07-01T21:40"), "0123-01234-1234", 999.99D, 123456789L);
		Employee marge = new Employee("Marge", "Simpson", LocalDateTime.parse("1979-06-28T23:45"), "0124-03214-1567", 9999.99D, 987654321L);
		Employee lisa = new Employee("Lisa", "Simpson", LocalDateTime.parse("1999-08-31T11:30"), "0224-03214-1567", 99.99D, 334455667L);
		Employee bart = new Employee("Bart", "Simpson", LocalDateTime.parse("2003-12-24T09:20"), "2224-03214-1567", 9.99D, 778899001L);
		Employee maggie = new Employee("Maggie", "Simpson", LocalDateTime.parse("2013-02-06T14:50"), "1454-03214-1567", 0.99D, 998855667L);
		// check clone references
		printEmployeeCloneRef(homer);
		// create composite employee
		Employee manager = new Employee("Al", "Bundy", LocalDateTime.parse("1943-06-21T14:50"), "0-2433257-8", 10000.9D, 32145236L, homer,
				marge, lisa, bart, maggie);
		System.out.println("\nBefore Serialization\n");
		System.out.println(manager);
		System.out.println();
		System.out.printf("Manager contains homer? %s, same homer? %s%n", manager.contains(homer), manager.get(homer) == homer);
		System.out.printf("Manager contains marge? %s, same marge? %s%n", manager.contains(marge), manager.get(marge) == marge);
		System.out.printf("Manager contains lisa? %s, same lisa? %s%n", manager.contains(lisa), manager.get(lisa) == lisa);
		System.out.printf("Manager contains bart? %s, same bart? %s%n", manager.contains(bart), manager.get(bart) == bart);
		System.out.printf("Manager contains maggie? %s, same maggie? %s%n", manager.contains(maggie), manager.get(maggie) == maggie);
		// serialize
		SerializationUtils.serialize(manager, "manager.ser");
		// deserialize
		Employee readManager = SerializationUtils.deserialize("manager.ser");
		System.out.println("\nAfter Serialization\n");
		System.out.printf("Manager contains homer? %s, same homer? %s%n", readManager.contains(homer), readManager.get(homer) == homer);
		System.out.printf("Manager contains marge? %s, same marge? %s%n", readManager.contains(marge), readManager.get(marge) == marge);
		System.out.printf("Manager contains lisa? %s, same lisa? %s%n", readManager.contains(lisa), readManager.get(lisa) == lisa);
		System.out.printf("Manager contains bart? %s, same bart? %s%n", readManager.contains(bart), readManager.get(bart) == bart);
		System.out.printf("Manager contains maggie? %s, same maggie? %s%n", readManager.contains(maggie), readManager.get(maggie) == maggie);
	}
}
