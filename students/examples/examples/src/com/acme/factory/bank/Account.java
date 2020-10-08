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
package com.acme.factory.bank;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {

	private static final long serialVersionUID = 8415114765363658566L;

	private String name = "N/A";
	private BigDecimal balance = new BigDecimal("0");
	private long accountId;

	public Account(String name, Double balance, long accountId) {
		super();
		this.name = name;
		this.balance = new BigDecimal(balance);
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return new BigDecimal(balance.doubleValue());
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public long getAccountId() {
		return accountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
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
		Account other = (Account) obj;
		if (accountId != other.accountId) {
			return false;
		}
		if (balance == null) {
			if (other.balance != null) {
				return false;
			}
		} else if (!balance.equals(other.balance)) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("Account [name=");
		builder.append(name);
		builder.append(", balance=");
		builder.append(balance.doubleValue());
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append("]");
		return builder.toString();
	}
}
