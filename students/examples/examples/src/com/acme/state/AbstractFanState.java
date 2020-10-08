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

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

/**
 * The Class AbstractFanState is the top of the Fan States heirarchy.
 */
public abstract class AbstractFanState extends JPanel implements Serializable {

	private static final long serialVersionUID = -8937870085510066213L;
	protected int angle1 = -15;
	protected int angle2 = 75;
	protected int angle3 = 165;
	protected int angle4 = 255;
	private boolean stop;

	/**
	 * This method is the work method of the sub classes (Template Pattern)
	 */
	protected abstract void rotateFan();

	/**
	 * Gets the fan speed. It's the responsibility of the sub clases to return the
	 * speed of the fan This is the Template method
	 *
	 * @return the fan speed
	 */
	protected abstract long getFanSpeed();

	/**
	 * returns a title caption for this state
	 *
	 * @return title caption for this state
	 */
	protected abstract String getTitle();

	/**
	 * returns the unique id of this state
	 *
	 * @return the unique id of this state
	 */
	protected abstract String getId();

	/**
	 * Gets the memento.
	 *
	 * @return the memento
	 */
	protected Memento getMemento() {
		return new FanMementoImpl();
	}

	/**
	 * Sets the memento.
	 *
	 * @param memento the new memento
	 */
	protected void setMemento(Memento memento) {
		angle1 = ((FanMemento) memento).getAngle1();
		angle2 = ((FanMemento) memento).getAngle2();
		angle3 = ((FanMemento) memento).getAngle3();
		angle4 = ((FanMemento) memento).getAngle4();
	}

	/**
	 * Checks if is stopped.
	 *
	 * @return true, if is stopped
	 */
	protected boolean isStopped() {
		return stop;
	}

	/**
	 * A method to indicate to threads it's time to stop
	 */
	protected void stop() {
		stop = true;
	}

	/**
	 * Initialize this thread
	 */
	protected void start() {
		setVisible(true);
		stop = false;
		Thread spinner = new Thread(new Runnable() {

			@Override
			public void run() {
				if (!stop) {
					rotateFan();
				}
			}
		});
		// we want all threads to die when we close the frame window
		spinner.setDaemon(true);
		spinner.start();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		setForeground(Color.lightGray);
		setBackground(Color.black);
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawOval(0, 0, width, height);
		g.setColor(Color.lightGray);
		g.fillArc(0, 0, width, height, angle1, 30);
		g.setColor(Color.gray);
		g.fillArc(0, 0, width, height, angle2, 30);
		g.setColor(Color.lightGray);
		g.fillArc(0, 0, width, height, angle3, 30);
		g.setColor(Color.gray);
		g.fillArc(0, 0, width, height, angle4, 30);
	}

	/**
	 * The Class FanMementoImpl.
	 */
	protected class FanMementoImpl implements Memento, FanMemento, Serializable {

		private static final long serialVersionUID = -3216683876456715566L;
		int angle1, angle2, angle3, angle4;

		public FanMementoImpl() {
			this.angle1 = AbstractFanState.this.angle1;
			this.angle2 = AbstractFanState.this.angle2;
			this.angle3 = AbstractFanState.this.angle3;
			this.angle4 = AbstractFanState.this.angle4;
		}

		public FanMementoImpl(int angle1, int angle2, int angle3, int angle4) {
			this.angle1 = angle1;
			this.angle2 = angle2;
			this.angle3 = angle3;
			this.angle4 = angle4;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see com.acme.state.FanMemento#getAngle1()
		 */
		@Override
		public int getAngle1() {
			return this.angle1;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see com.acme.state.FanMemento#getAngle2()
		 */
		@Override
		public int getAngle2() {
			return this.angle2;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see com.acme.state.FanMemento#getAngle3()
		 */
		@Override
		public int getAngle3() {
			return this.angle3;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see com.acme.state.FanMemento#getAngle4()
		 */
		@Override
		public int getAngle4() {
			return this.angle4;
		}
	}
}
