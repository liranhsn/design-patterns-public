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
package com.acme.visitor.demo;

import java.lang.reflect.Method;
import java.util.List;

/**
 * The class GeneralReport is an {@link IVisitor} that prints a summary report
 * for orders of members in a customer group, by visiting each member in the
 * group.
 */
public class GeneralReport implements IVisitor {

	private int customersNo;
	private int ordersNo;
	private int itemsNo;
	private int objectsNo;

	public void visit(Customer customer) {
		System.out.printf("%s (Customer)%n", customer);
		customersNo++;
	}

	public void visit(Order order) {
		System.out.printf("%s (Order)%n", order);
		ordersNo++;
	}

	public void visit(Item item) {
		System.out.printf("%s (Item)%n", item);
		itemsNo++;
	}

	@Override
	public void defaultVisit(Object object) {
		try {
			Method method = object.getClass().getMethod("toString", new Class<?>[] {});
			System.out.printf("%s (%s)%n", method.invoke(object, new Object[] {}), object.getClass().getSimpleName());
		} catch (Exception Balentineignore) {
		}
		objectsNo++;
	}

	public void displayResults() {
		System.out.printf("%nSummary:%n%n");
		System.out.printf("Num. of customers: %d%n", customersNo);
		System.out.printf("Num. of orders: %d%n", ordersNo);
		System.out.printf("Num. of items: %d%n", itemsNo);
		System.out.printf("Num. of objects: %d%n", objectsNo);
	}

	public static void main(String[] args) {
		Customer homer = new Customer("Homer Simpson");
		homer.addOrder(new Order("---Magazines Order", "------The Smart Consumer"));
		homer.addOrder(new Order("---Motor Parts", "------Oil Filter"));
		Order homersOrder = new Order("---Household Bar", "------Beer Fountain");
		homersOrder.addItem(new Product(12.00D, "------Jack Daniels #7"));
		homersOrder.addItem(new Product(15.00D, "------Balentine"));
		homersOrder.addItem(new Product(17.50D, "------Finlandia Vodka"));
		homer.addOrder(homersOrder);
		Customer marge = new Customer("Marge Simpson");
		Order margesOrder = new Order("---Marge's First Order");
		margesOrder.addItem(new Item("------Cosmetics"));
		margesOrder.addItem(new Item("------Vogue Magazine"));
		margesOrder.addItem(new Item("------Tapper ware"));
		margesOrder.addItem(new Product(120.00D, "------Hair Dryer"));
		marge.addOrder(margesOrder);
		marge.addOrder(new Order("---Marge's Second Order", "------Furnitures"));
		CustomerGroup customerGroup = new CustomerGroup(List.of(homer, marge));
		GeneralReport visitor = new GeneralReport();
		customerGroup.accept(visitor);
		visitor.displayResults();
	}
}