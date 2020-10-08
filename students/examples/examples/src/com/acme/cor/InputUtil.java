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
package com.acme.cor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class InputUtil is a utility class designed to ease accepting user input
 * via the standard in. All of this class methods accept input through the a
 * call to the method {@link #input(String)}. The <tt>input(String)</tt> method
 * accepts a String parameter which is prompt back to the user. This method
 * blocks until the user enters some input. Other methods of this class are
 * designed to accept a specific type of input such as integer or long values,
 * or a yes | no value (Y/N). These methods repeat the prompt until the user
 * enters a valid value.
 *
 * @author tnsilver
 */
public class InputUtil {

	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static final String QUIT = "quit";

	/**
	 * Hidden private constructor.
	 */
	private InputUtil() {
		super();
	}

	/**
	 * Returns the user input as a String. This method blocks until the user enters
	 * some input. Empty or white space Strings do not numOfMoves as valid input.
	 * This method will repeat the prompt until a valid input is entered.
	 *
	 * @param prompt - The prompt to display to the user
	 * @return - The user String input
	 */
	public static String input(String prompt) {
		try {
			System.out.print(prompt + " ");
			String line = in.readLine();
			while (line == null || line.isEmpty()) {
				System.out.print("Expecting input! > " + prompt + " ");
				line = in.readLine();
			}
			if (line.equalsIgnoreCase(QUIT)) {
				System.out.println("Goodbye!");
				System.exit(0);
			}
			return line;
		} catch (IOException e) {
			return ("<input error>");
		}
	}

	/**
	 * Waits to read a single character from the user's input, otherwise displays
	 * the options over and over again.
	 *
	 * @param prompt  - The prompt to display to the user
	 * @param options - The input options
	 * @return - The user's char input option
	 */
	public static String inputOneChar(String prompt, String options) {
		String line = "";
		while ((line.length() != 1) || (options.indexOf(line.toUpperCase()) < 0)) {
			line = input(prompt);
		}
		return line;
	}

	/**
	 * Waits for the user for a Y | N input (case insensitive).
	 *
	 * @param prompt - The prompt to display to the user
	 * @return - The user input choice translated to a boolean
	 */
	public static boolean inputYesOrNo(String prompt) {
		String line = inputOneChar(prompt, "YN");
		return ("Y".equalsIgnoreCase(line));
	}

	/**
	 * Waits for the user input to be translated as a valid Long value.
	 *
	 * @param prompt - The prompt to display to the user
	 * @return - The user input choice translated to a long
	 */
	public static long inputLong(String prompt) {
		boolean isLong = false;
		Long value = null;
		String out = prompt;
		do {
			try {
				String line = input(out);
				value = Long.parseLong(line);
				isLong = true;
			} catch (NumberFormatException nfe) {
				out = "Expecting a long value! > " + prompt;
			}
		} while (!isLong);
		return value;
	}

	/**
	 * Input double.
	 *
	 * @param prompt the prompt
	 * @return the double
	 */
	public static double inputDouble(String prompt) {
		boolean isDouble = false;
		Double value = null;
		String out = prompt;
		do {
			try {
				String line = input(out);
				value = Double.parseDouble(line);
				isDouble = true;
			} catch (NumberFormatException nfe) {
				out = "Expecting a double value! > " + prompt;
			}
		} while (!isDouble);
		return value;
	}

	/**
	 * Waits for the user input to be translated as a valid integer value.
	 *
	 * @param prompt - The prompt to display to the user
	 * @return - The user input choice translated to an integer
	 */
	public static int inputInt(String prompt) {
		boolean isInteger = false;
		Integer value = null;
		String out = prompt;
		do {
			try {
				String line = input(out);
				value = Integer.parseInt(line);
				isInteger = true;
			} catch (NumberFormatException nfe) {
				out = "Expecting an integer value! > " + prompt;
			}
		} while (!isInteger);
		return value;
	}

	/**
	 * Input date.
	 *
	 * @param prompt  the prompt
	 * @param pattern the pattern
	 * @return the date
	 */
	public static Date inputDate(String prompt, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		boolean ok = false;
		do {
			try {
				String inputDate = InputUtil.input(prompt);
				date = format.parse(inputDate);
				ok = true;
			} catch (ParseException pex) {
				System.out.println("Try again...");
			}
		} while (!ok);
		return date;
	}
}
