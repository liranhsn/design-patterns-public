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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 * The Class ImageIconProxy is a proxy (or surrogate) for an icon.
 *
 * The proxy delays loading the image until the first time the image is drawn.
 * While the icon is loading its image, the proxy draws a border and the message
 * "Loading image..."
 */
public class ImageIconProxy implements javax.swing.Icon {

	private Icon realIcon = null;
	volatile boolean isIconCreated = false;
	private String imageName;
	private int width, height;

	public ImageIconProxy(String imageName, int width, int height) {
		this.imageName = imageName;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getIconHeight() {
		return isIconCreated ? height : realIcon.getIconHeight();
	}

	@Override
	public int getIconWidth() {
		return isIconCreated ? width : realIcon.getIconWidth();
	}

	/**
	 * The proxy's paint() method is overloaded to draw a border and a message
	 * ("Loading image...") while the image loads.
	 *
	 * After the image has loaded, it is drawn. Notice that the proxy does not load
	 * the image until it is actually needed.
	 */
	@Override
	public void paintIcon(final Component component, Graphics graphics, int x, int y) {
		if (isIconCreated) {
			System.out.printf("ImageIconProxy.paintIcon(): image already loaded!%n");
			realIcon.paintIcon(component, graphics, x, y);
			return;
		}
		graphics.setColor(Color.WHITE);
		graphics.fillRect(x, y, width - 1, height - 1);
		graphics.setColor(Color.RED);
		graphics.drawString("loading image...", x + 30, y + 30);
		// The icon is created (meaning the image is loaded) on another thread.
		synchronized (this) {
			SwingUtilities.invokeLater(() -> {
				// this is the body of the runnable:
				if (!isIconCreated) {
					try {
						Thread.sleep(1500); // Slow down the image-loading process for the demo
					} catch (InterruptedException ignore) {
					}
					System.out.printf("ImageIconProxy.paintIcon(): Thread: (%s) => loading image...%n", Thread.currentThread().getName());
					realIcon = new ImageIcon(imageName); // ImageIcon constructor creates the image.
					isIconCreated = realIcon != null;
					System.out.printf("ImageIconProxy.paintIcon():: Thread: (%s) => image loaded!%n", Thread.currentThread().getName());
				}
			});
		}
		component.repaint(); // repaint the icon's component after the icon has been created.
	}
}