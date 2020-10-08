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
