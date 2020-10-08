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
package patterns.creational.builder;

import java.util.Collection;
import java.util.SortedMap;

/**
 * The Class CounterDictionaryBuilder counts the appearance of each word in the
 * collection (dictionary) of words. The words are stored in a map where the key
 * is the mapand the value is the count of appearances
 */
public class CounterDictionaryBuilder implements DictionaryBuilder {

	/*
	 * TODO: 5. Remove this when the class is implemented
	 */
	@SuppressWarnings("unused")
	private SortedMap<String, Integer> dictionary; // the words count map.

	/**
	 * Instantiates a new words counter builder.
	 */
	public CounterDictionaryBuilder() {
		super();
		/*
		 * TODO: 2. You'll need to instantiate the dictionary. If you want a sorted map
		 * implementation you can use a TreeMap. Since Stings are comparable the TreeMap
		 * will not need a comparator in the constructor to sort it's entries so you can
		 * do something like dictionary = new TreeMap<String, Integer>();
		 */
		throw new UnsupportedOperationException("Constructor CounterDictionaryBuilder() is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.builder.DictionaryBuilder#addWord(java.lang.String)
	 */
	@Override
	public void addWord(String word) {
		/*
		 * TODO: 3. You'll need to extract the count of the word from the map. If it
		 * exists, you'll have to increment it by one and put it back in the map under
		 * the same key. Other wise, this is the first time you encountered the word and
		 * it's count is therefore 1.
		 */
		throw new UnsupportedOperationException("method CounterDictionaryBuilder.addWord(String word) is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.builder.DictionaryBuilder#getDictionary()
	 */
	@Override
	public Collection<?> getDictionary() {
		/*
		 * TODO: 4. Return the dictionary's entry set
		 */
		throw new UnsupportedOperationException("method CounterDictionaryBuilder.getCollection() is not implemented yet!");
	}
}
