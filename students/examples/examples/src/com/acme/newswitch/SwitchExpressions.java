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
package com.acme.newswitch;

import java.util.stream.IntStream;

import pl.allegro.finance.tradukisto.ValueConverters;

/**
 * In Java 14, it is a standard feature. In Java 13 and Java 12, it was added as
 * an preview feature.
 *
 * It has the support of multiple case labels and using yield to return value in
 * place of old return keyword.
 *
 * It also support returning value via label rules (arrow operator similar to
 * lambda).
 *
 * If we use arraw (->) operator, we can skip yield keyword.
 *
 * If we use colon (:) operator, we need to use yield keyword
 *
 *
 * In case of multiple statements, use curly braces along with yield keyword.
 *
 * In case of enum, we can skip the default case.
 *
 * If there is any missing enum value not handled in cases, compiler will
 * complain. In all other expression types (int, strings etc), we must provide
 * default case as well.
 *
 */
public class SwitchExpressions {

	enum Planet {
		MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE;
	};

	/*
	 * multiple cases arrow notation
	 *
	 * can omit default keyword if all enum values are cased
	 */
	public static Boolean isGasGiant(Planet planet) {
		return switch (planet) {
			case JUPITER, SATURN, NEPTUNE, URANUS -> true;
			case MERCURY, VENUS, EARTH, MARS -> false;
		};
	}

	/*
	 * multiple cases using yield key word
	 *
	 * can omit default keyword if all enum values are cased
	 */
	public static Boolean isJovian(Planet planet) {
		return switch (planet) {
			case JUPITER, SATURN, NEPTUNE, URANUS:
				yield true;
			case MERCURY, VENUS, EARTH, MARS:
				yield false;
		};
	}

	/*
	 * must use default if not all enum members cased
	 */
	public static String habilitationString(Planet planet) {
		return switch (planet) {
			case JUPITER, SATURN, NEPTUNE, URANUS:
				yield "uninhabitable, too gasious";
			case MERCURY, VENUS, MARS:
				yield "uninhabitable, atmosphere not suitable";
			default:
				yield "inhabitable, perfect for life!";
		};
	}

	/*
	 * must use default key word
	 */
	public static String singleDigitToWord(Integer digit) {
		return switch (digit) {
			case 0 -> "zero";
			case 1 -> "one";
			case 2 -> "two";
			case 3 -> "three";
			case 4 -> "four";
			case 5 -> "five";
			case 6 -> "six";
			case 7 -> "seven";
			case 8 -> "eight";
			case 9 -> "nine";
			default -> "not a single digit";
		};
	}

	public static String intAsWords(int digit) {
		String sign = digit < 0 ? "minus " : "";
		return switch (Math.abs(digit)) { // -2147483648
			case Integer.MIN_VALUE -> String.format("%d: %s", digit,
					sign + "two billion one hundred forty-seven million four hundred eighty-three thousand six hundred forty-eight");
			default -> {
				yield String.format("%d: %s", digit, sign + ValueConverters.ENGLISH_INTEGER.asWords(Math.abs(digit)));
			}
		};
	}

	/*
	 * demo
	 */
	public static void main(String[] args) {
		System.out.printf("%s%n", intAsWords(Integer.MIN_VALUE));

		IntStream.range(-12, 12)
			.mapToObj(SwitchExpressions::intAsWords)
			.forEach(System.out::println);

		System.out.printf("%s%n", intAsWords(Integer.MAX_VALUE));

		System.out.println();

		IntStream.range(0, Planet.values().length)
			.mapToObj(i -> String.format("%s: %s", Planet.values()[i], habilitationString(Planet.values()[i])))
			.forEach(System.out::println);

	}

}
