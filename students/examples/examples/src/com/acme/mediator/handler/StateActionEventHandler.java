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

import static com.acme.mediator.util.Action.STATE;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import com.acme.mediator.impl.BaseMediator;
import com.acme.mediator.util.Action;
import com.acme.utils.GeoDB;
import com.acme.utils.GeoDB.KeyValue;

/**
 * The Class StateActionEventHandler handles an event that is triggered when the
 * states drop list changes. In such cases, the city drop list is populated.
 */
public class StateActionEventHandler extends BaseEventHandler {

	/**
	 * Instantiates a new state action event handler.
	 *
	 * @param mediator the mediator
	 */
	public StateActionEventHandler(BaseMediator mediator) {
		super(mediator, ActionEvent.class);
	}

	/**
	 * Can handle.
	 *
	 * @param <E> the element type
	 * @param event the event
	 * @return true, if successful
	 */
	@Override
	protected <E extends AWTEvent> boolean canHandle(E event) {
		return Action.valueOf(((ActionEvent) event).getActionCommand()).equals(STATE);
	}

	/**
	 * Handle event.
	 *
	 * @param <E> the element type
	 * @param e the e
	 */
	@Override
	public <E extends AWTEvent> void handleEvent(E e) {
		ActionEvent event = (ActionEvent) e;
		System.out.printf(
			"%s.handleEvent() -> handling %s with parameters %s%n",
			getClass().getSimpleName(),
			event.getClass().getSimpleName(),
			event.paramString());
		SwingUtilities.invokeLater(() -> populateCities());
	}

	/**
	 * populate the cities drop list. To populate the cities we need both the
	 * country and state code from the appropriate drop list colleagues.
	 */
	@SuppressWarnings("unchecked")
	private void populateCities() {
		JComboBox<KeyValue> countryJComboBox = mediator.getColleague("countryJComboBox", JComboBox.class).get();
		JComboBox<KeyValue> stateJComboBox = mediator.getColleague("stateJComboBox", JComboBox.class).get();
		JComboBox<String> cityJComboBox = mediator.getColleague("cityJComboBox", JComboBox.class).get();
		String countryCode = ((KeyValue) countryJComboBox.getSelectedItem()).getKey();
		String stateCode = ((KeyValue) stateJComboBox.getSelectedItem()).getKey();
		System.out.printf(
			"StateActionEventHandler.populateCities() -> populating cities for country and state codes '%s,%s'%n",
			countryCode,
			stateCode);
		DefaultComboBoxModel<String> cities = (DefaultComboBoxModel<String>) cityJComboBox.getModel();
		cityJComboBox.removeActionListener(mediator);
		cities.removeAllElements();
		cityJComboBox.addActionListener(mediator);
		List<String> values = GeoDB.citiesByCodes(countryCode, stateCode);

		if (null != values && !values.isEmpty()) {
			cityJComboBox.removeActionListener(mediator);
			cities.addAll(GeoDB.citiesByCodes(countryCode, stateCode));
			cityJComboBox.addActionListener(mediator);
			cities.setSelectedItem(cities.getElementAt(0));
		}
	}

}
