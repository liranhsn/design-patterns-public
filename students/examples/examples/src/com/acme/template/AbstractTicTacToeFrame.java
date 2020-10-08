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
package com.acme.template;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * The Class AbstractTicTacToeFrame sets the GUI for a Tic Tac Toe game and
 * provides, via the Template design patterns the basic algorithm for managing
 * the game by sub classes.
 */
public abstract class AbstractTicTacToeFrame extends JFrame {

	private static final long serialVersionUID = 1387338572093476874L;
	private volatile Clip clip;
	protected int numOfMoves = 0;
	private boolean deadLock;
	private boolean gameWon;
	protected final int[][] winCombos = new int[][] {
		    { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // indices 0 - 2: horizontal wins
			{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // indices 3 - 5: vertical wins
			{ 0, 4, 8 }, { 2, 4, 6 } // indices 6 - 7: diagonal wins
	};
	protected final JButton buttons[] = new JButton[9];

	/*
	 * The action listener defines the algorithm
	 */
	private ActionListener actionListener = event -> {
		JButton source = (JButton) event.getSource();
		doPlayerMoveTemplate(source); // template
		checkGameStatusTemplate(); // template
		postPlayerMoveHook(); // hook
		if (gameWon)
			postGameWonHook(); // hook
		if (deadLock)
			postDeadLockHook(); // hook
	};

	public AbstractTicTacToeFrame(String title) {
		super(title);
		initGUI();
	}

	/* center the GUI */
	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		setLocation(x, y);
	}

	/**
	 * A template method to check game status. It's the roll of this method to
	 * determine if the game is won or reached a deadlock and to call the
	 * {@link #markGameWon()} or {@link #markDeadLock()} methods to notify this
	 * frame about the new status
	 */
	protected abstract void checkGameStatusTemplate();

	/**
	 * Perform the GUI operations to reflect the player's move on the board
	 *
	 * @param source the button that reflect the player's choice
	 */
	protected abstract void doPlayerMoveTemplate(JButton source);

	/* initialize the GUI */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(370, 370);
		setLayout(new GridLayout(3, 3));
		for (int i = 0; i <= 8; i++) {
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.white);
			add(buttons[i]);
			buttons[i].addActionListener(actionListener);
		}
		setResizable(false);
		center();
		setVisible(true);
	}

	protected boolean isGameWon() {
		return gameWon;
	}

	protected final void markDeadLock() {
		deadLock = true;
	}

	protected final void markGameWon() {
		gameWon = true;
	}

	/**
	 * An alternative method for implementation by sub classes that wish to perform
	 * any action after a dead lock situation was detected by this frame (by call of
	 * a sub class to the {@link #markDeadLock()} method
	 */
	protected void postDeadLockHook() {
	}

	/**
	 * An alternative method for implementation by sub classes that wish to perform
	 * any action after a winning situation was detected by this frame (by call of a
	 * sub class to the {@link #markGameWon()} method
	 */
	protected void postGameWonHook() {
	}

	/**
	 * An alternative method for implementation by sub classes that wish to perform
	 * any action after a player had done a move by call to
	 * {@link #doPlayerMoveTemplate(JButton)}
	 */
	protected void postPlayerMoveHook() {
	}

	/**
	 * Resets the game board and counters in preparation for a new game
	 */
	protected final void reset() {
		numOfMoves = 0;
		deadLock = false;
		gameWon = false;
		for (int i = 0; i < buttons.length; i++) {
			remove(buttons[i]);
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.white);
			add(buttons[i]);
			buttons[i].addActionListener(actionListener);
		}
		if (clip != null && clip.isRunning()) {
			try {
				clip.stop();
				clip.close();
			} catch (Exception ignore) {
			}
		}
		paintAll(getGraphics());
	}

	protected void playClip(String fileName) {
		new Thread(() -> {
			if (clip == null || !clip.isRunning()) {
				try (AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new BufferedInputStream(new FileInputStream(fileName)));) {
					clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
					clip.drain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}