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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * The Class DictionaryDirector manages the dictionary building process by
 * delegating the build process stages to it's dependent (wrapped) builder.
 */
public class DictionaryDirector {

	// This director is complete. Just review it, nothing to do here

	/** The builder. */
	private DictionaryBuilder builder;

	/**
	 * Instantiates a new text words director.
	 *
	 * @param builder the builder
	 */
	public DictionaryDirector(DictionaryBuilder builder) {
		this.builder = builder;
	}

	/**
	 * Prints the collection.
	 */
	public void printDictionary() {
		Collection<?> dictionary = this.builder.getDictionary();
		System.out.printf("%s: (%d entries)%n%n", "Your dictionary contains the following entries", dictionary.size());
		dictionary.forEach(System.out::println);
	}

	/**
	 * Produce collection.
	 *
	 * @param textFile the text file
	 */
	public void populateDictionary(File textFile) {
		try (BufferedReader reader = new BufferedReader(new FileReader(textFile));) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				Scanner scanner = new Scanner(line);
				while (scanner.hasNext()) {
					String word = scanner.next();
					word = word.replaceAll("[^\\w]", ""); // remove none 'word' characters
					this.builder.addWord(word);
				}
				scanner.close();
			}
			reader.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			File inputFile = new File("words.txt");
			// DictionaryBuilder builder = new CounterDictionaryBuilder();
			DictionaryBuilder builder = new ClassicDictionaryBuilder();
			DictionaryDirector director = new DictionaryDirector(builder);
			director.populateDictionary(inputFile);
			director.printDictionary();
			System.out.println(">>> Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
