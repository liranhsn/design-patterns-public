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
package com.acme.mediator.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Optional;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.acme.mediator.api.IMediator;
import com.acme.mediator.impl.Mediator;
import com.acme.mediator.util.MediatorUtil;

/**
 * The Class IMediatorJUnitTest tests the {@link Mediator} functionality that is
 * derived from {@link IMediator} interface
 */
class IMediatorJUnitTest {

	/*
	 * test colleagues
	 */
	IMediator<JComponent> mediator = new Mediator();
	JCheckBox cb1 = new JCheckBox();
	JCheckBox cb2 = new JCheckBox();
	JComboBox<String> list1 = new JComboBox<String>();
	JComboBox<String> list2 = new JComboBox<String>();
	JTextField input = new JTextField("test");

	@BeforeEach
	void setup() {
		cb1.setActionCommand("cb1");
		cb2.setActionCommand("cb2");
		list1.setActionCommand("list1");
		list2.setActionCommand("list2");
		input.setName("inputJTextField");
		JPanel container = new JPanel();
		container.add(cb1);
		container.add(cb2);
		container.add(list1);
		container.add(list2);
		container.add(input);
		MediatorUtil.assignColleagueNames(container);
		mediator.addColleague(cb1);
		mediator.addColleague(cb2);
		mediator.addColleague(list1);
		mediator.addColleague(list2);
		mediator.addColleague(input);
	}

	@Test
	@DisplayName("test has colleagues")
	void testHasColleagues() {
		Collection<JComponent> actual = mediator.getColleagues();
		assertFalse(actual.isEmpty());
		assertEquals(5, actual.size());
	}

	@Test
	@DisplayName("test colleagues removed")
	void testColleaguesRemoved() {
		mediator.removeColleagues();
		assertTrue(mediator.getColleagues().isEmpty());
	}

	@Test
	@DisplayName("test remove colleague")
	void testRemoveColleague() {
		mediator.removeColleague(cb1);
		Collection<JComponent> actual = mediator.getColleagues();
		assertFalse(actual.isEmpty());
		assertEquals(4, actual.size());
	}

	@ParameterizedTest(name = "test get colleague by name")
	@CsvSource({ "cb1JCheckBox", "cb1JCheckBox", "list1JComboBox", "list2JComboBox", "inputJTextField" })
	void testGetColleagueByName(String name) {
		Optional<JComponent> actual = mediator.getColleague(name);
		assertTrue(actual.isPresent());
	}

	@Test
	@DisplayName("test get checkbox colleague by name and type")
	void testGetJCheckBoxColleagueByNameAndClass() {
		Optional<?> actual = mediator.getColleague("cb1JCheckBox", JCheckBox.class);
		assertTrue(actual.isPresent());
		assertEquals(cb1, actual.get());
		assertEquals(JCheckBox.class, actual.get().getClass());
	}

	@Test
	@DisplayName("test get JComboBox colleague by name and type")
	void testGetJComboBoxColleagueByNameAndClass() {
		Optional<?> actual = mediator.getColleague("list1JComboBox", JComboBox.class);
		assertTrue(actual.isPresent());
		assertEquals(list1, actual.get());
		assertEquals(JComboBox.class, actual.get().getClass());
	}

	@Test
	@DisplayName("test get JTextField colleague by name and type")
	void testGetJTextFieldColleagueByNameAndClass() {
		Optional<JTextField> actual = mediator.getColleague("inputJTextField", JTextField.class);
		assertTrue(actual.isPresent());
		assertEquals(input, actual.get());
		assertEquals(JTextField.class, actual.get().getClass());
	}

	@Test
	@DisplayName("test get JCheckBox colleagues by type")
	void testGetJCheckBoxColleaguesByType() {
		Collection<JCheckBox> actual = mediator.getColleagues(JCheckBox.class);
		assertFalse(actual.isEmpty());
		assertEquals(2, actual.size());
	}

	@Test
	@DisplayName("test get JComboBox colleagues by type")
	void testGetJComboBoxColleaguesByType() {
		@SuppressWarnings("rawtypes")
		Collection<JComboBox> actual = mediator.getColleagues(JComboBox.class);
		assertFalse(actual.isEmpty());
		assertEquals(2, actual.size());
	}

	@Test
	@DisplayName("test get JTextField colleagues by type")
	void testGetJJTextFieldColleaguesByType() {
		Collection<JTextField> actual = mediator.getColleagues(JTextField.class);
		assertFalse(actual.isEmpty());
		assertEquals(1, actual.size());
	}

}
