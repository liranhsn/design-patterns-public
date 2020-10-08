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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The Class DynamicVirtualProxyTest shows how you can use the JDK's built-in
 * support for the Proxy pattern to implement the image-icon proxy in
 * VirtualProxyTest.
 *
 * This JFrame displays two icons. The left is a normal {@link ImageIcon} and
 * the right is a dynamically obtained proxy
 */
public class DynamicVirtualProxyTest extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1524931342742145573L;
	private static String IMAGE_NAME = "mandrill.jpg";
	private static int IMAGE_WIDTH = 298;
	private static int IMAGE_HEIGHT = 298;
	private static int SPACING = 5;
	private static int FRAME_X = 150;
	private static int FRAME_Y = 200;
	private static int FRAME_WIDTH = 622;
	private static int FRAME_HEIGHT = 340;
	private ImageIcon imageIcon = null;
	private Icon imageIconProxy = null;
	private Insets insets;
	private Graphics graphics;
	private boolean doubleClicked = false;

	static public void main(String args[]) {
		new DynamicVirtualProxyTest().setVisible(true);
	}

	/**
	 * Instantiates a new virtual proxy test rev.
	 */
	public DynamicVirtualProxyTest() {
		super("Virtual Proxy CommandServerTest");
		setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		imageIcon = new ImageIcon(IMAGE_NAME);
		imageIconProxy = (Icon) Proxy.newProxyInstance(ImageIcon.class.getClassLoader(), ImageIcon.class.getInterfaces(),
				new ImageIconInvocationHandler(IMAGE_NAME, IMAGE_WIDTH, IMAGE_HEIGHT));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			doubleClicked = !doubleClicked;
			repaint();
		}
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
}

/*
 * ImageIconInvocationHandler is a proxy for an icon. The proxy delays loading
 * the image until the first time the image is drawn. While the icon is loading
 * its image, the proxy draws a border and the message "Loading image..."
 */
class ImageIconInvocationHandler implements InvocationHandler {

	private static final String PAINT_ICON_METHOD = "paintIcon", GET_WIDTH_METHOD = "getIconWidth", GET_HEIGHT_METHOD = "getIconHeight";
	private ImageIcon realIcon = null;
	private boolean isIconCreated = false;
	private String imageName = null;
	private int width, height;

	public ImageIconInvocationHandler(String imageName, int width, int height) {
		this.width = width;
		this.height = height;
		this.imageName = imageName;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		if (PAINT_ICON_METHOD.equals(method.getName())) {
			final Component component = (Component) args[0];
			final Graphics graphics = (Graphics) args[1];
			final int x = ((Integer) args[2]).intValue(), y = ((Integer) args[3]).intValue();
			if (isIconCreated) {
				System.out.printf("ImageIconInvocationHandler.invoke() [%s]: method %s invoked later. icon loaded.%n",
						Thread.currentThread().getName(), PAINT_ICON_METHOD);
				realIcon.paintIcon(component, graphics, x, y);
				return null;
			}
			graphics.setColor(Color.WHITE);
			graphics.fillRect(x, y, width - 1, height - 1);
			graphics.setColor(Color.RED);
			graphics.drawString("Loading image...", x + 30, y + 30);
			/*
			 * The image is loaded via a Runnable passed to SwingUtilties.invokeLater(),
			 * which means that it is executed on another thread.
			 */
			SwingUtilities.invokeLater(() -> {
				if (!isIconCreated) {
					try {
						System.out.printf("ImageIconInvocationHandler.invoke() [%s]: method %s invoked later. loading icon...%n",
								Thread.currentThread().getName(), PAINT_ICON_METHOD);
						realIcon = new ImageIcon(imageName);
						Thread.sleep(1500); // Slow down the image-loading process.
						isIconCreated = realIcon != null;
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					component.repaint();
				}
			});
		} else if (GET_HEIGHT_METHOD.equals(method.getName())) {
			return isIconCreated ? height : realIcon.getIconHeight();
		} else if (GET_WIDTH_METHOD.equals(method.getName())) {
			return isIconCreated ? width : realIcon.getIconWidth();
		}
		return null;
	}
}
