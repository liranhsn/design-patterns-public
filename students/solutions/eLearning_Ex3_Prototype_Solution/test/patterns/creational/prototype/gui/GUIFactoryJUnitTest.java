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
package patterns.creational.prototype.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import patterns.creational.prototype.Button;
import patterns.creational.prototype.ComboBox;
import patterns.creational.prototype.gui.ComboBoxImpl.IndexedValue;

/**
 * The Class GUIFactoryJUnitTest is a sanity JUnit test case to test the
 * functionality of the {@link GUIFactory} class
 */
public class GUIFactoryJUnitTest {

    private ButtonImpl button = null;
    private ComboBoxImpl comboBox = null;
    private GUIFactory factory = null;

    @BeforeEach
    public void beforeEach(TestInfo info) {
	System.out.printf("entering %s%n", info.getDisplayName());
	button = new ButtonImpl("Button", "My Button", "My Action");
	List<IndexedValue> values = new LinkedList<IndexedValue>();
	comboBox = new ComboBoxImpl("Combo Box", "My Combo Box", values);
	IndexedValue iv1 = comboBox.new IndexedValue("1", "value 1");
	IndexedValue iv2 = comboBox.new IndexedValue("2", "value 2");
	values.add(iv1);
	values.add(iv2);
	comboBox.setSelected(iv2);
	factory = new GUIFactory(button, comboBox);
    }

    @Test
    @DisplayName("test gui factory construction")
    public void testGUIFactoryConstruction() {
	assertNotNull(new GUIFactory(button, comboBox));
    }

    @Test
    @DisplayName("test create button")
    public void testCreateButton() {
	Button button = factory.createButton();
	assertNotNull(button);
	assertTrue(button instanceof ButtonImpl);
    }

    @Test
    @DisplayName("test create combo box")
    public void testCreateComboBox() {
	ComboBox comboBox = factory.createComboBox();
	assertNotNull(comboBox);
	assertTrue(comboBox instanceof ComboBoxImpl);
    }

    @Test
    @DisplayName("test create prototype button")
    public void testCreatePrototypeButton() {
	Button actual = factory.createButton();
	assertNotSame(button, actual);
    }

    @Test
    @DisplayName("test create prototype combo box")
    public void testCreatePrototypeComboBox() {
	ComboBox actual = factory.createComboBox();
	assertNotSame(comboBox, actual);
    }

    @Test
    @DisplayName("test deep clone prototype button")
    public void testDeepClonePrototypeButton() {
	Button actual = factory.createButton();
	// test equality
	assertEquals(button.getId(), ((ButtonImpl) actual).getId());
	assertEquals(button.getLabel(), ((ButtonImpl) actual).getLabel());
	assertEquals(button.getAction(), ((ButtonImpl) actual).getAction());
	// test different reference
	assertNotSame(button.getId(), ((ButtonImpl) actual).getId());
	assertNotSame(button.getLabel(), ((ButtonImpl) actual).getLabel());
	assertNotSame(button.getAction(), ((ButtonImpl) actual).getAction());
    }

    @Test
    @DisplayName("test deep clone prototype combo box")
    public void testDeepClonePrototypeComboBox() {
	ComboBox actual = factory.createComboBox();
	// test equality
	assertEquals(comboBox.getId(), ((ComboBoxImpl) actual).getId());
	assertEquals(comboBox.getLabel(), ((ComboBoxImpl) actual).getLabel());
	assertEquals(comboBox.getSelected(), ((ComboBoxImpl) actual).getSelected());
	assertEquals(comboBox.getValues(), ((ComboBoxImpl) actual).getValues());
	for (int i = 0; i < comboBox.getValues().size(); i++) {
	    assertEquals(comboBox.getValues().get(i), ((ComboBoxImpl) actual).getValues().get(i));
	}
	// test different reference
	assertNotSame(comboBox.getId(), ((ComboBoxImpl) actual).getId());
	assertNotSame(comboBox.getLabel(), ((ComboBoxImpl) actual).getLabel());
	assertNotSame(comboBox.getSelected(), ((ComboBoxImpl) actual).getSelected());
	assertNotSame(comboBox.getValues(), ((ComboBoxImpl) actual).getValues());
	for (int i = 0; i < comboBox.getValues().size(); i++) {
	    assertNotSame(comboBox.getValues().get(i), ((ComboBoxImpl) actual).getValues().get(i));
	}
    }
}
