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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class AbstractLogger defines the methods for logging. It defines two
 * hooks, {@link #preLogHook()} and {@link #postLogHook()} an abstract template
 * method {@link #log(String)} to be implemented by sub-classes
 */
public abstract class AbstractLogger {

	private StringBuilder buffer = new StringBuilder();
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSSSSS");

	public AbstractLogger() {
		super();
	}

	protected String getMessage() {
		return buffer.toString();
	}

	/**
	 * Any sub class that want to perform some actions before the actual logging,
	 * can override this method.
	 */
	protected void preLogHook() {
	}

	/**
	 * Any sub class that want to perform some actions after the actual logging, can
	 * override this method.
	 */
	protected void postLogHook() {
	}

	/**
	 * Log a message containing a possible {@code header} and a message
	 * {@code body}. Both should be preceded with a date. The actual logging is done
	 * by the implementing sub-class
	 *
	 * @param header the header of the message or null if none exists
	 * @param body   the body of the message
	 */
	public void log(String header, String body) {
		/*
		 * define the general algorithm for logging a message using the preLogHook, then
		 * the log() method, and finally, the postLogHook method. You can, for example,
		 * format a date and a header line before the body
		 */
		buffer.setLength(0);
		String message = String.format("%s: [%2$s]%n%3$s", LocalDateTime.now().format(formatter), header, body);
		buffer.append(message);
		preLogHook();
		log(message);
		postLogHook();
	}

	/**
	 * the template method
	 *
	 * @param message the message
	 */
	protected abstract void log(String message);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			AbstractLogger logger = new ConsoleLogger();
			// AbstractLogger logger = new FileLogger("log.txt");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
