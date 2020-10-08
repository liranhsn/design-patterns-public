/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation  and/or other materials provided with the distribution.
 * 3. Neither the names of the copyright holders nor the names of any
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package patterns.structural.bridge;

/**
 * The Class AbstractDBTable is the top level class in the abstraction side of
 * the bridge pattern. It defines the behavior of all DB Tables and includes
 * some default behavior
 *
 * @param <PK> the generic type of the key in the storage
 * @param <T>  the generic type of the data in the storage
 */
public abstract class AbstractDBTable<PK, T> {

	private DataMap<PK, T> dataMap;

	public AbstractDBTable(DataMap<PK, T> dataMap) {
		super();
		this.dataMap = dataMap;
	}

	/**
	 * allow access to the underlying storage to sub classes
	 * @return the data map
	 */
	protected DataMap<PK, T> getImpl() {
		return this.dataMap;
	}

	/**
	 * Insert the data into the storage (if it does not exist)
	 *
	 * @param id   the id
	 * @param data the data
	 */
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

	/*
	 * TODO: 12. Run this test only after implementations of all todo tasks
	 */
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
