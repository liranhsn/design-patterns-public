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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.Arrays;

import javax.swing.JFrame;

public class ComboDirector {

	private Builder builder;

	public ComboDirector(Builder builder) {
		super();
		this.builder = builder;
	}

	public Component create(String[] elements) {
		Arrays.stream(elements).forEach(c -> {
			if (c == null)
				builder.addSeparator();
			else
				builder.addPart(c);
		});
		return builder.getResult();
	}

	private void center(Frame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = frame.getSize().width;
		int h = frame.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		frame.setLocation(x, y);
	}

	public void showGUI(String title, Component child) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(child, BorderLayout.NORTH);
		frame.setSize(200, 200);
		center(frame);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//ComboDirector director = new ComboDirector(new RadioButtonBuilder());
		ComboDirector director = new ComboDirector(new DropdownBuilder());
		String[] elements = { "black", "white", null, "red", "green", "blue", null, "yellow" };
		Component comp = director.create(elements);
		director.showGUI("Radio Button Builder", comp);
	}
}
