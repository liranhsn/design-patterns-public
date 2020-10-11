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

import java.util.ResourceBundle;

public class BankStorageFactory {

	public static final String PROP_FILE_BASENAME = "bankstorage";
	private Class<?> concreteClass;

	public BankStorageFactory() {
		try {
			ResourceBundle bundle = ResourceBundle
					.getBundle(getClass().getPackage().getName() + "." + PROP_FILE_BASENAME);
			String classname = bundle.getString("storage.classname");
			concreteClass = (Class<?>) Class.forName(classname);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public BankStorage createBankStorage() {
		try {
			System.out.println(
					"BankStorageFactory.createBankStorage() -> creating new " + concreteClass.getCanonicalName());
			return (BankStorage) concreteClass.getDeclaredConstructor().newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		BankStorageFactory factory = new BankStorageFactory();
		BankStorage storage = factory.createBankStorage();
		Account account = new Account("Tomer Silverman", 1045.53, 6294411);
		storage.saveAccount(account);
		storage.readAccount(account.getAccountId());
	}
}
