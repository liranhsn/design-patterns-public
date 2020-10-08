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

/**
 * ClubSodaDecorator is a concrete decorator that accepts a another drink in
 * it's constructor. But - it's also a drink.
 */
public class ClubSodaDecorator extends AbstractDrinkDecorator {

	public ClubSodaDecorator() {
		super();
		this.price = 3;
		this.description = "bubbling club soda";
	}

	public ClubSodaDecorator(AbstractDrink drink) {
		this();
		this.drink = drink;
	}

	@Override
	public double getPrice() {
		return this.price + (this.drink == null ? 0 : this.drink.getPrice());
	}

	@Override
	public String toString() {
		return String.format("%s for a price of $%.2f", description, price);
	}
}