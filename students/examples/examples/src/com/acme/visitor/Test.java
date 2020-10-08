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
package com.acme.visitor;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * The class Test builds a {@link Chapter} out of a file and lets a
 * {@link SimpleVisitor} visit the chapter
 */
public class Test {

	public static void main(String[] args) throws Exception {
		Chapter chapter = buildChapter("visitor.txt");
		Visitor visitor = new SimpleVisitor();
		chapter.accept(visitor);
	}

	public static Chapter buildChapter(String fileName) throws Exception {
		List<Sentence> sentences = new LinkedList<Sentence>();
		try (Scanner chapterScanner = new Scanner(new FileInputStream(new File(fileName)));) {
			chapterScanner.useDelimiter("\\.");
			while (chapterScanner.hasNext()) {
				String line = chapterScanner.next().trim();
				line = line.replaceAll("\n", " ");
				Scanner wordsScanner = new Scanner(line);
				wordsScanner.useDelimiter(" ");
				List<Word> words = new LinkedList<Word>();
				while (wordsScanner.hasNext()) {
					String word = wordsScanner.next();
					words.add(new Word(word));
				}
				wordsScanner.close();
				Sentence sentence = new Sentence(words);
				sentences.add(sentence);
			}
		}
		Sentence title = sentences.get(0);
		sentences.remove(title);
		return new Chapter(title, sentences);
	}
}
