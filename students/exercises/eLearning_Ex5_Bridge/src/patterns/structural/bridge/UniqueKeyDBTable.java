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
 * The Class UniqueKeyDBTable is a database table that will not allow duplicate
 * keys
 *
 * @param <PK> the generic type of the key
 * @param <T>  the generic type of the value
 */
public class UniqueKeyDBTable<PK, T> extends AbstractDBTable<PK, T> {

	public UniqueKeyDBTable(DataMap<PK, T> dataMapImpl) {
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
		 * TODO: 8. implement this by calling getImpl() and checking if he key exists,
		 * only performing the operation if it doesn't
		 */
		throw new UnsupportedOperationException("method UniqueKeyDBTable.insert(PK id, T data) is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.structural.bridge.AbstractDBTable#select(java.lang.Object)
	 */
	@Override
	public T select(PK id) {
		/*
		 * TODO: 9. implement this - by calling getImpl() and retrieving the data by the key (id)
		 */
		throw new UnsupportedOperationException("method UniqueKeyDBTable.select(PK id) is not implemented yet!");
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
         *  TODO: 10. implement this - implement this by calling getImpl() and checking if he key exists, only performing the operation if it does
         */
        throw new UnsupportedOperationException("method UniqueKeyDBTable.update(PK id, T data) is not implemented yet!");
	}

}
