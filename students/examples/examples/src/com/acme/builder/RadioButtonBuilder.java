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
package com.acme.builder;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

class RadioButtonBuilder extends Builder {

	private Box box = Box.createVerticalBox();
	private ButtonGroup group = new ButtonGroup();

	@Override
	public void addPart(String choices) {
		JRadioButton bt = new JRadioButton(choices);
		group.add(bt);
		box.add(bt);
	}

	@Override
	public void addSeparator() {
		box.add(new JSeparator());
	}

	// Return the Complex object
	@Override
	public Component getResult() {
		group.getElements().nextElement().setSelected(true); // select first radio button
		return box;
	}
}
