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
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The Class CounterDictionaryBuilder counts the appearance of each word in the
 * collection (dictionary) of words. The words are stored in a map where the key
 * is the mapand the value is the count of appearances
 */
public class CounterDictionaryBuilder implements DictionaryBuilder {

    /** The words count map. */
    private SortedMap<String, Integer> wordsCountMap;

    /**
     * Instantiates a new words counter builder.
     */
    public CounterDictionaryBuilder() {
        super();
        // You'll need to instantiate the dictionary. If you want a sorted
        // map implementation you can use a TreeMap. Since Stings are comparable
        // the TreeMap will not need a comparator in the constructor to sort
        // it's entries so you can do something like wordsCountMap = new
        // TreeMap<String, Integer>();
        wordsCountMap = new TreeMap<String, Integer>();
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.creational.builder.DictionaryBuilder#addWord(java.lang.String)
     */
    @Override
    public void addWord(String word) {
        // You'll need to extract the count of the word from the map. If
        // it exists, you'll have to increment it by one and put it back in the
        // map under the same key. Other wise, this is the first time you
        // encountered the word and it's count is therefore 'one'.
        Integer count = wordsCountMap.get(word);
        wordsCountMap.put(word, count == null ? 1 : ++count);
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.creational.builder.DictionaryBuilder#getCollection()
     */
    @Override
    public Collection<?> getDictionary() {
        // Return dictionary entry set [e.g. return
        // wordsCountMap.entrySet();]
        return wordsCountMap.entrySet();
    }
}
