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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * The Class TextWordsDirector manages the dictionary building process by
 * delegating the build process stages to it's dependent (wrapped) builder.
 */
public class TextWordsDirector {

	/*
	 * TODO: 1. This director is complete as it is. Just review it, nothing to do
	 * here
	 */

	private WordsBuilder builder;

	/**
	 * Instantiates a new text words director.
	 *
	 * @param builder the builder to delegate work to
	 */
	public TextWordsDirector(WordsBuilder builder) {
		this.builder = builder;
	}

	public void printCollection() {
		Collection<?> dictionary = this.builder.getCollection();
		System.out.printf("%s: (%d entries)%n%n", "Your dictionary contains the following entries", dictionary.size());
		dictionary.forEach(System.out::println);
	}

	/**
	 * Produce collection.
	 *
	 * @param textFile the text file
	 */
	public void produceCollection(File textFile) {
		try (BufferedReader reader = new BufferedReader(new FileReader(textFile));) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				Scanner scanner = new Scanner(line);
				while (scanner.hasNext()) {
					String word = scanner.next();
					word = word.replaceAll("[^\\w]", ""); // remove none 'wordly' characters from the string
					this.builder.addWord(word);
				}
				scanner.close();
			}
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
			WordsBuilder builder = new WordsCounterBuilder();
			// WordsBuilder builder = new DictionaryBuilder();
			TextWordsDirector director = new TextWordsDirector(builder);
			director.produceCollection(inputFile);
			director.printCollection();
			System.out.println(">>> Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
