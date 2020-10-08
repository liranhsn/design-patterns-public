/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation  and/or other materials provided with the distribution.
 * 3. Neither the names of the copyright holders nor the names of any
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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
