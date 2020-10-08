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

import static com.acme.mediator.util.AppConst.DEFAULT_BG;
import static com.acme.mediator.util.MediatorUtil.NOTNULL_VALIDATOR;
import static com.acme.mediator.util.MediatorUtil.validateJTextField;

import java.awt.AWTEvent;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.swing.JTextField;

import com.acme.mediator.impl.BaseMediator;

/**
 * The Class FocusEventHandler handles FocusEvent to validate the name field
 * input and make sure it is not null.
 */
public class FocusEventHandler extends BaseComponentsTogglingHandler {

	/**
	 * Instantiates a new focus event handler.
	 *
	 * @param mediator the mediator
	 */
	public FocusEventHandler(BaseMediator mediator) {
		super(mediator, FocusEvent.class);
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
		JTextField nameJTextField = mediator.getColleague("nameJTextField", JTextField.class).get();
		return event.getSource().equals(nameJTextField);
	}

	/**
	 * Handle event.
	 *
	 * @param <E> the element type
	 * @param event the event
	 */
	@Override
	public <E extends AWTEvent> void handleEvent(E event) {
		if (((FocusEvent) event).getID() == FocusEvent.FOCUS_LOST)
			handleFocusLostEvent((FocusEvent) event);
		else
			handleFocusGainedEvent((FocusEvent) event);
	}

	/**
	 * Handle focus lost event.
	 *
	 * @param event the event
	 */
	private void handleFocusLostEvent(FocusEvent event) {

		if (event.getSource().getClass().isAssignableFrom(JTextField.class)) {
			System.out.printf("%s.handleFocusLostEvent() -> invalid name for nameJTextField%n", getClass().getSimpleName());
			Consumer<Object[]> action = (excludes) -> toggleComponents(false, Arrays.stream(excludes).toArray(String[]::new));
			JTextField textField = (JTextField) event.getSource();
			validateJTextField(textField, NOTNULL_VALIDATOR, action, "name is required!", textField.getName());
		}
	}

	/**
	 * Handle focus gained event.
	 *
	 * @param event the event
	 */
	private void handleFocusGainedEvent(FocusEvent event) {
		JTextField nameJTextField = mediator.getColleague("nameJTextField", JTextField.class).get();
		nameJTextField.setBackground(DEFAULT_BG);
		toggleComponents(true, "nameJTextField");
	}

}
