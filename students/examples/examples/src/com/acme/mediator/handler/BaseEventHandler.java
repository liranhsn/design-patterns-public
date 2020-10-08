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

import com.acme.mediator.impl.BaseMediator;

/**
 * The Class BaseEventHandler is the super class for all event handlers and
 * templates out the {@link #accept(AWTEvent)} method, which determines whether
 * or not a handler can handle a specific event.
 *
 * The {@link #canHandle(AWTEvent)} method is a hook which lets sub classes
 * enhance the event acceptance algorithm
 *
 * The {@link #handleEvent(AWTEvent)} method is the workhorse handling method to
 * be implemented by all concrete sub classes
 */
public abstract class BaseEventHandler {

	protected BaseMediator mediator;
	private Class<? extends AWTEvent> clazz;

	/**
	 * Instantiates a new handler.
	 *
	 * @param mediator the mediator
	 * @param clazz the clazz
	 */
	public BaseEventHandler(BaseMediator mediator, Class<? extends AWTEvent> clazz) {
		super();
		this.mediator = mediator;
		this.clazz = clazz;
	}

	/**
	 * determines if the event should be handled by comparing the event class to the
	 * member variable clazz.
	 *
	 * @param <E> the type of event
	 * @param event the event
	 * @return true if accepted and the event can be handled, otherwise false
	 */
	public <E extends AWTEvent> boolean accept(E event) {
		return clazz == event.getClass() && canHandle(event);
	}

	/**
	 * hook method to determines if the event can be handled. This is intended to be
	 * implemented in sub classes that wish to add conditions about the even before
	 * handling it
	 *
	 * @param <E> the type of event
	 * @param event the event
	 * @return true if accepted and the even can be handled, otherwise false
	 */
	protected <E extends AWTEvent> boolean canHandle(E event) {
		return true;
	}

	/**
	 * handles an event, possibly be delegating work to the mediator.
	 *
	 * @param <E> the type of event
	 * @param event the event
	 */
	public abstract <E extends AWTEvent> void handleEvent(E event);

}
