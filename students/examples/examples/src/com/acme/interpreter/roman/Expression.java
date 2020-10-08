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
package com.acme.interpreter.roman;

/**
 * The class Expression is the base for a Roman numeral expression sub class
 */
public abstract class Expression {

	/*
	 * template method to interpret a Roman numerals expression
	 */
	public void interpret(Context context) {
		if (context.getInput().length() == 0)
			return;
		if (context.getInput().startsWith(nine())) {
			context.setOutput(context.getOutput() + (9 * multiplier()));
			context.setInput(context.getInput().substring(2));
		} else if (context.getInput().startsWith(four())) {
			context.setOutput(context.getOutput() + (4 * multiplier()));
			context.setInput(context.getInput().substring(2));
		} else if (context.getInput().startsWith(five())) {
			context.setOutput(context.getOutput() + (5 * multiplier()));
			context.setInput(context.getInput().substring(1));
		}

		while (context.getInput().startsWith(one())) {
			context.setOutput(context.getOutput() + (1 * multiplier()));
			context.setInput(context.getInput().substring(1));
		}
	}

	public abstract String one();

	public abstract String four();

	public abstract String five();

	public abstract String nine();

	public abstract int multiplier();
}
