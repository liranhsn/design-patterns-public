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

/**
 * The Class GameResult is a wrapper around a boolean flag indicating if a game
 * was won and the winning trio index in the winning combination matrix
 */
public class GameResult {

	boolean gameWon;
	int winTrioIndex;

	/**
	 * Instantiates a new game result.
	 *
	 * @param gameWon the game won
	 * @param winTrioIndex the win trio index
	 */
	public GameResult(boolean gameWon, int winTrioIndex) {
		super();
		this.gameWon = gameWon;
		this.winTrioIndex = winTrioIndex;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public int getWinTrioIndex() {
		return winTrioIndex;
	}

}
