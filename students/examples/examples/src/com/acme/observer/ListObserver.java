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
package com.acme.observer;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class ListObserver<E> implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(getClass().getName() + " -> Change occured in " + o.getClass().getSimpleName() + " (change: '" + arg + "')");
	}

	public static void main(String[] args) {
		ObservableList<String> observableList = new ObservableList<>();
		ListObserver<String> listObserver = new ListObserver<>();
		observableList.addObserver(listObserver);
		observableList.add("Tomer");
		observableList.add("Silverman");
		observableList.add("Design Patterns");
		System.out.printf("%n%s%n%n",observableList);
		observableList.remove("blah"); // no change
		observableList.set(0, "tomer");
		observableList.set(1, "silverman");
		observableList.remove("Design Patterns");
		System.out.printf("%n%s%n%n",observableList);
		observableList.clear();
		System.out.printf("%n%s%n%n",observableList);

	}
}
