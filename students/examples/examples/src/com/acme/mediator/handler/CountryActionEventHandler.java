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
package com.acme.mediator.handler;

import static com.acme.mediator.util.Action.COUNTRY;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import com.acme.mediator.impl.BaseMediator;
import com.acme.mediator.util.Action;
import com.acme.utils.GeoDB;
import com.acme.utils.GeoDB.KeyValue;

/**
 * The Class CountryActionEventHandler is handling an event that is triggered
 * when a country is selected from the country drop list. In such a case, the
 * states list is populated.
 */
public class CountryActionEventHandler extends BaseEventHandler {

	/**
	 * Instantiates a new country action event handler.
	 *
	 * @param mediator the mediator
	 */
	public CountryActionEventHandler(BaseMediator mediator) {
		super(mediator, ActionEvent.class);
	}

	/**
	 * Can handle.
	 *
	 * @param <E> the element type
	 * @param e the e
	 * @return true, if successful
	 */
	@Override
	protected <E extends AWTEvent> boolean canHandle(E e) {
		ActionEvent event = (ActionEvent) e;
		return Action.valueOf(event.getActionCommand()).equals(COUNTRY);
	}

	/**
	 * Handle event.
	 *
	 * @param <E> the element type
	 * @param event the event
	 */
	@Override
	public <E extends AWTEvent> void handleEvent(E event) {
		System.out.printf(
			"%s.handleEvent() -> handling %s with parameters %s%n",
			getClass().getSimpleName(),
			event.getClass().getSimpleName(),
			event.paramString());
		SwingUtilities.invokeLater(() -> populateStates());
	}

	/**
	 * Populate the stateJComboBox model with a list of KeyValue states
	 */
	@SuppressWarnings("unchecked")
	private void populateStates() {
		JComboBox<KeyValue> stateJComboBox = mediator.getColleague("stateJComboBox", JComboBox.class).get();
		JComboBox<KeyValue> countryJComboBox = mediator.getColleague("countryJComboBox", JComboBox.class).get();
		DefaultComboBoxModel<KeyValue> states = (DefaultComboBoxModel<KeyValue>) stateJComboBox.getModel();
		String countryCode = ((KeyValue) countryJComboBox.getSelectedItem()).getKey();
		System.out.printf("populating states for country code %s%n", countryCode);
		stateJComboBox.removeActionListener(mediator);
		states.removeAllElements();
		states.addAll(GeoDB.statesByCountryCode(countryCode));
		stateJComboBox.addActionListener(mediator);
		states.setSelectedItem(states.getElementAt(0));
	}

}
