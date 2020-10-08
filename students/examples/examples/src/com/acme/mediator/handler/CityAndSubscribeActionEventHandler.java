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

import static com.acme.mediator.util.Action.CITY;
import static com.acme.mediator.util.Action.SELECTED;
import static com.acme.mediator.util.Action.SUBSCRIBE;
import static com.acme.mediator.util.AppConst.TEXT_SIZE;
import static com.acme.mediator.util.StateRank.LARGE;
import static com.acme.mediator.util.StateRank.MEDIUM;
import static com.acme.mediator.util.StateRank.SMALL;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;

import java.awt.AWTEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.acme.mediator.impl.BaseMediator;
import com.acme.mediator.util.Action;
import com.acme.mediator.util.StateRank;

/**
 * The Class CityAndSubscribeActionEventHandler is handling an event that is
 * triggered when a city is selected from the list. In this case, the relevant
 * services must be rendered for the user to choose (unless the 'subscrive to
 * our services' radio button is un-ticked.
 */
public class CityAndSubscribeActionEventHandler extends BaseEventHandler {

	/**
	 * the cashing map holds the count of services created for each city, so that the same
	 * number of services can be generated for the same city - consistently
	 */
	private final Map<String, Integer> cityServiceCache = new HashMap<>();

	/**
	 * Instantiates a new handler.
	 *
	 * @param mediator the mediator
	 */
	public CityAndSubscribeActionEventHandler(BaseMediator mediator) {
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
		return Action.valueOf(((ActionEvent) event).getActionCommand()).equals(CITY)
				|| Action.valueOf(((ActionEvent) event).getActionCommand()).equals(SUBSCRIBE);
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
		SwingUtilities.invokeLater(() -> populateServices());
	}

	/**
	 * Populate services.
	 */
	@SuppressWarnings("unchecked")
	protected void populateServices() {
		JRadioButton unsubscribeJRadioButton = mediator.getColleague("unsubscribeJRadioButton", JRadioButton.class).get();
		if (unsubscribeJRadioButton.isSelected())
			return;
		JComboBox<String> cityJComboBox = mediator.getColleague("cityJComboBox", JComboBox.class).get();
		DefaultComboBoxModel<String> cities = (DefaultComboBoxModel<String>) cityJComboBox.getModel();
		StateRank stateRank = StateRank.getStateRank(cities.getSize());

		switch (stateRank) {
			case SMALL -> SwingUtilities.invokeLater(() -> createServices(SMALL));
			case MEDIUM -> SwingUtilities.invokeLater(() -> createServices(MEDIUM));
			case LARGE -> SwingUtilities.invokeLater(() -> createServices(LARGE));
		}
	}

	/**
	 * Creates the services by recreating the services panel colleague
	 *
	 * @param stateRank the state size
	 */
	@SuppressWarnings("unchecked")
	protected void createServices(StateRank stateRank) {
		JComboBox<String> cityJComboBox = mediator.getColleague("cityJComboBox", JComboBox.class).get();
		Box leftServiceBox = mediator.getColleague("leftServiceBox", Box.class).get();
		Box rightServiceBox = mediator.getColleague("rightServiceBox", Box.class).get();
		JPanel serviceJPanel = mediator.getColleague("serviceJPanel", JPanel.class).get();
		JButton submitJButton = mediator.getColleague("submitJButton", JButton.class).get();
		leftServiceBox.removeAll();
		rightServiceBox.removeAll();
		serviceJPanel.removeAll();
		mediator.clearServices();
		String city = (String) cityJComboBox.getSelectedItem();
		int services = cityServiceCache.containsKey(city) ? cityServiceCache.get(city) : switch (stateRank) {
			case SMALL -> ThreadLocalRandom.current().nextInt(2, 5);
			case MEDIUM -> ThreadLocalRandom.current().nextInt(4, 7);
			case LARGE -> ThreadLocalRandom.current().nextInt(6, 9);
		};
		if (!cityServiceCache.containsKey(city))
			cityServiceCache.put(city, services);
		System.out.printf("%screateServices() -> populating %d services for %s%n", getClass().getSimpleName(), services, city);
		IntStream.rangeClosed(1, services).forEach(i -> {
			String name = "Service#" + i;
			JCheckBox checkBox = new JCheckBox(name);
			checkBox.setFont(new Font("Monospaced", Font.BOLD, TEXT_SIZE));
			checkBox.setName(name);
			checkBox.setActionCommand(SELECTED.name());
			checkBox.addActionListener(mediator);
			Box box = i <= 4 ? leftServiceBox : rightServiceBox;
			box.add(checkBox);
		});
		serviceJPanel.add(leftServiceBox, WEST);
		serviceJPanel.add(rightServiceBox, CENTER);
		JPanel submitPanel = new JPanel(new GridLayout(1, 1));
		submitPanel.setBorder(new EmptyBorder(10, 5, 10, 5));// top,left,bottom,right);
		submitPanel.add(submitJButton);
		serviceJPanel.add(submitPanel, SOUTH);
		serviceJPanel.updateUI();
	}

}
