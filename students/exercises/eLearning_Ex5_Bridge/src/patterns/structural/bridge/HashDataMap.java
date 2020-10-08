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

import java.util.HashMap;
import java.util.Map;

/**
 * The Class HashDataMap is a default implementation of the {@link DataMap}
 * interface
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class HashDataMap<K, V> implements DataMap<K, V> {

	// TODO: 11. remove this when implemented
	@SuppressWarnings("unused")
	private Map<K, V> dataMap;

	public HashDataMap() {
		super();
		this.dataMap = new HashMap<K, V>();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.structural.bridge.DataMap#get(java.lang.Object)
	 */
	@Override
	public V get(K key) {
		/*
		 *  TODO: 1. get the value from the dataMap, using the key
		 */
		throw new UnsupportedOperationException("method HashDataMap.get(K key) is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.structural.bridge.DataMap#keyExists(java.lang.Object)
	 */
	@Override
	public boolean keyExists(K key) {
		/*
		 * TODO: 2. check if the key exists in the data map
		 */
		throw new UnsupportedOperationException("method HashDataMap.keyExists(K key) is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.structural.bridge.DataMap#put(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void put(K key, V value) {
		/*
		 * TODO: 3. put the key and value in the data map
		 */
		throw new UnsupportedOperationException("method HashDataMap.put(K key, V value) is not implemented yet!");
	}
}
