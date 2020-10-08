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
package com.acme.proxy;

import java.awt.Graphics;

import javax.swing.Icon;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The Class IconTest creates an image icon -- an instance of
 * javax.swing.ImageIcon -- and then overrides the paint() method to paint the
 * icon.
 *
 * This is a poor use of Swing image icons because you should use image icons
 * only for small pictures. That restriction exists because creating images is
 * expensive, and ImageIcon instances create their images when they are
 * constructed.
 *
 * If an application creates many large images at once, it could cause a
 * significant performance hit. Also, if the application does not use all of its
 * images, it's wasteful to create them upfront.
 *
 * One solution is to use a proxy, like the {@link ImageIconProxy}
 */
public class IconTest extends JFrame {

	private static final long serialVersionUID = 9103215799698007449L;
	private static String IMAGE_NAME = "mandrill.jpg";
	private static int FRAME_X = 150, FRAME_Y = 200, FRAME_WIDTH = 268, FRAME_HEIGHT = 286;
	private Icon imageIcon = null;

	static public void main(String args[]) {
		new IconTest().setVisible(true);
	}

	/**
	 * Instantiates a new icon test.
	 */
	public IconTest() {
		super("Icon CommandServerTest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		// imageIcon = new ImageIcon(IMAGE_NAME);
		imageIcon = new ImageIconProxy(IMAGE_NAME, FRAME_WIDTH, FRAME_HEIGHT);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		imageIcon.paintIcon(this, g, getInsets().left, getInsets().top);
	}
}
