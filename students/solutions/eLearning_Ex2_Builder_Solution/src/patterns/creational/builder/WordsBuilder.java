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

/**
 * The Interface WordsBuilder represents the basic contract for dealing with a
 * {@link Collection} of words. It offers just basic methods to add a word to a
 * collection of words and a getter for the collection. We will use this simple
 * interface to represent a dictionary builder from a source of words
 */
public interface WordsBuilder {

    /**
     * Clients use this method to add words to the dictionary
     *
     * @param word the word
     */
    public void addWord(String word);

    /**
     * Clients use this method to get the collection of words added. Different
     * builders may return different collections. Typically this is just the
     * internal collection of the dictionary.
     *
     * @return the collection
     */
    public Collection<?> getCollection();
}
