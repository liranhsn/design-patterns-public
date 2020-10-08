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
 * The Class NormalDBTable is a simple DB table with no special features
 *
 * @param <PK> the generic type of the key
 * @param <T> the generic type of the value
 */
public class NormalDBTable<PK, T> extends AbstractDBTable<PK, T> {

    public NormalDBTable(DataMap<PK, T> dataMapImpl) {
        super(dataMapImpl);
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.structural.bridge.AbstractDBTable#insert(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void insert(PK id, T data) {
        /*
         * TODO: 4. implement this - by calling getImpl() and inserting the key and value pair (id and data)
         */
        throw new UnsupportedOperationException("method NormalDBTable.insert(PK id, T data) is not implemented yet!");
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.structural.bridge.AbstractDBTable#select(java.lang.Object)
     */
    @Override
    public T select(PK id) {
        /*
         *  TODO: 5. implement this - by returning the result of calling getImpl() and retrieving the data by it's key (id)
         */
        throw new UnsupportedOperationException("method NormalDBTable.select(PK id) is not implemented yet!");
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.structural.bridge.AbstractDBTable#update(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void update(PK id, T data) {
        /*
         *  TODO: 6. implement this - for example by calling getImpl() and storing the key value pairs (id,data)
         */
        throw new UnsupportedOperationException("method NormalDBTable.update(PK id, T data) is not implemented yet!");
    }
}
