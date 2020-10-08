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

/**
 * The Class ConsoleLogger is a simple logger implementation that writes to the
 * console. It should override the super {@link AbstractLogger#log(String)}
 * method and can possible override any of the hooks
 */
public class ConsoleLogger extends AbstractLogger {

	public ConsoleLogger() {
		super();
	}

	@Override
	protected void preLogHook() {
		/*
		 * Implement this hook so a 50 characters long line of '-' is printed before
		 * each log entry
		 */
		System.out.println("--------------------------------------------------");
	}

	@Override
	protected void log(String message) {
		/*
		 * Implement this simple logging method (write to the console using System.out)
		 */
		System.out.println(message);
	}
}
