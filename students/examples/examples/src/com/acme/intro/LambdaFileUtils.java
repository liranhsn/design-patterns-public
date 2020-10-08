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
package com.acme.intro;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.function.Consumer;

/**
 * The Class LambdaFileUtils is a utility class demonstrating usage of lambda
 * expressions to keep the DRY principal
 */
public class LambdaFileUtils {

	private LambdaFileUtils() {
		super();
	}

	/**
	 * A facade to set a file writeable, or a director's entire content This is a
	 * facade to the setWriteable action.
	 *
	 * @param file the file
	 * @throws Exception the exception
	 */
	public static void setWriteable(File file) throws Exception {
		recurse(file, f -> f.setWritable(true));
	}

	/**
	 * A facade to delete a file, or a director's entire content This is a facade to
	 * the delete action.
	 *
	 * @param file the file
	 * @throws Exception the exception
	 */
	public static void delete(File file) throws Exception {
		recurse(file, File::delete);
	}

	/**
	 * A facade to sets a file or directory's content read only.
	 *
	 * @param file the new read only
	 * @throws Exception the exception
	 */
	public static void setReadOnly(File file) throws Exception {
		recurse(file, File::setReadOnly);
	}

	/**
	 * Recurse a file or directory and perform a {@code fileAction} on the given
	 * {@code file}.
	 *
	 * @param file     the file to perform an action upon
	 * @param consumer the consumer
	 * @throws Exception if anything goes wrong
	 */
	private static void recurse(File file, Consumer<File> consumer) throws Exception {
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			for (File child : children) {
				recurse(child, consumer);
			}
		}
		consumer.accept(file);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		// create temp.txt for the test
		File file = new File("temp.txt");
		PrintWriter writer = new PrintWriter(
				new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)), "UTF-8"));
		writer.write("This is a test!");
		writer.close();
		System.out.println("Created file " + file.getAbsolutePath());
		System.out.println("Is file writeable? " + file.canWrite());
		// call operations
		LambdaFileUtils.setReadOnly(file);
		System.out.println("Is file writeable? " + file.canWrite());
		LambdaFileUtils.setWriteable(file);
		System.out.println("Is file writeable? " + file.canWrite());
		LambdaFileUtils.delete(file);
		System.out.println(file.getName() + " exists? " + file.exists());
	}
}
