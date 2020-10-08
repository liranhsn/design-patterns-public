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
package com.acme.strategy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * The class SortingStrategySwingDemo is a swing application demonstrates how a
 * {@link SortedListModel} keeps sorting order by the comparator it receives in
 * it's constructor
 */
public class SortingStrategySwingDemo extends JFrame {

	private static final long serialVersionUID = -8828352120793743454L;

	/*
	 * a bi-function to produce a configurable name comparator with two arguments
	 */
	private final BiFunction<Boolean, Boolean, Comparator<Employee>> nameCompFunc = (sortByFname, asc) -> (e1, e2) -> {
		if (sortByFname) {
			if (asc)
				return (e1.getFirstName().compareTo(e2.getFirstName()));
			return (e2.getFirstName().compareTo(e1.getFirstName()));
		} else {
			if (asc)
				return (e1.getLastName().compareTo(e2.getLastName()));
			return (e2.getLastName().compareTo(e1.getLastName()));
		}
	};
	/*
	 * a bi-function to produce a configurable salary comparator with one arguments
	 */
	private final Function<Boolean, Comparator<Employee>> salaryCompFunc = asc -> (e1, e2) -> {
		if (asc)
			return (e1.getSalary().compareTo(e2.getSalary()));
		return (e2.getSalary().compareTo(e1.getSalary()));
	};
	/*
	 * other class members
	 */
	private JPanel controlPnl = new JPanel();
	private JCheckBox sortAscCB = new JCheckBox("sort ASC", true);
	private JCheckBox sortByNickNameCB = new JCheckBox("sort by nick name", true);
	private JButton toggleBtn = new JButton("toggle strategy");
	private JButton sortBtn = new JButton("sort");
	private boolean isNameStrategy = true;
	private String title;
	private Comparator<Employee> comparator = nameCompFunc.apply(sortByNickNameCB.isSelected(), sortAscCB.isSelected());
	private SortedListModel<Employee> listModel = new SortedListModel<Employee>(comparator);
	private JList<Employee> list = new JList<>(listModel);
	private JScrollPane scrollPane = new JScrollPane(list);

	public void initGUI() {
		setTitle();
		list.setFont(new Font("Monospaced", Font.BOLD, 12));
		listModel.addAll(getModelData());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		controlPnl.add(sortByNickNameCB);
		controlPnl.add(sortBtn);
		controlPnl.add(sortAscCB);
		controlPnl.add(toggleBtn);
		add(controlPnl, BorderLayout.SOUTH);
		setSize(500, 300);
		center();
		setVisible(true);
	}

	private void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		setLocation(x, y);
	}

	private void setTitle() {
		if (isNameStrategy)
			title = String.format("sorting by %s name (%s)", (sortByNickNameCB.isSelected() ? "nick" : "first"),
					(sortAscCB.isSelected() ? "ASC" : "DESC"));
		else
			title = String.format("sorting by salary (%s)", (sortAscCB.isSelected() ? "ASC" : "DESC"));
		super.setTitle(title);
	}

	public SortingStrategySwingDemo() {
		initGUI();
		toggleBtn.addActionListener(event -> {
			toggleComparator();
			sortBtn.doClick();
			setTitle();
		});
		sortBtn.addActionListener(event -> {
			listModel.setComparator(comparator);
			repaint();
			list.ensureIndexIsVisible(list.getModel().getSize() - 1);
		});
		sortAscCB.addItemListener(event -> {
			if (isNameStrategy)
				comparator = nameCompFunc.apply(sortByNickNameCB.isSelected(), sortAscCB.isSelected());
			else
				comparator = salaryCompFunc.apply(sortAscCB.isSelected());
			setTitle();
		});
		sortByNickNameCB.addItemListener(event -> {
			comparator = nameCompFunc.apply(sortByNickNameCB.isSelected(), sortAscCB.isSelected());
			setTitle();
		});
	}

	private void toggleComparator() {
		if (isNameStrategy) {
			comparator = salaryCompFunc.apply(sortAscCB.isSelected());
			sortByNickNameCB.setEnabled(false);
			isNameStrategy = false;
		} else {
			comparator = nameCompFunc.apply(sortByNickNameCB.isSelected(), sortAscCB.isSelected());
			sortByNickNameCB.setEnabled(true);
			isNameStrategy = true;
		}
	}

	private Employee[] getModelData() {
		return List.of(new Employee("Bigboss", "Robert", 30000D), new Employee("Newbe", "Joe", 5000D), new Employee("Veteran", "Jack", 17000D),
				new Employee("Freelance", "Bill", 15000D), new Employee("Sloppy", "Lisa", 6000D), new Employee("Clumzy", "George", 6500D),
				new Employee("Sneaky", "Larry", 7500D), new Employee("Lazy", "Ray", 7500D), new Employee("Greasy", "Tony", 8500D),
				new Employee("Lucky", "Luke", 11500D)).stream().toArray(Employee[]::new);
	}

	public static void main(String[] args) {
		new SortingStrategySwingDemo();
	}
}