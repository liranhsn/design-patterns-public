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
 * The Interface DataMap represents the top of the hierarchy for the
 * implementation side of the bridge. It defines the contract for all the under
 * lying data structures to be used by the abstractions
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface DataMap<K, V> {

    /**
     * Gets the the value from the storage
     *
     * @param key the key
     * @return the v
     */
    public V get(K key);

    /**
     * Check if the key exists in the storage
     *
     * @param key the key
     * @return true, if successful
     */
    public boolean keyExists(K key);

    /**
     * Put the key and value in the storage
     *
     * @param key the key
     * @param value the value
     */
    public void put(K key, V value);
}
