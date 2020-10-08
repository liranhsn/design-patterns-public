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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * The Class TicTacToeFrame is an implementation of a traditional Tic Tac Toe
 * game where any trio of the same shape is considered a win.
 *
 * This class implements the {@link #doPlayerMoveTemplate(JButton)} and
 * {@link #checkGameStatusTemplate()} template methods
 *
 * This class implements the {@link #postPlayerMoveHook()},
 * {@link #postGameWonHook()} and {@link #postDeadLockHook()} hook methods in
 * it's own particular ways...
 *
 * @author tnsilver
 */
public class TicTacToeFrame extends AbstractTicTacToeFrame {

	private static final long serialVersionUID = -2816705194907761817L;

	public static void main(String[] args) {
		new TicTacToeFrame("Tic Tac Toe");
	}

	private final String player1 = "Player 1";
	private final String player2 = "Player 2";
	private final ImageIcon XIcon = new ImageIcon("X-icon.png");
	private final ImageIcon OIcon = new ImageIcon("O-icon.png");
	protected String winner = player1;

	public TicTacToeFrame(String title) {
		super(title);
	}

	/**
	 * Find out if any 3 buttons, out of the 8 winning position combinations, line
	 * up with the pre defined values in the winning combinations array. If this is
	 * the case, a call to {@link #markGameWon()} is made
	 */
	@Override
	protected void checkGameStatusTemplate() {
		for (int i = 0; i <= 7 && !isGameWon(); i++) {
			if (buttons[winCombos[i][0]].getActionCommand().equals(buttons[winCombos[i][1]].getActionCommand())
					&& buttons[winCombos[i][1]].getActionCommand().equals(buttons[winCombos[i][2]].getActionCommand())
					&& buttons[winCombos[i][0]].getActionCommand() != "") {
				markGameWon();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.template.AbstractTicTacToeFrame#doPlayerMoveTemplate(javax
	 * .swing.JButton)
	 */
	@Override
	protected void doPlayerMoveTemplate(JButton source) {
		numOfMoves++;
		source.setIcon(numOfMoves % 2 == 0 ? OIcon : XIcon);
		source.setActionCommand(numOfMoves % 2 == 0 ? "0" : "Y");
		source.setEnabled(false);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.template.AbstractTicTacToeFrame#postDeadLookHook()
	 */
	@Override
	protected void postDeadLockHook() {
		JOptionPane.showMessageDialog(null, "The game was tie!");
		reset();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.template.AbstractTicTacToeFrame#postGameWonHook()
	 */
	@Override
	protected void postGameWonHook() {
		playClip("churchbell.wav");
		JOptionPane.showMessageDialog(null, winner + " wins the game!");
		markDeadLock();
		reset();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.template.AbstractTicTacToeFrame#postPlayerMoveHook()
	 */
	@Override
	protected void postPlayerMoveHook() {
		if (isGameWon()) {
			winner = player1;
			if (numOfMoves % 2 == 0)
				winner = player2;
		} else if (numOfMoves == 9) {
			markDeadLock();
		}
	}
}
