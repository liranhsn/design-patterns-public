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
package patterns.behavioral.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Class FileLogger is a concrete logger implementation that writes logging
 * lines to a file via the inherited method {@link FileLogger#log(String)}. It
 * can optionally override any or none of the super class hooks.
 */
public class FileLogger extends AbstractLogger {

	private File logFile;
	private String message;

	/**
	 * Instantiates a new file logger.
	 *
	 * @param fileName the file name
	 */
	public FileLogger(String fileName) {
		super();
		logFile = new File(fileName);
	}

	/**
	 * A utility method to write text to a file.
	 *
	 * @param message the message line to write to a file
	 * @param append  if true, text will be appended to the file. Otherwise the file
	 *                will be re-written each time
	 */
	private void writeToFile(String message, boolean append) {
		try (FileWriter out = new FileWriter(logFile, append); PrintWriter writer = new PrintWriter(out);) {
			writer.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void postLogHook() {
		/*
		 * Implement this so the current message line that was written to the file, will
		 * be shown to the user on the console
		 */
		message = message.replace("\n", " ");
		System.out.println(message);
	}

	@Override
	protected void log(String message) {
		/*
		 * implement this method possibly by using the provided writeToFile utility
		 * method
		 */
		this.message = message;
		writeToFile(message, true);
	}
}
