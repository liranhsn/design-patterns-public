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

import java.awt.AWTEvent;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

import com.acme.mediator.impl.BaseMediator;
import com.acme.utils.GeoDB;
import com.acme.utils.GeoDB.KeyValue;

/**
 * The Class WindowOpenedEventHandler is handling a window event that is triggered
 * when the application window (JFrame) is opened. It then populates the country drop
 * list.
 */
public class WindowOpenedEventHandler extends BaseEventHandler {


	/**
	 * Instantiates a new window opened event handler.
	 *
	 * @param mediator the mediator
	 */
	public WindowOpenedEventHandler(BaseMediator mediator) {
		super(mediator, WindowEvent.class);
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
		SwingUtilities.invokeLater(() -> populateCountries());
	}

	/**
	 * populates the country drop list when the window is initlized.
	 */
	@SuppressWarnings("unchecked")
	public void populateCountries() {
		JComboBox<KeyValue> countyJComboBox = mediator.getColleague("countryJComboBox", JComboBox.class).get();
		DefaultComboBoxModel<KeyValue> countries = (DefaultComboBoxModel<KeyValue>) countyJComboBox.getModel();
		countyJComboBox.removeActionListener(mediator);
		countries.addAll(GeoDB.COUNTRIES);
		countyJComboBox.addActionListener(mediator);
		countries.setSelectedItem(countries.getElementAt(0));
	}

}
