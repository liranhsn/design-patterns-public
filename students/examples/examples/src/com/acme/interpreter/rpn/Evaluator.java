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
package com.acme.interpreter.rpn;

import java.util.Scanner;
import java.util.Stack;

public class Evaluator {

	private Expression syntaxTree;

	public Evaluator(String expression) {
		Stack<Expression> expressionStack = new Stack<Expression>();
		try (Scanner scanner = new Scanner(expression)) {
			while (scanner.hasNext()) {
				String token = scanner.next();
				if (token.equals("+")) {
					Expression subExpression = new PlusExp(expressionStack.pop(), expressionStack.pop());
					expressionStack.push(subExpression);
				} else if (token.equals("-")) {
					Expression subExpression = new MinusExp(expressionStack.pop(), expressionStack.pop());
					expressionStack.push(subExpression);
				} else if (token.equals("*")) {
					Expression subExpression = new MultExp(expressionStack.pop(), expressionStack.pop());
					expressionStack.push(subExpression);
				} else if (token.equals("/")) {
					Expression subExpression = new DivExp(expressionStack.pop(), expressionStack.pop());
					expressionStack.push(subExpression);
				} else {
					expressionStack.push(new VarExp(token));
				}
			}
		}
		this.syntaxTree = expressionStack.pop();
	}

	public double evaluate(Context context) {
		return this.syntaxTree.eval(context);
	}
}
