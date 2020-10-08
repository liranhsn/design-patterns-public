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
 * The Class MidSpeedFanState represents a medium speed rotating fan state
 */
public class MidSpeedFanState extends LowSpeedFanState {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4189007572638553839L;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.state.LowSpeedFanState#getFanSpeed()
	 */
	@Override
	protected long getFanSpeed() {
		return FanState.MID_SPEED_STATE.getFanSpeed();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.state.AbstractFanState#getTitle()
	 */
	@Override
	protected String getTitle() {
		return FanState.MID_SPEED_STATE.prettyPrint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.state.AbstractFanState#getId()
	 */
	@Override
	protected String getId() {
		return FanState.MID_SPEED_STATE.toString();
	}
}
