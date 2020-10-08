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
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The Class VirtualProxyTest is a solution to the previous IconTest class which
 * demonstrates a poor use of Swing image icons because you should use image
 * icons only for small pictures.
 *
 * That restriction exists because creating images is expensive, and ImageIcon
 * instances create their images when they are constructed. If an application
 * creates many large images at once, it could cause a significant performance
 * hit. Also, if the application does not use all of its images, it's wasteful
 * to create them upfront.
 *
 * The Class VirtualProxyTest is a better solution that loads images as they
 * become needed.
 *
 * To do so, a proxy can create the real icon the first time the proxy's
 * paintIcon() method is called. Because image icons load their images when they
 * are constructed, an icon's image displays as soon as the application's window
 * opens.
 *
 * In contrast, the proxy does not load its image until it is painted for the
 * first time. Until the image loads, the proxy draws a border around its
 * perimeter and displays "Loading image..."
 */
public class VirtualProxyTest extends JFrame implements MouseListener{

	private static final long serialVersionUID = 130535715909222868L;
	private static String IMAGE_NAME = "mandrill.jpg";
	private static int IMAGE_WIDTH = 298;
	private static int IMAGE_HEIGHT = 298;
	private static int SPACING = 5;
	private static int FRAME_X = 150;
	private static int FRAME_Y = 200;
	private static int FRAME_WIDTH = 622;
	private static int FRAME_HEIGHT = 340;
	private Icon imageIcon = null,
			imageIconProxy = null;
	private Insets insets;
	private Graphics graphics;
	private boolean doubleClicked = false;

	static public void main(String args[]) {
		new VirtualProxyTest().setVisible(true);
	}

	/**
	 * Instantiates a new virtual proxy test.
	 */
	public VirtualProxyTest() {
		super("Virtual Proxy CommandServerTest");
		/*
		 * Create an image icon and an image-icon proxy.
		 */
		addMouseListener(this);
		imageIcon = new ImageIcon(IMAGE_NAME);
		imageIconProxy = new ImageIconProxy(IMAGE_NAME, IMAGE_WIDTH, IMAGE_HEIGHT);
		setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		insets = getInsets();
		graphics = g;
		imageIcon.paintIcon(this, graphics, insets.left, insets.top);
		if (doubleClicked) {
			imageIconProxy.paintIcon(this, graphics, insets.left + IMAGE_WIDTH + SPACING, insets.top);
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			doubleClicked = !doubleClicked;
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
