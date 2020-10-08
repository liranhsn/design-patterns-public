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
package patterns.creational.builder;

import java.util.Collection;
import java.util.SortedSet;

/**
 * The Class ClassicDictionaryBuilder is a very simple
 * {@link DictionaryBuilder} where each word is uniquely stored in a set.
 */
public class ClassicDictionaryBuilder implements DictionaryBuilder {

	// TODO: 9. Remove this annotation when this class is implemented
	@SuppressWarnings("unused")
	private SortedSet<String> dictionary;

	/**
	 * Instantiates a new dictionary builder.
	 */
	public ClassicDictionaryBuilder() {
		super();
		/*
		 * TODO: 6. You'll need to instantiate the dictionary. If you want a sorted set
		 * implementation you can use a TreeSet. Since Stings are comparable the TreeSet
		 * will not need a comparator in the constructor to sort it's entries so you can
		 * do something like dictionary = new TreeSet<String>();
		 */
		throw new UnsupportedOperationException("Constructor ClassicDictionaryBuilder() is not implemented yet!");
	}

	/**
	 * Adds the word.
	 *
	 * @param word the word
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.builder.DictionaryBuilder#addWord(java.lang.String)
	 */
	@Override
	public void addWord(String word) {
		/*
		 * TODO: 7. add a word to the dictionary
		 */
		throw new UnsupportedOperationException("method ClassicDictionaryBuilder.addWord(String word) is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.builder.DictionaryBuilder#getCollection()
	 */
	@Override
	public Collection<?> getDictionary() {
		// TODO: 8. Return the collection
		throw new UnsupportedOperationException("method ClassicDictionaryBuilder.getCollection() is not implemented yet!");
	}
}
