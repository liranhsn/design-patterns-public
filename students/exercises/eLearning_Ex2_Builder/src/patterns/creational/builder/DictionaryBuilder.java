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
import java.util.SortedSet;

/**
 * The Class DictionaryBuilder is a very simple {@link WordsBuilder} where each
 * word is uniquely stored in a set
 */
public class DictionaryBuilder implements WordsBuilder {

	// TODO: 9. Remove this annotation when this class is implemented
	@SuppressWarnings("unused")
	private SortedSet<String> dictionary;

	/**
	 * Instantiates a new dictionary builder.
	 */
	public DictionaryBuilder() {
		super();
		/*
		 * TODO: 6. You'll need to instantiate the dictionary. If you want a sorted set
		 * implementation you can use a TreeSet. Since Stings are comparable the TreeSet
		 * will not need a comparator in the constructor to sort it's entries so you can
		 * do something like dictionary = new TreeSet<String>();
		 */
		throw new UnsupportedOperationException("Constructor DictionaryBuilder() is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.builder.WordsBuilder#addWord(java.lang.String)
	 */
	@Override
	public void addWord(String word) {
		/*
		 * TODO: 7. add a word to the dictionary
		 */
		throw new UnsupportedOperationException("method DictionaryBuilder.addWord(String word) is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.builder.WordsBuilder#getCollection()
	 */
	@Override
	public Collection<?> getCollection() {
		// TODO: 8. Return the collection
		throw new UnsupportedOperationException("method DictionaryBuilder.getCollection() is not implemented yet!");
	}
}
