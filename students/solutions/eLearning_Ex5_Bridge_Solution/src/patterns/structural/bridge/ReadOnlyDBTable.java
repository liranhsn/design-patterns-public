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
 * The Class ReadOnlyDBTable is a database table that will not allow any DML. It
 * only supports selecting data (e.g. reading data)
 *
 * @param <PK> the generic type
 * @param <T> the generic type
 */
public class ReadOnlyDBTable<PK, T> extends AbstractDBTable<PK, T> {

    public ReadOnlyDBTable(DataMap<PK, T> dataMap) {
        super(dataMap);
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.structural.bridge.AbstractDBTable#insert(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void insert(PK id, T data) {
        throw new UnsupportedOperationException("ReadOnlyDBTable does not support insert operations!");
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.structural.bridge.AbstractDBTable#select(java.lang.Object)
     */
    @Override
    public T select(PK id) {
        // implement this - for example return getImpl().get(id);
        return getImpl().get(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.structural.bridge.AbstractDBTable#update(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void update(PK id, T data) {
        throw new UnsupportedOperationException("ReadOnlyDBTable does not support update operations!");
    }
}
