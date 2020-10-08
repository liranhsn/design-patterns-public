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
package com.acme.state;

/**
 * The Enum FanState stores fan states.
 */
public enum FanState {
	/** The low speed state. */
	LOW_SPEED_STATE {

		@Override
		public AbstractFanState getFanState() {
			lastState = new LowSpeedFanState();
			return lastState;
		}

		@Override
		public String prettyPrint() {
			return "Fan operates at low speed";
		}

		@Override
		public long getFanSpeed() {
			return 4;
		}
	},
	/** The mid speed state. */
	MID_SPEED_STATE {

		@Override
		public AbstractFanState getFanState() {
			lastState = new MidSpeedFanState();
			return lastState;
		}

		@Override
		public String prettyPrint() {
			return "Fan operates at medium speed";
		}

		@Override
		public long getFanSpeed() {
			return 2;
		}
	},
	/** The hi speed state. */
	HI_SPEED_STATE {

		@Override
		public AbstractFanState getFanState() {
			lastState = new HiSpeedFanState();
			return lastState;
		}

		@Override
		public String prettyPrint() {
			return "Fan operates at high speed";
		}

		@Override
		public long getFanSpeed() {
			return 1;
		}
	},
	/** The off state. */
	OFF_STATE {

		@Override
		public AbstractFanState getFanState() {
			return new OffFanState();
		}

		@Override
		public String prettyPrint() {
			return "Fan is turned off";
		}

		@Override
		public long getFanSpeed() {
			return Long.MAX_VALUE;
		}
	};

	/**
	 * The last state represents the last state. This is used by the On button if a
	 * previous speed was selected
	 */
	private static AbstractFanState lastState = null;

	/**
	 * Gets the fan state.
	 *
	 * @return the fan state
	 */
	public abstract AbstractFanState getFanState();

	/**
	 * Pretty print.
	 *
	 * @return the string
	 */
	public abstract String prettyPrint();

	public abstract long getFanSpeed();
}
