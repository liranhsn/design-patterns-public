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
package com.acme.builder.generic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

/**
 * The Class GenericOptionsBuilder is the builder's abstraction
 *
 * @param <C> the generic type of the Component sub-type to build
 * @param <I> the generic type of the input to feed the
 *            {@link #buildItem(Object)} method
 *
 * @author T.Silverman
 */
abstract class GenericOptionsBuilder<C extends Component, I> {

	public abstract void buildItem(I input);

	public abstract void buildSeparator();

	public abstract C get();

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Director director = new Director();
		String[] strings = { "black", "white", null, "red", "green", "blue" };
		Integer[] primes = { 1, 3, 5, 7, null, 11, 13, 17, 19, null, 23 };
		JComboBox<String> combo1 = director.create(new JComboBoxBuilder<String>(), strings);
		JComboBox<Integer> combo2 = director.create(new JComboBoxBuilder<Integer>(), primes);
		Component /* or Box */ box = director.create(new RadioButtonBoxBuilder(), strings);
		// director.showGUI("Droplist builder", combo1);
		//director.showGUI("Primes Droplist builder", combo2);
		director.showGUI("Radio Button builder", box);
	}
}

/**
 * The class JComboBoxBuilder builds a {@link JComboBox} based drop list
 *
 * @param <E> The type of element the JComboBox contains
 */
class JComboBoxBuilder<E> extends GenericOptionsBuilder<JComboBox<E>, E> {

	private JComboBox<E> dropdown = new JComboBox<>();

	@Override
	public void buildItem(E item) {
		dropdown.addItem(item);
	}

	@Override
	public void buildSeparator() {
		dropdown.addItem(null);
	}

	@Override
	public JComboBox<E> get() {
		return dropdown;
	}
}

class RadioButtonBoxBuilder extends GenericOptionsBuilder<Box, String> {

	private Box box = Box.createVerticalBox();
	private ButtonGroup group = new ButtonGroup();

	@Override
	public void buildItem(String item) {
		JRadioButton bt = new JRadioButton(item);
		group.add(bt);
		box.add(bt);
	}

	@Override
	public void buildSeparator() {
		box.add(new JSeparator());
	}

	@Override
	public Box get() {
		group.getElements().nextElement().setSelected(true); // select first radio button
		return box;
	}
}

class Director {

	/**
	 * creates any droplist component
	 *
	 * @param <I>     the type of element in the input array
	 * @param <C>     the type of component to build
	 * @param <B>     the type of builder to use - sub class of
	 *                GenericOptionsBuilder
	 * @param builder the builder
	 * @param items   the input array
	 * @return the Component
	 */
	public <I, C extends Component, B extends GenericOptionsBuilder<C, I>> C create(B builder, I[] items) {
		Arrays.stream(items).forEach(c -> {
			if (c == null)
				builder.buildSeparator();
			else
				builder.buildItem(c);
		});
		return builder.get();
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
}
