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
package com.acme.mediator.util;

/**
 * The Enum StateRank holds constants to assist in ranking a state by, for
 * example, the number of cities in it.
 */
public enum StateRank {

	SMALL(1, 7), MEDIUM(8, 15), LARGE(16, Integer.MAX_VALUE);

	int minCities, maxCities;

	/**
	 * Instantiates a new state size.
	 *
	 * @param minCities the min cities
	 * @param maxCities the max cities
	 */
	StateRank(int minCities, int maxCities) {
		this.minCities = minCities;
		this.maxCities = maxCities;
	}

	/**
	 * Gets the state rank by the number of cities
	 *
	 * @param cities the cities number
	 * @return the appropriate state rank constant
	 */
	public static StateRank getStateRank(int cities) {
		if (cities >= SMALL.minCities && cities <= SMALL.maxCities)
			return SMALL;
		if (cities >= MEDIUM.minCities && cities <= MEDIUM.maxCities)
			return MEDIUM;
		return LARGE;
	}

}
