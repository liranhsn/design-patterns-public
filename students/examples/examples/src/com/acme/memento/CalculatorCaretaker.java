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
package com.acme.memento;

/**
 * Simulates a user using the calculator to add numbers, the user calculates a
 * result, then enters wrong numbers, he is not satisfied with the result and he
 * hits Ctrl + Z to undo last operation and restore previous result.
 */
public class CalculatorCaretaker {

	public static void main(String[] args) {
		ICalculator<Double> calc = new Calculator<>(); // program starts
		Double op1 = 22D;
		Double op2 = 7D;
		// assume user enters two numbers
		calc.setOperand1(op1);
		calc.setOperand2(op2);
		// calc.setOperation((a,b) -> new BigDecimal(a.toString()).divide(new
		// BigDecimal(b.toString()), MathContext.DECIMAL32));
		calc.setOperation(calc.division());
		// find result
		System.out.printf("%d) (%s) result of %s / %s = %s%n", 1, "default state", op1, op2, calc.getResult());
		// Store result of this calculation in case of error
		IMemento memento = calc.createMemento();
		op1 = 22D;
		op2 = 8D;
		// user enters a number
		calc.setOperand1(op1);
		// user enters a wrong second number and calculates result
		calc.setOperand2(op2);
		// calculate result
		System.out.printf("%d) (%s) result of %s / %s = %s%n", 2, "new state", op1, op2, calc.getResult());
		// user hits CTRL + Z to undo last operation and see last result
		calc.setMemento(memento);
		// result restored
		System.out.printf("%d) (%s) result of %s + %s = %s%n", 3, "restored state", calc.getOperand1(), calc.getOperand2(), calc.getResult());
	}
}
