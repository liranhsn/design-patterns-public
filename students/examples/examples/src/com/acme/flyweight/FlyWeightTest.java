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

import static com.acme.flyweight.CircleFactory.NUM_OF_GC;
import static com.acme.flyweight.CircleFactory.NUM_OF_REQUESTS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;;

/**
 * You will see the printout from the command line, that you only have 11
 * objects created, not thousands of objects because you only have 11 colors.
 * Such a big reduction of object creation will improve your program performance
 * dramatically.
 *
 * Flyweight design is effective with instantiating a large amount of small and
 * fine-grained classes by combining with factory design pattern.
 *
 * It has similar structure as above example. When you create a string constant,
 * such constant is stored in a pool. When the second string is created, it will
 * be checked to see if it has been created. If it is true, the second string
 * instance will be picked up from the string pool instead of creating a new
 * one. This is why the following code makes sense, but bothers many people.
 *
 * <pre>
 * String s1 = &quot;hello&quot;;
 * String s2 = &quot;hello&quot;; // store in a string pool.
 * String s3 = new String(&quot;hello&quot;);
 * System.out.println(s1 == s2); // true, share the same memory address
 * System.out.println(s1 == s3); // false
 * </pre>
 */
public class FlyWeightTest extends JFrame {

	private static final long serialVersionUID = -4475171259447531685L;
	/**
	 *
	 */
	private static final boolean FLYWEIGHT = false;
	/**
	 *
	 */
	private static final int NUMBER_OF_CIRCLES = 50000;
	public static final int MAX_WIDTH = 500, MAX_HEIGHT = 300;

	public FlyWeightTest(String title) throws InterruptedException {
		super(title);
		Container contentPane = getContentPane();
		JButton button = new JButton("Draw Circle");
		final JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(button, BorderLayout.SOUTH);
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		setLocation(x, y);
		setVisible(true);
		button.addActionListener(event -> {
			System.gc();
			Graphics g = getGraphics();
			g.fillRect(0, 0, (int) panel.getSize().getWidth(), (int) panel.getSize().getHeight());
			for (int i = 0; i < NUMBER_OF_CIRCLES; ++i) {
				Circle circle = CircleFactory.getCircle(FLYWEIGHT);
				if (circle.isStateful())
					circle.draw(g);
				else
					circle.draw(g, CircleFactory.getRandomX(), CircleFactory.getRandomY(), CircleFactory.getRandomR());
			}
			SwingUtilities.invokeLater(() -> CircleFactory.reportMemStatus());
		});
		new Thread(() -> {
			while (true) {
				try {
					CircleFactory.REF_QUEUE.remove();
					NUM_OF_GC.incrementAndGet();
					if (NUM_OF_REQUESTS.get() % NUMBER_OF_CIRCLES == 0)
						System.out.printf("garbage collected %d/%d circles%n", NUM_OF_GC.get(), NUM_OF_REQUESTS.get());
				} catch (InterruptedException ignore) {
				}

			}
		}).start();
	}

	public static void main(String[] args) throws Exception {
		new FlyWeightTest("Circles");
	}

}
