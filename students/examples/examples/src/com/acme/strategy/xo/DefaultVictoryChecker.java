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

import javax.swing.JButton;

/**
 * A default implementation of a victory checker. Checks rows, columns and
 * diagonals.
 */
public class DefaultVictoryChecker implements VictoryChecker {

	/**
	 * Checks if the game was won.
	 *
	 * @param board the board 9 buttons array.
	 * @return true, if is game won
	 */
	@Override
	public GameResult getGameResult(JButton[] board) {
		boolean gameWon = false;
		int winTrioIndex = -1;
		for (int i = 0; i <= 7 && !gameWon; i++)
			if (board[WIN_MATRIX[i][0]].getActionCommand().equals(board[WIN_MATRIX[i][1]].getActionCommand()) &&
			    board[WIN_MATRIX[i][1]].getActionCommand().equals(board[WIN_MATRIX[i][2]].getActionCommand()) &&
				board[WIN_MATRIX[i][0]].getActionCommand() != "") {
				winTrioIndex = i;
				gameWon = true;
			}
		return new GameResult(gameWon, winTrioIndex);
	}

}
