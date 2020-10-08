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
 * The Interface VictoryChecker defines methods for checking victory in a tic
 * tac tow game.
 */
public interface VictoryChecker {

	/**
	 *
	 * @param board the 9 button array representing the game board
	 * @return GameResult containing the result of the game
	 */
	GameResult getGameResult(JButton[] board);

	int[][] WIN_MATRIX = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // indices 0 - 2: horizontal wins
			{ 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // indices 3 - 5: vertical wins
			{ 0, 4, 8 }, { 2, 4, 6 } // indices 6 - 7: diagonal wins
	};

}
