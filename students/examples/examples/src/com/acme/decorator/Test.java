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
package com.acme.decorator;

import com.acme.decorator.drink.AbstractDrink;
import com.acme.decorator.drink.Scotch;
import com.acme.decorator.drink.Vodka;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// make vodka
		AbstractDrink vodka = new Vodka();
		System.out.printf("We start with %s%n", vodka);
		// mix orange juice and vodka
		AbstractDrinkDecorator vodkaOJ = new OrangeJuiceDecorator(vodka);
		System.out.printf("We add %s%n", vodkaOJ);
		// mix lime juice in the orange vodka
		AbstractDrinkDecorator vodkaOJLime = new LimeJuiceDecorator(vodkaOJ);
		System.out.printf("Then we add %s%n", vodkaOJLime);
		// lime, orange juice and base vodka price combined
		System.out.printf("And the total cost is $%.2f%n", vodkaOJLime.getPrice());
		System.out.println();
		// make scotch
		AbstractDrink scotch = new Scotch();
		System.out.printf("We start with %s%n", scotch);
		// mix soda
		AbstractDrinkDecorator scotchSoda = new ClubSodaDecorator(scotch);
		System.out.printf("We add: %s%n", scotchSoda);
		// soda and scotch total
		System.out.printf("And the total cost is $%.2f%n", scotchSoda.getPrice());
	}
}
