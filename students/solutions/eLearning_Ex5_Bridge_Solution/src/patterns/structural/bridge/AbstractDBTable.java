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
package patterns.structural.bridge;

/**
 * The Class AbstractDBTable is the top level class in the abstraction side of
 * the bridge pattern. It defines the behavior of all DB Tables and includes
 * some default behavior
 *
 * @param <PK> the generic type of the key
 * @param <T>  the generic type of the value
 */
public abstract class AbstractDBTable<PK, T> {

	private DataMap<PK, T> dataMap;

	public AbstractDBTable(DataMap<PK, T> dataMap) {
		super();
		this.dataMap = dataMap;
	}

	protected DataMap<PK, T> getImpl() {
		return this.dataMap;
	}

	public abstract void insert(PK id, T data);

	/**
	 * Select a value from the underlying storage by using the primary key
	 *
	 * @param id the primary key of the data in the underlying storage
	 * @return the data (T)
	 */
	public abstract T select(PK id);

	/**
	 * Update the data in the storage (if it exists(
	 *
	 * @param id   the id
	 * @param data the data
	 */
	public abstract void update(PK id, T data);

	// Run this test only after implementations and revising all todo comments
	public static void main(String[] args) {
		try {
			DataMap<Integer, String> intMap = new HashDataMap<Integer, String>();
			DataMap<String, String> strMap = new HashDataMap<String, String>();
			AbstractDBTable<Integer, String> normalTab = new NormalDBTable<Integer, String>(intMap);
			AbstractDBTable<String, String> uniqueTab = new UniqueKeyDBTable<String, String>(strMap);
			DataMap<Integer, String> dataMap = new HashDataMap<Integer, String>();
			dataMap.put(1, "read only value 1");
			dataMap.put(2, "read only value 2");
			dataMap.put(3, "read only value 3");
			AbstractDBTable<Integer, String> readOnlyTab = new ReadOnlyDBTable<Integer, String>(dataMap);
			normalTab.insert(1, "normal value 1");
			normalTab.insert(2, "normal value 2");
			normalTab.insert(3, "normal value 3");
			normalTab.insert(3, "normal value 4");
			normalTab.update(3, "normal value 5");
			uniqueTab.insert("a", "unique value a");
			uniqueTab.insert("b", "unique value b");
			uniqueTab.insert("c", "unique value c");
			uniqueTab.insert("c", "unique value d");
			uniqueTab.update("c", "unique value e");
			System.out.println("========================================================");
			System.out.printf("data at normal db table for pk 1: %s%n", normalTab.select(1));
			System.out.printf("data at normal db table for pk 2: %s%n", normalTab.select(2));
			System.out.printf("data at normal db table for pk 3: %s%n", normalTab.select(3));
			System.out.printf("data at normal db table for pk 4: %s%n", normalTab.select(4));
			System.out.println("========================================================");
			System.out.printf("data at unique db table for pk 'a': %s%n", uniqueTab.select("a"));
			System.out.printf("data at unique db table for pk 'b': %s%n", uniqueTab.select("b"));
			System.out.printf("data at unique db table for pk 'c': %s%n", uniqueTab.select("c"));
			System.out.printf("data at unique db table for pk 'd': %s%n", uniqueTab.select("d"));
			System.out.println("========================================================");
			System.out.printf("data at read only db table for pk 1: %s%n", readOnlyTab.select(1));
			System.out.printf("data at read only db table for pk 2: %s%n", readOnlyTab.select(2));
			System.out.printf("data at read only db table for pk 3: %s%n", readOnlyTab.select(3));
			System.out.println("========================================================");
			try {
				readOnlyTab.update(1, "read only value 4");
			} catch (UnsupportedOperationException usoex) {
				System.out.println(usoex.getMessage());
			}
			try {
				readOnlyTab.insert(4, "read only value 4");
			} catch (UnsupportedOperationException usoex) {
				System.out.println(usoex.getMessage());
			}
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
	}
}
