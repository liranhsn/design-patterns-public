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
package com.acme.observer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataListener;

/**
 * The class ObservableDataModel demonstrates how using a
 * {@link DefaultListModel} makes it report changes to the
 * {@link ListDataListener} and separates the concern of data changes and
 * displaying it.
 */
public class ObservableDataModel extends JFrame {

	private static final long serialVersionUID = -8828352120793743454L;
	/**
	 * This is our observed model
	 */
	private DefaultListModel<Integer> observableListModel = new DefaultListModel<>();
	private JList<Integer> listObserver = new JList<>(observableListModel);
	private JButton addBtn = new JButton("Add Random ");
	private JScrollPane scrollPane = new JScrollPane(listObserver);
	// define action listener
	private ActionListener actionListener = e -> {
		int rand = ThreadLocalRandom.current().nextInt(100) + 1;
		observableListModel.addElement(rand); // we only add data to the model. It is responsible to update it's container
		listObserver.ensureIndexIsVisible(listObserver.getModel().getSize() - 1);
		setTitle(String.format("Size: %d", listObserver.getModel().getSize()));
	};

	public ObservableDataModel() {
		initGUI();
	}

	private void initGUI() {
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		add(scrollPane, BorderLayout.NORTH);
		add(addBtn, BorderLayout.CENTER);
		// add action listener
		addBtn.addActionListener(actionListener);
		setTitle(String.format("Size: %d", 0));
		setSize(200, 200);
		// center the frame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
		// show it
		setVisible(true);
	}

	public static void main(String[] args) {
		new ObservableDataModel();
	}
}