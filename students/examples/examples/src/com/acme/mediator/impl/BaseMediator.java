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

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComponent;

import com.acme.mediator.api.IComponentMediator;
import com.acme.mediator.handler.BaseEventHandler;
import com.acme.mediator.util.MediatorUtil;

/**
 * The class BaseMediator is a default implementation of the IComponentMediator
 * interface to serve as base implementation of all mediator objects that
 * mediate component colleagues and deal with AWT events.
 *
 * @apiNote This class extends {@link WindowAdapter} so concrete mediators can
 * implement just part of the methods from {@link WindowListener}. without
 * forcing them to implement all the window event handling methods.
 */
@SuppressWarnings("unchecked")
public abstract class BaseMediator extends WindowAdapter implements IComponentMediator, ActionListener, FocusListener {

	protected final Set<BaseEventHandler> handlers;
	protected final Set<JComponent> colleagues;
	protected final Set<String> services = new TreeSet<>();

	/**
	 * Instantiates a new base mediator.
	 */
	public BaseMediator() {
		super();
		colleagues = new HashSet<>();
		handlers = new HashSet<>();
	}

	/**
	 * register an event handler on this mediator.
	 *
	 * @param handler the event handler
	 */
	@Override
	public void addEventHandler(BaseEventHandler handler) {
		handlers.add(handler);
	}

	/**
	 * unregister an event handler from this mediator.
	 *
	 * @param handler the event handler
	 */
	@Override
	public void removeEventHandler(BaseEventHandler handler) {
		handlers.remove(handler);
	}

	/**
	 * handle an AWTEvent sub class by passing it to the registered handlers.
	 *
	 * @param <E> the type of event to handle
	 * @param event the event
	 */
	@Override
	public <E extends AWTEvent> void processEvent(E event) {
		handlers.stream().filter(h -> h.accept(event)).forEach(h -> h.handleEvent(event));
	}

	/**
	 * add a colleague, unless the given excludes contains the class of the
	 * colleague
	 *
	 * @param colleague the colleague to add
	 * @param excludes array of classes to exclude from adding
	 */
	@Override
	public void addColleague(JComponent colleague, Class<?>... excludes) {
		if (!MediatorUtil.hasName(colleague) && !Arrays.stream(excludes).anyMatch(ex -> colleague.getClass().isAssignableFrom(ex)))
			throw new UnsupportedOperationException(String.format("colleague type %s has no name!", colleague.getClass().getSimpleName()));
		colleagues.add(colleague);
	}

	/**
	 * Removes the colleague.
	 *
	 * @param colleague the colleague
	 */
	@Override
	public void removeColleague(JComponent colleague) {
		colleagues.remove(colleague);
	}

	/**
	 * Retrieve the a copy of the colleagues collection containing all colleagues of
	 * the given class
	 *
	 * @param <C> the generic type of the colleagues class
	 * @param clazz the class of the colleagues to retrieve
	 * @return a copy of the colleagues collection containing all colleagues of the
	 * given 'clazz' class
	 */
	@Override
	public <C extends JComponent> Set<C> getColleagues(Class<C> clazz) {
		return colleagues.stream().filter(c -> clazz.isAssignableFrom(c.getClass())).map(c -> (C) c).collect(toSet());
	}

	/**
	 * Retrieves an optional containing the colleague as identified by the given
	 * name. The content of the optional is of the same type as the specified class.
	 *
	 * @param <C> the generic type of the colleague to retrieve
	 * @param name the name of the colleague
	 * @param clazz the class of the colleague
	 * @return an optional containing the colleague as identified by the given name.
	 */
	@Override
	public <C extends JComponent> Optional<C> getColleague(String name, Class<C> clazz) {
		Optional<? extends JComponent> result = colleagues.stream().filter(c -> c.getClass().isAssignableFrom(clazz))
				.filter(c -> c.getName().equals(name)).findAny();
		return result.isEmpty() ? Optional.empty() : Optional.of((C) result.get());
	}

	@Override
	public Set<JComponent> getColleagues() {
		return Set.copyOf(colleagues);
	}

	/**
	 * Gets the colleague.
	 *
	 * @param name the name
	 * @return the colleague
	 */
	@Override
	public Optional<JComponent> getColleague(String name) {
		return colleagues.stream().filter(c -> c.getName().equals(name)).findAny();
	}

	/**
	 * Removes the colleagues.
	 */
	@Override
	public void removeColleagues() {
		colleagues.clear();
	}

	/**
	 * Clear services.
	 */
	public void clearServices() {
		services.clear();
	}

	/**
	 * Adds the service.
	 *
	 * @param service the service
	 */
	public void addService(String service) {
		services.add(service);
	}

	/**
	 * Removes the service.
	 *
	 * @param service the service
	 */
	public void removeService(String service) {
		services.remove(service);
	}

	/**
	 * Services CSV string.
	 *
	 * @return the string
	 */
	public String servicesCSVString() {
		return services.stream().collect(joining(", "));
	}

	public boolean isEmptyServices() {
		return services.isEmpty();
	}

}
