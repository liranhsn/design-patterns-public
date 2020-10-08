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
package com.acme.mediator.impl;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;

import com.acme.mediator.handler.CityAndSubscribeActionEventHandler;
import com.acme.mediator.handler.CountryActionEventHandler;
import com.acme.mediator.handler.FocusEventHandler;
import com.acme.mediator.handler.WindowOpenedEventHandler;
import com.acme.mediator.handler.SelectedActionEventHandler;
import com.acme.mediator.handler.StateActionEventHandler;
import com.acme.mediator.handler.SubmittedActionEventHandler;
import com.acme.mediator.handler.UnsubscribeActionEventHandler;

/**
 * The Class Mediator is the default concrete implementation of the BaseMediator
 * that is used in the application.
 */
public class Mediator extends BaseMediator {

	/**
	 * Instantiates a new mediator by registering all the event handlers that will
	 * able to respond to all events of interest in the application.
	 */
	public Mediator() {
		super();
		addEventHandler(new FocusEventHandler(this));
		addEventHandler(new WindowOpenedEventHandler(this));
		addEventHandler(new CountryActionEventHandler(this));
		addEventHandler(new StateActionEventHandler(this));
		addEventHandler(new CityAndSubscribeActionEventHandler(this));
		addEventHandler(new UnsubscribeActionEventHandler(this));
		addEventHandler(new SelectedActionEventHandler(this));
		addEventHandler(new SubmittedActionEventHandler(this));
	}

	/**
	 * a method to respond to focus gained events
	 *
	 * @param event the event
	 */
	@Override
	public void focusGained(FocusEvent event) {
		processEvent(event);
	}

	/**
	 * a method to respond to focus lost events. Used for validations of text
	 * fields.
	 *
	 * @param event the event
	 */
	@Override
	public void focusLost(FocusEvent event) {
		processEvent(event);
	}

	/**
	 * a method to respond to action events triggered by user clicks on buttons,
	 * lists, check boxes, radio buttons and other controls.
	 *
	 * @param event the event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		processEvent(event);
	}

	/**
	 * A method to respond to a window opened event and trigger the event chain that
	 * initializes the application.
	 */
	@Override
	public void windowOpened(WindowEvent event) {
		processEvent(event);
	}

}
