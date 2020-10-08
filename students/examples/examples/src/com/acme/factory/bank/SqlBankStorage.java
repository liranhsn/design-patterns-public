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

public class SqlBankStorage implements BankStorage {

	protected SqlBankStorage() {
	}

	@Override
	public Account readAccount(long accountId) {
		System.out.println("SqlBankStorage.readAccount() -> Reading account " + accountId + "...");
		return new Account("test", 444.99D, accountId);
	}

	@Override
	public void saveAccount(Account account) {
		System.out.println("SqlBankStorage.saveAccount() -> Saving account " + account + "...");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SqlBankStorage");
		return builder.toString();
	}
}
