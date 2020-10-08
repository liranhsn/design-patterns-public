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
import static com.acme.mediator.util.Action.SELECTED;
import static com.acme.mediator.util.Action.STATE;
import static com.acme.mediator.util.Action.SUBMITTED;
import static com.acme.mediator.util.Action.SUBSCRIBE;
import static com.acme.mediator.util.Action.UNSUBSCRIBE;
import static com.acme.mediator.util.AppConst.DEFAULT_BG;
import static com.acme.mediator.util.AppConst.LABEL_SIZE;
import static com.acme.mediator.util.AppConst.MAX_HEIGHT;
import static com.acme.mediator.util.AppConst.MAX_WIDTH;
import static com.acme.mediator.util.AppConst.TEXT_SIZE;
import static com.acme.mediator.util.AppConst.TITLE;
import static com.acme.mediator.util.AppConst.TITLE_SIZE;
import static com.acme.mediator.util.MediatorUtil.NOTNULL_VALIDATOR;
import static com.acme.mediator.util.MediatorUtil.center;
import static com.acme.mediator.util.MediatorUtil.createLabel;
import static com.acme.mediator.util.MediatorUtil.validateJTextField;
import static com.acme.mediator.util.StateRank.LARGE;
import static com.acme.mediator.util.StateRank.MEDIUM;
import static com.acme.mediator.util.StateRank.SMALL;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.BorderLayout.WEST;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.acme.mediator.util.Action;
import com.acme.mediator.util.StateRank;
import com.acme.utils.GeoDB;
import com.acme.utils.GeoDB.KeyValue;

/**
 * The class NoMediatorDemo demonstrates tangled and scattered code when
 * multiple colleague components communicate with each other w/o using the
 * mediator pattern.
 */
public class NoMediatorDemo extends JFrame implements ActionListener, FocusListener {

	private final Map<String, Integer> cityServiceCache = new HashMap<>();
	private final Set<String> services = new TreeSet<>();
	/*
	 * components
	 */
	private final JPanel contentJPanel = new JPanel();
	private final JTextField nameJTextField = new JTextField("Mr. Silly");
	private final JComboBox<KeyValue> countryJComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
	private final JComboBox<KeyValue> stateJComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
	private final JComboBox<String> cityJComboBox = new JComboBox<>(new DefaultComboBoxModel<>());
	private final JRadioButton subscribeJRadioButton = new JRadioButton("Yes", true);
	private final JRadioButton unsubscribeJRadioButton = new JRadioButton("No", true);
	private final Box leftServiceBox = Box.createVerticalBox();
	private final Box rightServiceBox = Box.createVerticalBox();
	private final JPanel serviceJPanel = new JPanel(new BorderLayout());
	private final JButton submitJButton = new JButton("subscribe");

	public NoMediatorDemo(String title) throws Exception {
		super(title);
		setup();
		registerListeners();
		populateCountries();
		initGUI();
		center(this);
		setVisible(true);
	}

