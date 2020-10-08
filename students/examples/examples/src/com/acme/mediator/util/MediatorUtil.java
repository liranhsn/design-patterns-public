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
package com.acme.mediator.util;

import static com.acme.mediator.util.AppConst.INVALID_BG;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * The class MediatorUtil is a utility class to help a mediator assign names and
 * refer to Component colleagues
 */
public final class MediatorUtil {

	public final static Predicate<String> NOTNULL_VALIDATOR = value -> !Objects.isNull(value) && !value.isEmpty();
	private final static String swingPackageName = "javax.swing";
	private final static String commandSetterName = "setActionCommand";
	private final static String commandGetterName = "getActionCommand";
	private final static Predicate<Class<?>> isInSwingPackage = clazz -> swingPackageName.equals(clazz.getPackageName());
	private final static Predicate<Class<?>> isJComponent = clazz -> JComponent.class.isAssignableFrom(clazz);
	private final static Predicate<Class<?>> hasCommandSetter = clazz -> {

		try {
			return null != clazz.getMethod(commandSetterName, new Class<?>[] { String.class });
		} catch (Exception ex) {
			return false;
		}
	};
	private final static Predicate<Component> hasNoName = comp -> null == comp.getName();
	private final static Consumer<JComponent> assignName = comp -> {

		if (!hasName(comp)) {

			try {
				Method actionCommandGetter = comp.getClass().getMethod(commandGetterName, new Class<?>[] {});
				String actionCommand = (String) actionCommandGetter.invoke(comp, new Object[] {});
				String name = actionCommand.toLowerCase().concat(comp.getClass().getSimpleName());
				comp.setName(name);
			} catch (Exception ignore) {
				System.out.println(ignore.getMessage());
			}
		}
	};

	private MediatorUtil() {
		super();
	}

	/**
	 * recursion to compose and return a list of all colleagues in a given
	 * container. A colleague is any JComponent from the package 'javax.swing' which
	 * has an action command setter method.
	 *
	 * @param parent the parent container
	 * @return a list of all colleagues in the given container
	 */
	public static List<JComponent> getColleagues(final Container parent) {
		Component[] children = parent.getComponents();
		List<JComponent> colleagues = new ArrayList<JComponent>();
		if (isJComponent.and(isInSwingPackage).and(hasCommandSetter).test(parent.getClass()) || hasName(parent))
			colleagues.add((JComponent) parent);

		for (Component child : children) {
			Class<?> clazz = child.getClass();
			if (isJComponent.and(isInSwingPackage).and(hasCommandSetter).test(clazz) || hasName(child))
				colleagues.add((JComponent) child);
			if (child instanceof Container)
				colleagues.addAll(getColleagues((Container) child));
		}
		return colleagues;
	}

	/**
	 * returns a stream of all colleagues in a given container. A colleague is any
	 * JComponent from the package 'javax.swing' which has an action command setter
	 * method.
	 *
	 * @param parent the parent container
	 * @return a stream of all colleagues in the given container
	 */
	public static Stream<JComponent> getColleaguesStream(final Container parent) {
		return getColleagues(parent).stream();
	}

	/**
	 * assign a name to each colleague component. A name is based on the component's
	 * action command, so components without action command string are excluded. The
	 * name of the component is composed of the lower cased action command string
	 * concatenated with the simple class name of the component.
	 *
	 * @param parent the parent container
	 */
	public static void assignColleagueNames(Container parent) {
		Component[] children = parent.getComponents();

		for (Component child : children) {
			Class<?> clazz = child.getClass();
			if (isJComponent.and(isInSwingPackage).and(hasCommandSetter).test(clazz))
				assignName.accept((JComponent) child);
			if (child instanceof Container)
				assignColleagueNames((Container) child);
		}
	}

	/**
	 * tests if a JComponent has a name
	 *
	 * @param component the component
	 * @return true if the component has a name, otherwise false
	 */
	public static boolean hasName(Component component) {
		return !hasNoName.test(component);
	}

	public static JLabel createLabel(String label, int size) {
		JLabel result = new JLabel(label);
		result.setFont(new Font("Monospaced", Font.BOLD, size));
		return result;
	}

	/*
	 * centers this frame on the screen
	 */
	public static void center(Window frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - frame.getSize().width) / 2;
		int y = (dim.height - frame.getSize().height) / 2;
		frame.setLocation(x, y);
	}

	/**
	 * A method to validate the content (text) input of a JTextFiled
	 *
	 * @param textField the field to validate
	 * @param validator a Predicate (lambda) expression to validate the content of
	 * the text input. A valid test ends in a true result.
	 * @param action the action to perform when invalid
	 * @param error the message to display in case of invalid input
	 * @param excluded a string array containing names of colleagues to exclude from
	 * disabling in case of invalid input
	 */
	public static void validateJTextField(JTextField textField, Predicate<String> validator, Consumer<Object[]> action, String error,
			String... excluded) {

		if (!validator.test(textField.getText())) {
			action.accept(excluded);
			textField.setBackground(INVALID_BG);
			JOptionPane.showMessageDialog(null, error);
		}
	}

}
