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
import java.awt.Component;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.JCheckBox;

import com.acme.mediator.impl.BaseMediator;

/**
 * The class BaseServiceTogglingHandler is an abstract handler that contains
 * code for toggling services on and off. Sub classes can use the protected
 * methods to perform actions that orhter wise would have required code
 * duplication.
 */
public abstract class BaseComponentsTogglingHandler extends BaseEventHandler {

	public BaseComponentsTogglingHandler(BaseMediator mediator, Class<? extends AWTEvent> clazz) {
		super(mediator, clazz);
	}

	/**
	 * iterate the entire mediator colleagues collection and enable/disable all but
	 * the excluded.
	 *
	 * @param enabled if true, enables the component, otherwise disables
	 * @param excludes an array of colleague names to be excluded (left alone)
	 */
	protected void toggleComponents(boolean enabled, String... excludes) {
		mediator.getColleagues().stream().filter(c -> Arrays.stream(excludes).noneMatch(ex -> ex.equals(c.getName())))
				.forEach(c -> c.setEnabled(enabled));
		toggleServices(enabled);
	}

	/**
	 * enable or disable all services
	 *
	 * @param enabled if true, turns on (enables) all services, otherwise false,
	 */
	protected void toggleServices(boolean enabled) {
		if (!enabled)
			mediator.clearServices();
		Box leftServiceBox = mediator.getColleague("leftServiceBox", Box.class).get();
		Box rightServiceBox = mediator.getColleague("rightServiceBox", Box.class).get();
		toggleServices(leftServiceBox, enabled);
		toggleServices(rightServiceBox, enabled);
	}

	/**
	 * enable or disable check boxes inside a container box
	 *
	 * @param box the box container
	 * @param enabled true to enable, otherwise disable
	 */
	private void toggleServices(Box box, boolean enabled) {
		int numOfServices = box.getComponentCount();

		for (int i = 0; i < numOfServices; i++) {
			Component current = box.getComponent(i);
			current.setEnabled(enabled);
			if (!enabled && JCheckBox.class.isAssignableFrom(current.getClass()))
				((JCheckBox) current).setSelected(enabled);
		}
	}

}
