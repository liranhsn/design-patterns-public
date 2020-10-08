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

import static com.acme.mediator.util.Action.SELECTED;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.acme.mediator.impl.BaseMediator;
import com.acme.mediator.util.Action;

/**
 * The Class SelectedActionEventHandler handles an event that is triggered by
 * the selection or toggling of a service checkbox.
 */
public class SelectedActionEventHandler extends BaseEventHandler {

	/**
	 * Instantiates a new selected action event handler.
	 *
	 * @param mediator the mediator
	 */
	public SelectedActionEventHandler(BaseMediator mediator) {
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
		return Action.valueOf(((ActionEvent) e).getActionCommand()).equals(SELECTED);
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
		SwingUtilities.invokeLater(() -> handleServiceSelection((JComponent) event.getSource()));
	}

	/**
	 * handle a service selection by adding or removing it from the mediator services collection
	 *
	 * @param source the source
	 */
	private void handleServiceSelection(JComponent source) {

		if (JCheckBox.class.isAssignableFrom(source.getClass())) {
			if (((JCheckBox) source).isSelected())
				mediator.addService(source.getName());
			else
				mediator.removeService(source.getName());
		}
	}

}
