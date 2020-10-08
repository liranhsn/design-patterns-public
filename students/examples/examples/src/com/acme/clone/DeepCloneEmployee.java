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
import java.util.Calendar;
import java.util.Date;

/**
 * The class DeepCloneEmployee demonstrates overrriding the clone method to
 * custom deep clone an object
 */
public class DeepCloneEmployee implements Cloneable, Serializable {

	private static final long serialVersionUID = -2493369462150354151L;
	private transient String firstName;
	private String lastName;
	private Date dateOfBirth;
	private Long empId;

	public DeepCloneEmployee() {
		super();
	}

	public DeepCloneEmployee(String firstName, String lastName, int yearOfBirth, int monthOfBirth, int dayOfBirth, Long empId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		Calendar cal = Calendar.getInstance();
		cal.set(yearOfBirth, monthOfBirth - 1, dayOfBirth);
		this.dateOfBirth = cal.getTime();
		this.empId = empId;
	}

	/*
	 * (non-Javadoc)
	 *
	 * Override the {@Code protected} modifier
	 *
	 * @see java.lang.Object#clone()
	 */
	@Override
	public DeepCloneEmployee clone() throws CloneNotSupportedException {
		System.out.println("DeepCloneEmployee.clone() -> cloning " + this);
		DeepCloneEmployee clone = (DeepCloneEmployee) super.clone();
		clone.setFirstName(String.copyValueOf(getFirstName().toCharArray())); // String is immutable
		clone.setLastName(new String(getLastName())); // String is immutable
		clone.setDateOfBirth(new Date(getDateOfBirth().getTime())); // Important!!!
		clone.setEmpId(Long.valueOf(getEmpId())); // Long is Immutable
		return clone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		DeepCloneEmployee other = (DeepCloneEmployee) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (empId == null) {
			if (other.empId != null) {
				return false;
			}
		} else if (!empId.equals(other.empId)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DeepCloneEmployee [lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", empId=" + empId + "]";
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) throws Exception {
		DeepCloneEmployee source = new DeepCloneEmployee("Tomer", "Silverman", 1981, 2, 6, 123456L);
		// keep reference to old date
		Date date = source.getDateOfBirth();
		DeepCloneEmployee clone = source.clone();
		System.out.printf("source == clone? %s%n", source == clone);
		System.out.printf("source.getFirstName() == clone.getFirstName()? %s%n", source.getFirstName() == clone.getFirstName());
		System.out.printf("source.getLastName() == clone.getLastName()? %s%n", source.getLastName() == clone.getLastName());
		System.out.printf("source.getDateOfBirth() == clone.getDateOfBirth()? %s%n", source.getDateOfBirth() == clone.getDateOfBirth());
		System.out.printf("source.getEmpId() == clone.getEmpId()? %s%n", source.getEmpId() == clone.getEmpId());
		System.out.println("\nBefore modification of source property:\n");
		System.out.printf("source: %s%n", source);
		System.out.printf("clone: %s%n", clone);
		// here is a no-problem demo... date is set for source only
		date.setTime(Calendar.getInstance().getTimeInMillis());
		System.out.println("\nAfter modification of source property:\n");
		System.out.printf("source: %s%n", source);
		System.out.printf("clone: %s%n", clone);
	}
}
