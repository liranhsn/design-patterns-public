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

import java.util.Arrays;
import java.util.List;

/**
 * A simple demo of the Roman numeral interpreter
 */
public class InterpreterDemo {

	static List<Expression> tree = List.of(
			new ThousandExpression(),
			new HundredExpression(),
			new TenExpression(),
			new OneExpression());

	public static int interpret(String roman) {
		Context context = new Context(roman);
		tree.forEach(exp -> exp.interpret(context));
		return context.getOutput();
	}

	public static void main(String[] args) {
		String[] singles = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
		String[] tens = { "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C",
				"CX", "CXX", "CXXX", "CXL", "CL", "CLX", "CLXX", "CLXXX",
				"CXC", "CC" };
		String[] hundreds = { "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "M" };
		String[] dates = { "MDCCLXXVI", "MCMXLVIII", "MCMLXIX", "MMXX" };
		Arrays.stream(singles).forEach(roman -> {
			int num = interpret(roman);
			System.out.printf("%s = %d%n", roman, num);
		});
		System.out.println();
		Arrays.stream(tens).forEach(roman -> {
			int num = interpret(roman);
			System.out.printf("%s = %d%n", roman, num);
		});
		System.out.println();
		Arrays.stream(hundreds).forEach(roman -> {
			int num = interpret(roman);
			System.out.printf("%s = %d%n", roman, num);
		});
		System.out.println();
		Arrays.stream(dates).forEach(roman -> {
			int num = interpret(roman);
			System.out.printf("%s = %d%n", roman, num);
		});

	}
}