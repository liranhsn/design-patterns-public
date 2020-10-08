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
package com.acme.mediator;

import static com.acme.mediator.util.Action.CITY;
import static com.acme.mediator.util.Action.COUNTRY;
import static com.acme.mediator.util.Action.STATE;
import static com.acme.mediator.util.Action.SUBMITTED;
import static com.acme.mediator.util.Action.SUBSCRIBE;
import static com.acme.mediator.util.Action.UNSUBSCRIBE;
import static com.acme.mediator.util.AppConst.LABEL_FONT;
import static com.acme.mediator.util.AppConst.LABEL_SIZE;
import static com.acme.mediator.util.AppConst.MAX_HEIGHT;
import static com.acme.mediator.util.AppConst.MAX_WIDTH;
import static com.acme.mediator.util.AppConst.TEXT_FONT;
import static com.acme.mediator.util.AppConst.TITLE;
import static com.acme.mediator.util.AppConst.TITLE_SIZE;
import static com.acme.mediator.util.MediatorUtil.center;
import static com.acme.mediator.util.MediatorUtil.createLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.acme.mediator.impl.Mediator;
import com.acme.mediator.util.MediatorUtil;
import com.acme.utils.GeoDB.KeyValue;

/**
 * The class MediatorDemo demonstrates how multiple colleague components
 * communicate through a mediator by applying the mediator pattern
 */
public class MediatorDemo extends JFrame {

	/*
	 * colleague components
	 */
	private final JPanel contentJPanel = new JPanel();
	private final JTextField nameJTextField = new JTextField("Mr. Silly");
	private final JComboBox<KeyValue> countyJComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
	private final JComboBox<KeyValue> stateJComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
	private final JComboBox<String> cityJComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
	private final JRadioButton subscribeJRadioButton = new JRadioButton("Yes", true);
	private final JRadioButton unsubscribeJRadioButton = new JRadioButton("No", true);
	private final Box leftServiceBox = Box.createVerticalBox();
	private final Box rightServiceBox = Box.createVerticalBox();
	private final JPanel serviceJPanel = new JPanel(new BorderLayout());
	private final JButton submitJButton = new JButton("subscribe");
	/*
	 * mediator
	 */
	private final Mediator mediator = new Mediator();

	public MediatorDemo(String title) throws Exception {
		super(title);
		setup();
		initGUI();
		center(this);
		setVisible(true);
	}

