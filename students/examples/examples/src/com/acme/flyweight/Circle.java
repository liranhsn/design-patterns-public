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
package com.acme.flyweight;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The class Circle is an object that can be a flyweight and only keep it's
 * color state, instead of also keeping it's radius and location. The method
 * {@link #draw(Graphics, Integer, Integer, Integer)} is used to supply the
 * Circle it's externalized state, while the method {@link #draw(Graphics)} uses
 * the internal (wasteful) state.
 */
public class Circle {

	private Color color;
	private Integer x, y, r;
	private boolean stateful;

	public Circle(Color color) {
		this.color = color;
	}

	public Circle(Color color, Integer x, Integer y, Integer r) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.r = r;
		this.stateful = true;
	}

	public boolean isStateful() {
		return stateful;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(x, y, r, r);
	}

	public void draw(Graphics g, Integer x, Integer y, Integer r) {
		g.setColor(color);
		g.drawOval(x, y, r, r);
	}
}
