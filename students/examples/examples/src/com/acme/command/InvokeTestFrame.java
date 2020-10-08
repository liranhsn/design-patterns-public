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
package com.acme.command;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class InvokeTestFrame extends JFrame {

	private static final long serialVersionUID = 2109602048454401833L;
	private JTextField numTextField = new JTextField(1);
	private JTextField threadTextField = new JTextField(1);
	private JButton goButton = new JButton("Go ");

	public InvokeTestFrame() {
		setLayout(new GridLayout(1, 4));
		add(goButton);
		add(threadTextField);
		add(numTextField);
		setSize(new Dimension(250, 50));
		// Before Java 8
		/**
		 * <pre>
		 * goButton.addActionListener(new ActionListener() {
		 *
		 * 	&#064;Override
		 * 	public void actionPerformed(ActionEvent ev) {
		 * 		new CounterThread().start();
		 * 	}
		 * });
		 * </pre>
		 **/
		// Now with Java 8:
		goButton.addActionListener(e -> new CounterThread().start());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	class CounterThread extends Thread {

		@Override
		public void run() {
			for (int i = 0; i < 20; i++) {
				final int num = i;
				// Before JDK 8
				/**
				 * <pre>
				 *
				 * SwingUtilities.invokeLater(new Runnable() {
				 *
				 * 	&#064;Override
				 * 	public void run() {
				 * 		numTextField.setText(&quot;Num: &quot; + num);
				 * 		threadTextField.setText(&quot;Thread: &quot; + getId());
				 * 	}
				 * });
				 * </pre>
				 */
				// Now with JDK 8
				SwingUtilities.invokeLater(() -> {
					numTextField.setText("Iteration: " + num);
					threadTextField.setText("Thread: " + getId());
				});
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		new InvokeTestFrame();
	}
}