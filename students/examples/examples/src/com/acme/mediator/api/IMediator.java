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

import java.util.Collection;
import java.util.Optional;

/**
 * The interface IMediator is a high level API that establishes the basic
 * colleagues management contract for all implementations
 *
 * @param <T> the super type of all mediator colleagues
 */
public interface IMediator<T> {

	/**
	 * adds a colleague to this mediator's registry
	 *
	 * @param colleague the colleague to add
	 * @param excludes array of classes to exclude
	 */
	void addColleague(T colleague, Class<?>... excludes);

	/**
	 * removes a colleague from this mediator's registry
	 *
	 * @param colleague the colleague to remove
	 */
	void removeColleague(T colleague);

	/**
	 * retrieve a optional of a colleague with a given name
	 *
	 * @param name the name of the colleague
	 * @return optional of a colleague with a given name
	 */
	Optional<T> getColleague(String name);

	/**
	 * retrieve a optional of a colleague with a given name from a given class. They
	 * object in the optional is the exact same type as the clazz parameter.
	 *
	 * @param <C> the type of colleague
	 * @param name the name of the colleague
	 * @param clazz the class of the colleague
	 * @return optional of a colleague with the given name from the given class.
	 */
	<C extends T> Optional<C> getColleague(String name, Class<C> clazz);

	/**
	 * retrieve a collection of colleagues matching the given type
	 *
	 * @param <C> the type of colleagues
	 * @param clazz the class of the colleagues
	 * @return a collection of colleagues matching the given type
	 */
	<C extends T> Collection<C> getColleagues(Class<C> clazz);

	/**
	 * retrieve a collection of all colleagues.
	 *
	 * @apiNote implementations can retrieve a copy of the registry
	 */
	Collection<T> getColleagues();

	/**
	 * clears the colleagues registry
	 */
	void removeColleagues();

}
