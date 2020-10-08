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
import java.util.stream.IntStream;

/**
 * The Class NoDiagonalTicTacToeFrame is an implementation of a Tic Tac Toe game
 * where a vertical or horizontal trio of of the same shape is considered a win.
 *
 * This class overloads the {@link #postGameWonHook()} and
 * {@link #postDeadLockHook()} hook methods in it's own particular ways, but
 * inherits the {@link #postPlayerMoveHook()} method.
 *
 */
public class NoDiagonalTicTacToeFrame extends TicTacToeFrame {

	private static final long serialVersionUID = 6786107898882718734L;

	private int winTrioIndex;

	public static void main(String[] args) {
		new NoDiagonalTicTacToeFrame("No Diagonals Tic Tac Toe");
	}

	public NoDiagonalTicTacToeFrame(String title) {
		super(title);
	}

	/**
	 * Find out if any 3 buttons, out of the 5 winning position combinations, line
	 * up with the pre defined values in the winning combinations array. In this
	 * sub-class, no diagonals are considered a winning combinations. If this is the
	 * case, a call to {@link #markGameWon()} is made
	 */
	@Override
	protected void checkGameStatusTemplate() {
		for (int i = 0; i <= 5 && !isGameWon(); i++) {
			if (buttons[winCombos[i][0]].getActionCommand().equals(buttons[winCombos[i][1]].getActionCommand()) &&
				buttons[winCombos[i][1]].getActionCommand().equals(buttons[winCombos[i][2]].getActionCommand()) &&
				buttons[winCombos[i][0]].getActionCommand() != "") {
				winTrioIndex = i;
				markGameWon();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.template.TicTacToeFrame#postDeadLockHook()
	 */
	@Override
	protected void postDeadLockHook() {
		for (int i = 0; i < buttons.length; i++)
			buttons[i].setBackground(Color.lightGray);
		super.postDeadLockHook();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acme.template.TicTacToeFrame#postGameWonHook()
	 */
	@Override
	protected void postGameWonHook() {
		Color color = numOfMoves % 2 == 0 ? Color.blue : Color.black;
		switch (winTrioIndex) {
			case 0 -> IntStream.iterate(0, i -> i < 3, i -> i + 1).forEach(i -> buttons[i].setBackground(color));
			case 1 -> IntStream.iterate(3, i -> i < 6, i -> i + 1).forEach(i -> buttons[i].setBackground(color));
			case 2 -> IntStream.iterate(6, i -> i < 9, i -> i + 1).forEach(i -> buttons[i].setBackground(color));
			case 3 -> IntStream.iterate(0, i -> i < 7, i -> i + 3).forEach(i -> buttons[i].setBackground(color));
			case 4 -> IntStream.iterate(1, i -> i < 8, i -> i + 3).forEach(i -> buttons[i].setBackground(color));
			case 5 -> IntStream.iterate(2, i -> i < 9, i -> i + 3).forEach(i -> buttons[i].setBackground(color));
		}
		super.postGameWonHook();
	}
}