	public void setup() {
		/*
		 * general setup
		 */
		Font textFont = new Font("Monospaced", Font.BOLD, TEXT_SIZE);
		contentJPanel.setBorder(BorderFactory.createEtchedBorder());
		nameJTextField.setToolTipText("Please enter a valid name");
		nameJTextField.requestFocusInWindow();
		nameJTextField.setName("nameJTextField");
		leftServiceBox.setBorder(new EmptyBorder(0, 0, 0, 20)); // top,left,bottom,right
		rightServiceBox.setBorder(new EmptyBorder(0, 20, 0, 0)); // top,left,bottom,right
		serviceJPanel.setBorder(new EmptyBorder(5, 20, 5, 0)); // top,left,bottom,right
		subscribeJRadioButton.setFont(textFont);
		unsubscribeJRadioButton.setFont(textFont);
		countryJComboBox.setPreferredSize(new Dimension(200, 10));
		stateJComboBox.setPreferredSize(new Dimension(200, 10));
		cityJComboBox.setPreferredSize(new Dimension(200, 10));
		/*
		 * set action commands
		 */
		countryJComboBox.setActionCommand(COUNTRY.name());
		stateJComboBox.setActionCommand(STATE.name());
		cityJComboBox.setActionCommand(CITY.name());
		subscribeJRadioButton.setActionCommand(SUBSCRIBE.name());
		unsubscribeJRadioButton.setActionCommand(UNSUBSCRIBE.name());
		submitJButton.setActionCommand(SUBMITTED.name());
		/*
		 * frame setup
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(MAX_WIDTH, MAX_HEIGHT);
	}

	/**
	 * Add this as listener to all colleagues
	 */
	private void registerListeners() {
		countryJComboBox.addActionListener(this);
		stateJComboBox.addActionListener(this);
		cityJComboBox.addActionListener(this);
		submitJButton.addActionListener(this);
		subscribeJRadioButton.addActionListener(this);
		unsubscribeJRadioButton.addActionListener(this);
		nameJTextField.addFocusListener(this);
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
		wrapperPanel.add(inputPanel, NORTH);
		wrapperPanel.add(optionsPanel, CENTER);
		wrapperPanel.add(servicePanel, SOUTH);
		contentJPanel.add(wrapperPanel, NORTH);
		add(contentJPanel);
	}

	public void populateCountries() {
		DefaultComboBoxModel<KeyValue> countries = (DefaultComboBoxModel<KeyValue>) countryJComboBox.getModel();
		countryJComboBox.removeActionListener(this);
		countries.addAll(GeoDB.COUNTRIES);
		countryJComboBox.addActionListener(this);
		countries.setSelectedItem(countries.getElementAt(0));
	}

	public void populateStates() {
		DefaultComboBoxModel<KeyValue> states = (DefaultComboBoxModel<KeyValue>) stateJComboBox.getModel();
		String countryCode = ((KeyValue) countryJComboBox.getSelectedItem()).getKey();
		System.out.printf("populating states for country code %s%n", countryCode);
		stateJComboBox.removeActionListener(this);
		states.removeAllElements();
		states.addAll(GeoDB.statesByCountryCode(countryCode));
		stateJComboBox.addActionListener(this);
		states.setSelectedItem(states.getElementAt(0));
	}

	private void populateCities() {
		String countryCode = ((KeyValue) countryJComboBox.getSelectedItem()).getKey();
		String stateCode = ((KeyValue) stateJComboBox.getSelectedItem()).getKey();
		System.out.printf("populating cities for country and state codes '%s,%s'%n", countryCode, stateCode);
		DefaultComboBoxModel<String> cities = (DefaultComboBoxModel<String>) cityJComboBox.getModel();
		cityJComboBox.removeActionListener(this);
		cities.removeAllElements();
		cityJComboBox.addActionListener(this);
		List<String> values = GeoDB.citiesByCodes(countryCode, stateCode);

		if (null != values && !values.isEmpty()) {
			cityJComboBox.removeActionListener(this);
			cities.addAll(GeoDB.citiesByCodes(countryCode, stateCode));
			cityJComboBox.addActionListener(this);
			cities.setSelectedItem(cities.getElementAt(0));
		}
	}

	private void populateServices() {
		if (unsubscribeJRadioButton.isSelected())
			return;
		DefaultComboBoxModel<String> cities = (DefaultComboBoxModel<String>) cityJComboBox.getModel();
		StateRank stateRank = StateRank.getStateRank(cities.getSize());

		switch (stateRank) {
			case SMALL -> SwingUtilities.invokeLater(() -> createServices(SMALL));
			case MEDIUM -> SwingUtilities.invokeLater(() -> createServices(MEDIUM));
			case LARGE -> SwingUtilities.invokeLater(() -> createServices(LARGE));
		}
	}

	private void createServices(StateRank stateRank) {
		leftServiceBox.removeAll();
		rightServiceBox.removeAll();
		serviceJPanel.removeAll();
		services.clear();
		String city = (String) cityJComboBox.getSelectedItem();
		int services = cityServiceCache.containsKey(city) ? cityServiceCache.get(city) : switch (stateRank) {
			case SMALL -> ThreadLocalRandom.current().nextInt(2, 5);
			case MEDIUM -> ThreadLocalRandom.current().nextInt(4, 7);
			case LARGE -> ThreadLocalRandom.current().nextInt(6, 9);
		};

		if (!cityServiceCache.containsKey(city)) {
			cityServiceCache.put(city, services);
		}
		System.out.printf("populating %d services for %s%n", services, city);
		IntStream.rangeClosed(1, services).forEach(i -> {
			String name = "Service#" + i;
			JCheckBox checkBox = new JCheckBox(name);
			checkBox.setFont(new Font("Monospaced", Font.BOLD, TEXT_SIZE));
			checkBox.setName(name);
			checkBox.setActionCommand(SELECTED.name());
			checkBox.addActionListener(this);
			Box box = i <= 4 ? leftServiceBox : rightServiceBox;
			box.add(checkBox);
		});
		serviceJPanel.add(leftServiceBox, WEST);
		serviceJPanel.add(rightServiceBox, CENTER);
		JPanel submitPanel = new JPanel(new GridLayout(1, 1));
		submitPanel.setBorder(new EmptyBorder(10, 5, 10, 5));// top,left,bottom,right);
		submitPanel.add(submitJButton);
		serviceJPanel.add(submitPanel, SOUTH);
		serviceJPanel.updateUI();
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
		inputPanel.add(countryJComboBox);
		inputPanel.add(stateLabel);
		inputPanel.add(stateJComboBox);
		inputPanel.add(cityLabel);
		inputPanel.add(cityJComboBox);
		return inputPanel;
	}

	private JPanel buildOptionsPanel() {
		JPanel optionsPanel = new JPanel(new GridLayout(2, 1));
		JLabel subscribeLabel = new JLabel("Subscribe to our services:");
		subscribeLabel.setFont(new Font("Monospaced", Font.BOLD, LABEL_SIZE));
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
		populateServices();
		return serviceJPanel;
	}

	/*
	 * enable or disable all services
	 */
	private void toggleServices(boolean enabled) {
		if (!enabled)
			services.clear();
		toggleServices(leftServiceBox, enabled);
		toggleServices(rightServiceBox, enabled);
	}

	/**
	 * enable or disable check boxes inside a container box
	 *
	 * @param box the box container
	 * @param enabled true to enable, otherwise disable
	 */
	private void toggleServices(Box box, boolean enabled) {
		int numOfServices = box.getComponentCount();

		for (int i = 0; i < numOfServices; i++) {
			Component current = box.getComponent(i);
			current.setEnabled(enabled);
			if (!enabled && JCheckBox.class.isAssignableFrom(current.getClass()))
				((JCheckBox) current).setSelected(enabled);
		}
	}

	/**
	 * enable or disable components that are not in the excluded array
	 *
	 * @param enabled true to enable, otherwise disable
	 * @param excludes array of excluded componenet names
	 */
	private void toggleComponents(boolean enabled, String... excludes) {
		if (!isExcluded("nameJTextField", excludes))
			nameJTextField.setEnabled(enabled);
		if (!isExcluded("countryJComboBox", excludes))
			countryJComboBox.setEnabled(enabled);
		if (!isExcluded("stateJComboBox", excludes))
			stateJComboBox.setEnabled(enabled);
		if (!isExcluded("cityJComboBox", excludes))
			cityJComboBox.setEnabled(enabled);
		if (!isExcluded("subscribeJRadioButton", excludes))
			subscribeJRadioButton.setEnabled(enabled);
		if (!isExcluded("unsubscribeJRadioButton", excludes))
			unsubscribeJRadioButton.setEnabled(enabled);
		if (!isExcluded("submitJButton", excludes))
			submitJButton.setEnabled(enabled);
		toggleServices(enabled);
	}

	private boolean isExcluded(String name, String[] excludes) {
		return Arrays.stream(excludes).anyMatch(ex -> ex.equals(name));
	}

	private void handleServiceSelection(JComponent source) {

		if (JCheckBox.class.isAssignableFrom(source.getClass())) {
			if (((JCheckBox) source).isSelected())
				services.add(source.getName());
			else
				services.remove(source.getName());
		}
	}

	private void submit() {
		String name = nameJTextField.getText();
		String country = ((KeyValue) countryJComboBox.getSelectedItem()).getValue();
		String state = ((KeyValue) stateJComboBox.getSelectedItem()).getValue();
		String city = (String) cityJComboBox.getSelectedItem();
		String address = String.format("%s, %s - %s", city, state, country);
		String servicesStr = services.isEmpty() ? "no services" : services.stream().collect(Collectors.joining(", "));
		String message = String.format("%s from %s, subscribed to %s", name, address, servicesStr);
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * react to action events
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JComponent source = (JComponent) event.getSource();
		Action action = Action.valueOf(event.getActionCommand());
		System.out.printf("event %s from %s%n", action, source.getClass().getSimpleName());

		switch (action) {
			case COUNTRY -> SwingUtilities.invokeLater(() -> populateStates());
			case STATE -> SwingUtilities.invokeLater(() -> populateCities());
			case CITY -> SwingUtilities.invokeLater(() -> populateServices());
			case SUBSCRIBE -> SwingUtilities.invokeLater(() -> populateServices());
			case UNSUBSCRIBE -> SwingUtilities.invokeLater(() -> toggleServices(false));
			case SELECTED -> SwingUtilities.invokeLater(() -> handleServiceSelection(source));
			case SUBMITTED -> SwingUtilities.invokeLater(() -> submit());
			default -> SwingUtilities.invokeLater(() -> System.out.println("unkown option"));
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		toggleComponents(true, "nameJTextField");
		nameJTextField.setBackground(DEFAULT_BG);
	}

	@Override
	public void focusLost(FocusEvent event) {

		if (event.getSource().getClass().isAssignableFrom(JTextField.class)) {
			System.out.printf("%s.handleFocusLostEvent() -> invalid name for nameJTextField%n", getClass().getSimpleName());
			Consumer<Object[]> action = (excludes) -> toggleComponents(false, Arrays.stream(excludes).toArray(String[]::new));
			JTextField textField = (JTextField) event.getSource();
			validateJTextField(textField, NOTNULL_VALIDATOR, action, "name is required!", textField.getName());
		}
	}

	public static void main(String[] args) throws Exception {
		new NoMediatorDemo("No Mediator Demo");
	}

}
