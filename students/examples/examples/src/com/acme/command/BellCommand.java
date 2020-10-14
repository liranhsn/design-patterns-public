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

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The class BellCommand is a command to play a bell sound file
 */
public class BellCommand implements Command {

	private static final boolean START_CLIP = false;
	private String fileName;
	private String msg;

	public BellCommand(String fileName, String msg) {
		super();
		this.fileName = fileName;
		this.msg = msg;
	}

	private synchronized void playClip(String fileName) {
		try (AudioInputStream audioInputStream = AudioSystem
				.getAudioInputStream(new BufferedInputStream(new FileInputStream(fileName)));
				Clip clip = AudioSystem.getClip();) {
			clip.open(audioInputStream);
			if (START_CLIP)
				clip.start();
			this.wait(clip.getMicrosecondLength());
			// clip.drain();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute() {
		System.out.printf("%n%s dinging %s for %s...%n%n", Thread.currentThread().getName(), fileName, msg);
		new Thread(() -> playClip(fileName)).start();
	}

}
