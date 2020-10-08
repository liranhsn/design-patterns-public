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

import static com.acme.mediator.util.Action.UNSUBSCRIBE;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import com.acme.mediator.impl.BaseMediator;
import com.acme.mediator.util.Action;

/**
 * The Class UnsubscribeActionEventHandler handles an event triggered by a user
 * unchecking the 'subscribe to our services' radio button. In such case, all
 * services are turned off.
 */
public class UnsubscribeActionEventHandler extends BaseComponentsTogglingHandler {

	/**
	 * Instantiates a new unsubscribe action event handler.
	 *
	 * @param mediator the mediator
	 */
	public UnsubscribeActionEventHandler(BaseMediator mediator) {
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
		return Action.valueOf(((ActionEvent) event).getActionCommand()).equals(UNSUBSCRIBE);
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
		SwingUtilities.invokeLater(() -> toggleServices(false));
	}

}
