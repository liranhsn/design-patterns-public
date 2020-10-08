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
package com.acme.factory.laf;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The class LookAndFeel demonstrates swing's {@link UIManager} factory pattern
 * in creating families of swing components with a common look and feel
 */
public class LookAndFeelDemo implements ActionListener {

	/**
	 * com.sun.java.swing.plaf.motif.MotifLookAndFeel
	 * com.sun.java.swing.plaf.windows.WindowsLookAndFeel
	 * javax.swing.plaf.metal.MetalLookAndFeel
	 * com.sun.java.swing.plaf.gtk.GTKLookAndFeel
	 */
	// private static final String LAF_CLASS_NAME =
	// "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	private static final String LAF_CLASS_NAME = "javax.swing.plaf.metal.MetalLookAndFeel";
	private static String caption = "Number of button clicks: ";
	private int numClicks = 0;
	final JLabel label = new JLabel(caption + "0    ");

	public Component createComponents() {
		JButton button = new JButton("I'm a Swing button!");
		button.setMnemonic(KeyEvent.VK_I);
		button.addActionListener(this);
		label.setLabelFor(button);
		JPanel pane = new JPanel(new GridLayout(0, 1));
		pane.add(button);
		pane.add(label);
		pane.setBorder(BorderFactory.createEmptyBorder(60, // top
				60, // left
				20, // bottom
				60) // right
		);
		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		numClicks++;
		label.setText(caption + numClicks);
	}

	private static void initLookAndFeel() {
		System.out.printf("%s%n", UIManager.getLookAndFeel().getClass().getName());
		try {
			UIManager.setLookAndFeel(LAF_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			System.err.printf("Couldn't find class for specified look and feel: %s%n", LAF_CLASS_NAME);
			System.err.printf("Did you include the L&F library in the class path?%n");
			System.err.printf("Using the default look and feel.%n");
		} catch (UnsupportedLookAndFeelException e) {
			System.err.printf("Can't use the specified look and feel (%s) on this platform.%n", LAF_CLASS_NAME);
			System.err.printf("Using the default look and feel.%n");
		} catch (Exception e) {
			System.err.printf("Couldn't get specified look and feel (%s), for some reason.%n", LAF_CLASS_NAME);
			System.err.printf("Using the default look and feel.%n");
			e.printStackTrace();
		}
	}

	private static void createAndShowGUI() {
		initLookAndFeel(); // Set the look and feel.
		JFrame.setDefaultLookAndFeelDecorated(true); // Make sure we have nice window decorations.
		JFrame frame = new JFrame(UIManager.getLookAndFeel().getClass().getName()); // Create and set up the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LookAndFeelDemo app = new LookAndFeelDemo();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);
		frame.setSize(new Dimension(500, 250)); // Display the window.
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		/*
		 * Schedule a job for the event dispatch thread: creating and showing this
		 * application's GUI.
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
