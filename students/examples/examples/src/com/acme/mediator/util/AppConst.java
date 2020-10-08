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
package com.acme.mediator.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

/**
 * The Interface DemoConstants holds constants for the application
 */
public interface AppConst {

	/**
	 * Strings
	 */
	String TITLE = "Sign On";
	/*
	 * size
	 */
	int MAX_WIDTH = 450;
	int MAX_HEIGHT = 550;
	int TITLE_SIZE = 32;
	int LABEL_SIZE = 18;
	int TEXT_SIZE = 14;
	/*
	 * fonts
	 */
	Font TITLE_FONT = new Font("Monospaced", Font.BOLD, TITLE_SIZE);
	Font LABEL_FONT = new Font("Monospaced", Font.BOLD, LABEL_SIZE);
	Font TEXT_FONT = new Font("Monospaced", Font.BOLD, TEXT_SIZE);
	/*
	 * Colors
	 */
	Color INVALID_BG = new Color(255, 204, 153);
	Color DEFAULT_BG = UIManager.getColor("TextField.background");

}
