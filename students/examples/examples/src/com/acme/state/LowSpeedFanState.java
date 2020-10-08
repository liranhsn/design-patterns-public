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

import javax.swing.SwingUtilities;

/**
 * The Class LowSpeedFanState represents a low speed rotating fan state
 */
public class LowSpeedFanState extends AbstractFanState {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1615614959302119974L;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.state.AbstractFanState#rotateFan()
	 */
	@Override
	protected void rotateFan() {
		try {
			while (!isStopped()) {
				angle1 = (angle1 + 1) % 360;
				angle2 = (angle2 + 1) % 360;
				angle3 = (angle3 + 1) % 360;
				angle4 = (angle4 + 1) % 360;
				SwingUtilities.invokeLater(() -> repaint());
				Thread.sleep(getFanSpeed());
			}
		} catch (InterruptedException ex) {
			System.out.println("Problem while putting thread to sleep.");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.state.AbstractFanState#getFanSpeed()
	 */
	@Override
	protected long getFanSpeed() {
		return FanState.LOW_SPEED_STATE.getFanSpeed();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.state.AbstractFanState#getTitle()
	 */
	@Override
	protected String getTitle() {
		return FanState.LOW_SPEED_STATE.prettyPrint();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.state.AbstractFanState#getId()
	 */
	@Override
	protected String getId() {
		return FanState.LOW_SPEED_STATE.toString();
	}
}
