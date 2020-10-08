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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.acme.utils.SerializationUtils;

/**
 * The Class FanApp.
 */
public class FanApp extends JFrame {

	private static final long serialVersionUID = -631180143070870359L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final String SER_FILENAME = "fan.ser";
	private JPanel controlPnl = new JPanel();
	private JButton offBtn = new JButton("Off");
	private JButton lowSpeedBtn = new JButton("Low");
	private JButton midSpeedBtn = new JButton("Mid");
	private JButton hiSpeedBtn = new JButton("Hi");
	@SuppressWarnings("unused")
	private JButton warpSpeedBtn = new JButton("Max");
	private AbstractFanState state = new OffFanState();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new FanApp();
	}

	private void loadState() {
		File ser = new File(SER_FILENAME);
		AbstractFanState newState = new OffFanState();
		if (ser.exists()) {
			System.out.printf("loading fan state from %s%n", ser.getAbsolutePath());
			try {
				newState = SerializationUtils.deserialize("fan.ser");
			} catch (Exception ex) {
				System.err.printf("could not load state from %s! defaulting to off state.%n", ex.getMessage());
			}
		}
		final String id = newState.getId();
		// disable the button that corresponds to the loaded state
		Arrays.stream(controlPnl.getComponents()).filter(c -> c.getClass() == JButton.class && ((JButton) c).getActionCommand().equals(id))
				.forEach(b -> b.setEnabled(false));
		switchState(newState);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.Window#dispose()
	 */
	@Override
	public void dispose() {
		if (state != null) {
			try {
				SerializationUtils.serialize(state, SER_FILENAME);
				System.out.printf("serialized fan state to '%s' before disposing...%n", SER_FILENAME);
			} catch (IOException ioex) {
				System.err.printf("could not serialize state b/c %s%n", ioex.getMessage());
			}
			state.stop();
		}
		super.dispose();
	}

	/**
	 * Center.
	 */
	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		setLocation(x, y);
	}

	/**
	 * Switch state.
	 *
	 * @param newState the new state
	 */
	private void switchState(AbstractFanState newState) {
		Memento memento = state.getMemento();
		state.stop();
		remove(state);
		state = newState;
		add(newState, BorderLayout.CENTER);
		newState.setMemento(memento);
		newState.start();
		setTitle(newState.getTitle());
		paintComponents(getGraphics());
	}

	/**
	 * Inits the gui.
	 */
	private void initGUI() {
		StateButtonActionListener stateButtonActionListener = new StateButtonActionListener();
		controlPnl.setLayout(new GridLayout(1, 5));
		lowSpeedBtn.setActionCommand(FanState.LOW_SPEED_STATE.toString());
		lowSpeedBtn.addActionListener(stateButtonActionListener);
		controlPnl.add(lowSpeedBtn);
		midSpeedBtn.setActionCommand(FanState.MID_SPEED_STATE.toString());
		midSpeedBtn.addActionListener(stateButtonActionListener);
		controlPnl.add(midSpeedBtn);
		hiSpeedBtn.setActionCommand(FanState.HI_SPEED_STATE.toString());
		hiSpeedBtn.addActionListener(stateButtonActionListener);
		controlPnl.add(hiSpeedBtn);
		offBtn.setActionCommand(FanState.OFF_STATE.toString());
		offBtn.addActionListener(stateButtonActionListener);
		controlPnl.add(offBtn);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		add(controlPnl, BorderLayout.SOUTH);
		loadState();
		center();
		setVisible(true);
	}

	/**
	 * Instantiates a new fan app.
	 *
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public FanApp() {
		initGUI();
	}

	/**
	 * The listener interface for receiving stateButtonAction events. The class that
	 * is interested in processing a stateButtonAction event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addStateButtonActionListener<code>
	 * method. When the stateButtonAction event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see StateButtonActionEvent
	 */
	private class StateButtonActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 *
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// get the state from the states enum based on the source action command
			AbstractFanState newState = FanState.valueOf(event.getActionCommand()).getFanState();
			if (state.getClass() != newState.getClass()) {
				toggleButtons(event);
				setTitle(newState.getTitle());
				switchState(newState);
			}
		}

		/**
		 * Toggle buttons off and on. A pressed button will stay pressed until another
		 * button is pressed
		 *
		 * @param event the action event that encapsulates the source JButton
		 */
		private void toggleButtons(ActionEvent event) {
			JButton source = (JButton) event.getSource();
			source.setEnabled(false);
			Container container = source.getParent();
			for (Component comp : container.getComponents()) {
				if (comp.getClass() == JButton.class && comp != source) {
					((JButton) comp).setEnabled(true);
				}
			}
		}
	}
}