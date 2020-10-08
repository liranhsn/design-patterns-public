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

/**
 * The Class FileUtils is a classic utility class pattern set of functions to
 * deal with file operations.
 *
 * WE WILL OPTIMIZE THIS CLASS LATER
 */
public class OldFileUtils {

	private OldFileUtils() {
		super();
	}

	/**
	 * Delete file, or a director's entire content
	 *
	 * @param file       the file
	 * @param fileAction the file action
	 * @throws Exception the exception
	 */
	public static void delete(File file) throws Exception {
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			for (File child : children)
				delete(child);
		}
		file.delete();
	}

	/**
	 * Set file, or directory+entire content only:.
	 *
	 * @param file   the new read only
	 * @param action the action
	 * @throws Exception the exception
	 */
	public static void setReadOnly(File file) throws Exception { // same recursion,
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			for (File child : children)
				setReadOnly(child);
		}
		file.setReadOnly();
	}

	/**
	 * Set a file or entire directory content writable
	 *
	 * @param file   the new read only
	 * @param action the action
	 * @throws Exception the exception
	 */
	public static void setWritable(File file) throws Exception { // same recursion,
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			for (File child : children)
				setWritable(child);
		}
		file.setWritable(true);
	}

	public static void main(String[] args) throws Exception {
		// create temp.txt for the test
		File file = new File("temp.txt");
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)), "UTF-8"));
		writer.write("This is a test!");
		writer.close();
		System.out.println("Created file " + file.getAbsolutePath());
		System.out.println("Is file writeable? " + file.canWrite());
		// call operations
		OldFileUtils.setReadOnly(file);
		System.out.println("Is file writeable? " + file.canWrite());
		OldFileUtils.setWritable(file);
		System.out.println("Is file writeable? " + file.canWrite());
		OldFileUtils.delete(file);
		System.out.println(file.getName() + " exists? " + file.exists());
	}
}
