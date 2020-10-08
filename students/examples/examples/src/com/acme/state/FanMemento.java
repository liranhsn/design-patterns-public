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

import java.io.Serializable;

/**
 * The Interface FanMemento is only used by a fan state object to restore the
 * angles of the fan upon the first paint() method call. This is done to avoid
 * jittering of the fan as a result of the angles reset
 */
public interface FanMemento extends Serializable {

	/**
	 * Gets the angle1.
	 *
	 * @return the angle1
	 */
	public int getAngle1();

	/**
	 * Gets the angle2.
	 *
	 * @return the angle2
	 */
	public int getAngle2();

	/**
	 * Gets the angle3.
	 *
	 * @return the angle3
	 */
	public int getAngle3();

	/**
	 * Gets the angle4.
	 *
	 * @return the angle4
	 */
	public int getAngle4();
}
