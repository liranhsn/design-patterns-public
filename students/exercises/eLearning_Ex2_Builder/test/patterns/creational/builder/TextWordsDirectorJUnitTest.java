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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class TextWordsDirectorJUnitTest is a simple sanity JUnit test case for
 * the {@link TextWordsDirector} class. It tests it's basic functionality with
 * two different builders
 */
public class TextWordsDirectorJUnitTest {

	private static final String WORDS_FILE_NAME = "words.txt";
	private WordsBuilder wordsCounterBuilder = new WordsCounterBuilder();
	private WordsBuilder dictionaryBuilder = new DictionaryBuilder();
	File inputFile = new File(WORDS_FILE_NAME);

	@BeforeEach
	public void beforeEach(TestInfo info) throws Exception {
		System.out.printf("entering %s%n", info.getDisplayName());
	}

	@Test
	@DisplayName("test create words counter builder")
	public void testCreateWordsCounterBuilder() {
		TextWordsDirector director = new TextWordsDirector(wordsCounterBuilder);
		assertNotNull(director);
	}

	@Test
	@DisplayName("test create dictionary builder")
	public void testCreateDictionaryBuilder() {
		TextWordsDirector director = new TextWordsDirector(dictionaryBuilder);
		assertNotNull(director);
	}

	@Test
	@DisplayName("test words counter builder creates words count")
	public void testWordsCounterBuilderCreatesCollection() {
		TextWordsDirector director = new TextWordsDirector(wordsCounterBuilder);
		director.produceCollection(inputFile);
		assertNotNull(wordsCounterBuilder.getCollection());
		assertFalse(wordsCounterBuilder.getCollection().isEmpty());
	}

	@Test
	@DisplayName("test dictionary builder creates dictionary")
	public void testDictionaryBuilderCreatesCollection() {
		TextWordsDirector director = new TextWordsDirector(dictionaryBuilder);
		director.produceCollection(inputFile);
		assertNotNull(dictionaryBuilder.getCollection());
		assertFalse(dictionaryBuilder.getCollection().isEmpty());
	}

	@Test
	@DisplayName("test words counter builder print collection")
	public void testWordsCounterBuilderPrintCollection() {
		TextWordsDirector director = new TextWordsDirector(wordsCounterBuilder);
		director.produceCollection(inputFile);
		try {
			director.printCollection();
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	@DisplayName("test dictionary builder print collection")
	public void testPrintSimpleDictionaryCollection() {
		TextWordsDirector director = new TextWordsDirector(dictionaryBuilder);
		director.produceCollection(inputFile);
		try {
			director.printCollection();
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}