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

import static com.acme.mediator.util.Action.SUBMITTED;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.acme.mediator.impl.BaseMediator;
import com.acme.mediator.util.Action;
import com.acme.utils.GeoDB.KeyValue;

/**
 * The Class SubmittedActionEventHandler handles an event that is triggered when
 * the submit button is clicked. It displays a dialog box with the data
 * collected from the various input fields.
 */
public class SubmittedActionEventHandler extends BaseEventHandler {

	/**
	 * Instantiates a new submitted action event handler.
	 *
	 * @param mediator the mediator
	 */
	public SubmittedActionEventHandler(BaseMediator mediator) {
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
		return Action.valueOf(((ActionEvent) e).getActionCommand()).equals(SUBMITTED);
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
		SwingUtilities.invokeLater(() -> submit());
	}

	/**
	 * collects input data and siaplays a message in a dialog box
	 */
	@SuppressWarnings("unchecked")
	private void submit() {
		/*
		 * references to colleagues
		 */
		JComboBox<KeyValue> countryJComboBox = mediator.getColleague("countryJComboBox", JComboBox.class).get();
		JComboBox<KeyValue> stateJComboBox = mediator.getColleague("stateJComboBox", JComboBox.class).get();
		JComboBox<String> cityJComboBox = mediator.getColleague("cityJComboBox", JComboBox.class).get();
		JTextField nameJTextField = mediator.getColleague("nameJTextField", JTextField.class).get();
		JPanel contentJPanel = mediator.getColleague("contentJPanel", JPanel.class).get();
		/*
		 * format message string
		 */
		String name = nameJTextField.getText();
		String country = ((KeyValue) countryJComboBox.getSelectedItem()).getValue();
		String state = ((KeyValue) stateJComboBox.getSelectedItem()).getValue();
		String city = (String) cityJComboBox.getSelectedItem();
		String address = String.format("%s, %s - %s", city, state, country);
		String servicesStr = mediator.isEmptyServices() ? "no services" : mediator.servicesCSVString();
		String message = String.format("%s from %s, subscribed to %s", name, address, servicesStr);
		JOptionPane.showMessageDialog(contentJPanel, message);
	}

}