	public void setup() {
		/*
		 * general setup
		 */
		contentJPanel.setBorder(BorderFactory.createEtchedBorder());
		nameJTextField.setToolTipText("Please enter a valid name");
		// nameJTextField.requestFocusInWindow();
		nameJTextField.setName("nameJTextField");
		leftServiceBox.setBorder(new EmptyBorder(0, 0, 0, 20)); // top,left,bottom,right
		rightServiceBox.setBorder(new EmptyBorder(0, 20, 0, 0)); // top,left,bottom,right
		serviceJPanel.setBorder(new EmptyBorder(5, 20, 5, 0)); // top,left,bottom,right
		subscribeJRadioButton.setFont(TEXT_FONT);
		unsubscribeJRadioButton.setFont(TEXT_FONT);
		countyJComboBox.setPreferredSize(new Dimension(200, 10));
		stateJComboBox.setPreferredSize(new Dimension(200, 10));
		cityJComboBox.setPreferredSize(new Dimension(200, 10));
		/*
		 * set action commands
		 */
		countyJComboBox.setActionCommand(COUNTRY.name());
		stateJComboBox.setActionCommand(STATE.name());
		cityJComboBox.setActionCommand(CITY.name());
		subscribeJRadioButton.setActionCommand(SUBSCRIBE.name());
		unsubscribeJRadioButton.setActionCommand(UNSUBSCRIBE.name());
		submitJButton.setActionCommand(SUBMITTED.name());
		/*
		 * assign names
		 */
		contentJPanel.setName("contentJPanel");
		serviceJPanel.setName("serviceJPanel");
		leftServiceBox.setName("leftServiceBox");
		rightServiceBox.setName("rightServiceBox");
		submitJButton.setName("submitJButton");
		/*
		 * frame setup
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(MAX_WIDTH, MAX_HEIGHT);
	}

	/**
	 * Add mediator as listener to all colleagues and register colleagues with
	 * mediator
	 */
	private void registerListeners() {
		countyJComboBox.addActionListener(mediator);
		stateJComboBox.addActionListener(mediator);
		cityJComboBox.addActionListener(mediator);
		submitJButton.addActionListener(mediator);
		subscribeJRadioButton.addActionListener(mediator);
		unsubscribeJRadioButton.addActionListener(mediator);
		nameJTextField.addFocusListener(mediator);
		this.addWindowListener(mediator);
		MediatorUtil.assignColleagueNames(contentJPanel);
		MediatorUtil.getColleaguesStream(contentJPanel).forEach(c -> mediator.addColleague(c, JPanel.class));
		/*
		 * mediator.getColleagues().forEach(c ->
		 * out.printf("MediatorDemo.registerListeners() -> colleague %s '%s'%n",
		 * c.getClass().getSimpleName(), c.getName()));
		 */
	}

	/*
	 * arranges panels in content panel
	 */
	public void initGUI() throws Exception {
		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.setBorder(new EmptyBorder(0, 5, 0, 5));// top,left,bottom,right
		JPanel inputPanel = buildInputPanel();
		JPanel optionsPanel = buildOptionsPanel();
		JPanel servicePanel = buildServicePanel();
		wrapperPanel.add(inputPanel, BorderLayout.NORTH);
		wrapperPanel.add(optionsPanel, BorderLayout.CENTER);
		wrapperPanel.add(servicePanel, BorderLayout.SOUTH);
		contentJPanel.add(wrapperPanel, BorderLayout.NORTH);
		add(contentJPanel);
		registerListeners();
	}

	private JPanel buildInputPanel() {
		JLabel titleLabel = createLabel(TITLE, TITLE_SIZE);
		JLabel nameLabel = createLabel("Name:", LABEL_SIZE);
		nameJTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameJTextField.getPreferredSize().height));
		JPanel inputPanel = new JPanel(new GridLayout(5, 2));
		JLabel countriesLabel = createLabel("Country:", LABEL_SIZE);
		JLabel stateLabel = createLabel("State:", LABEL_SIZE);
		JLabel cityLabel = createLabel("City:", LABEL_SIZE);
		inputPanel.add(titleLabel);
		inputPanel.add(new JLabel());
		inputPanel.add(nameLabel);
		inputPanel.add(nameJTextField);
		inputPanel.add(countriesLabel);
		inputPanel.add(countyJComboBox);
		inputPanel.add(stateLabel);
		inputPanel.add(stateJComboBox);
		inputPanel.add(cityLabel);
		inputPanel.add(cityJComboBox);
		return inputPanel;
	}

	private JPanel buildOptionsPanel() {
		JPanel optionsPanel = new JPanel(new GridLayout(2, 1));
		JLabel subscribeLabel = new JLabel("Subscribe to our services:");
		subscribeLabel.setFont(LABEL_FONT);
		optionsPanel.add(subscribeLabel);
		Box optionsBox = Box.createVerticalBox();
		ButtonGroup subscribeButtonGroup = new ButtonGroup();
		subscribeButtonGroup.add(subscribeJRadioButton);
		subscribeButtonGroup.add(unsubscribeJRadioButton);
		optionsBox.add(subscribeJRadioButton);
		optionsBox.add(unsubscribeJRadioButton);
		optionsPanel.add(optionsBox);
		return optionsPanel;
	}

	private JPanel buildServicePanel() {
		serviceJPanel.add(leftServiceBox, BorderLayout.WEST);
		serviceJPanel.add(rightServiceBox, BorderLayout.CENTER);
		JPanel submitPanel = new JPanel(new GridLayout(1, 1));
		submitPanel.setBorder(new EmptyBorder(10, 5, 10, 5));// top,left,bottom,right);
		submitPanel.add(submitJButton);
		serviceJPanel.add(submitPanel, BorderLayout.SOUTH);
		return serviceJPanel;
	}

	public static void main(String[] args) throws Exception {
		new MediatorDemo("Mediator Demo");
	}

}
