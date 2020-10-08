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
package com.acme.strategy.xo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The Class TicTacToeFrame sets the GUI for a Tic Tac Toe game and provides,
 * via the Template design patterns the basic algorithm for managing the game by
 * sub classes.
 */
public class TicTacToeFrame extends JFrame implements ActionListener {

	private final ImageIcon XIcon = new ImageIcon("X-icon.png");
	private final ImageIcon OIcon = new ImageIcon("O-icon.png");
	private final JButton buttons[] = new JButton[9];
	private int numOfMoves = 0;
	private boolean deadLock;
	private GameResult result;
	private final String player1 = "Player 1";
	private final String player2 = "Player 2";
	private String winner = player1;
	private final VictoryChecker strategy;

	public TicTacToeFrame(String title, VictoryChecker strategy) {
		super(title);
		this.strategy = strategy;
		initGUI();
		center();
		setVisible(true);
	}

	/* center the GUI */
	void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		setLocation(x, y);
	}

	/**
	 * Perform the GUI operations to reflect the player's move on the board
	 *
	 * @param source the button that reflect the player's choice
	 */
	void doPlayerMove(JButton source) {
		numOfMoves++;
		source.setIcon(numOfMoves % 2 == 0 ? OIcon : XIcon);
		source.setActionCommand(numOfMoves % 2 == 0 ? "0" : "Y");
		source.setEnabled(false);
	}

	/* initialize the GUI */
	void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(370, 370);
		setLayout(new GridLayout(3, 3));

		for (int i = 0; i <= 8; i++) {
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.white);
			add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		setResizable(false);
	}

	/**
	 * An alternative method for implementation by sub classes that wish to perform
	 * any action after a dead lock situation was detected by this frame (by call of
	 * a sub class to the {@link #markDeadLock()} method
	 */
	void postDeadLock() {
		for (int i = 0; i < buttons.length; i++)
			buttons[i].setBackground(Color.lightGray);
		JOptionPane.showMessageDialog(null, "The game was tie!");
		reset();
	}

	/**
	 * An alternative method for implementation by sub classes that wish to perform
	 * any action after a winning situation was detected by this frame (by call of a
	 * sub class to the {@link #markGameWon()} method
	 */
	void postGameWon() {
		Color color = numOfMoves % 2 == 0 ? Color.blue : Color.black;
		switch (result.getWinTrioIndex()) {
			case 0 -> IntStream.iterate(0, i -> i < 3, i -> i + 1).forEach(i -> buttons[i].setBackground(color));
			case 1 -> IntStream.iterate(3, i -> i < 6, i -> i + 1).forEach(i -> buttons[i].setBackground(color));
			case 2 -> IntStream.iterate(6, i -> i < 9, i -> i + 1).forEach(i -> buttons[i].setBackground(color));
			case 3 -> IntStream.iterate(0, i -> i < 7, i -> i + 3).forEach(i -> buttons[i].setBackground(color));
			case 4 -> IntStream.iterate(1, i -> i < 8, i -> i + 3).forEach(i -> buttons[i].setBackground(color));
			case 5 -> IntStream.iterate(2, i -> i < 9, i -> i + 3).forEach(i -> buttons[i].setBackground(color));
			case 6 -> IntStream.iterate(0, i -> i < 9, i -> i + 4).forEach(i -> buttons[i].setBackground(color));
			case 7 -> IntStream.iterate(2, i -> i < 7, i -> i + 2).forEach(i -> buttons[i].setBackground(color));
		}
		JOptionPane.showMessageDialog(null, winner + " wins the game!");
		deadLock = true;
		reset();
	}

	/**
	 * Resets the game board and counters in preparation for a new game
	 */
	final void reset() {
		numOfMoves = 0;
		deadLock = false;
		result = null;

		for (int i = 0; i < buttons.length; i++) {
			remove(buttons[i]);
			buttons[i] = new JButton();
			buttons[i].setBackground(Color.white);
			add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		paintAll(getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton source = (JButton) event.getSource();
		doPlayerMove(source);
		result = strategy.getGameResult(buttons);
		if (result.isGameWon())
			winner = numOfMoves % 2 == 0 ? player2 : player1;
		else if (numOfMoves == 9)
			deadLock = true;
		if (result.isGameWon())
			postGameWon();
		if (deadLock)
			postDeadLock();
	}

	public static void main(String[] args) {
		VictoryChecker strategy = new DefaultVictoryChecker();
		//VictoryChecker strategy = new NoDiagonalsVictoryChecker();
		new TicTacToeFrame("Tic Tac Toe", strategy);
	}

}
