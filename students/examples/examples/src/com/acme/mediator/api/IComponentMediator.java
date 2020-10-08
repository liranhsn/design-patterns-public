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
package com.acme.mediator.api;

import java.awt.AWTEvent;

import javax.swing.JComponent;

import com.acme.mediator.handler.BaseEventHandler;

/**
 * The Interface IComponentMediator is a lower level API for mediators that deal
 * with colleagues that are swing components and have to respond to AWTEvents
 */
public interface IComponentMediator extends IMediator<JComponent> {

	/**
	 * register an event handler on this mediator.
	 *
	 * @param handler the event handler
	 */
	void addEventHandler(BaseEventHandler handler);

	/**
	 * unregister an event handler from this mediator.
	 *
	 * @param handler the event handler
	 */
	void removeEventHandler(BaseEventHandler handler);

	/**
	 * handle an AWTEvent sub class by passing it to the registered handlers.
	 *
	 * @param <E> the type of event to handle
	 * @param event the event
	 */
	<E extends AWTEvent> void processEvent(E event);

}
