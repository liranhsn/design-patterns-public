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

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import patterns.creational.prototype.Button;
import patterns.creational.prototype.ComboBox;
import patterns.creational.prototype.gui.ComboBoxImpl.IndexedValue;

public class GUIFactory {

    private Button button;
    private ComboBox combo;

    public GUIFactory(Button button, ComboBox combo) {
        this.button = button;
        this.combo = combo;
    }

    /**
     * implement this method to return a button clone. Is the button cloneable?
     *
     * We can do this, for example, using reflection:
     *
     * <pre>
     * Button copy = null;
     * try {
     *     Method method = button.getClass().getDeclaredMethod(&quot;clone&quot;, new Class&lt;?&gt;[] {});
     *     copy = (Button) method.invoke(this.button, new Object[] {});
     * } catch (Exception ex) {
     *     ex.printStackTrace();
     * }
     * return copy;
     * </pre>
     **/
    public Button createButton() {
        Button copy = null;
        try {
            Method method = button.getClass().getDeclaredMethod("clone", new Class<?>[] {});
            copy = (Button) method.invoke(this.button, new Object[] {});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(getClass().getSimpleName() + ".createButton() -> created " + copy.getClass().getName());
        return copy;
    }

    /**
     * TODO: Implement this method to return a combo box copy. Is the ComboBox Cloneable?
     *
     * We can do this, for example, using reflection:
     *
     * <pre>
     * ComboBox copy = null;
     * try {
     *     Method method = this.combo.getClass().getDeclaredMethod(&quot;clone&quot;, new Class&lt;?&gt;[] {});
     *     copy = (ComboBox) method.invoke(this.combo, new Object[] {});
     * } catch (Exception ex) {
     *     ex.printStackTrace();
     * }
     * return copy;
     * </pre>
     */
    public ComboBox createComboBox() {
        ComboBox copy = null;
        try {
            Method method = this.combo.getClass().getDeclaredMethod("clone", new Class<?>[] {});
            copy = (ComboBox) method.invoke(this.combo, new Object[] {});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(getClass().getSimpleName() + ".createComboBox() -> created " + copy.getClass().getName());
        return copy;
    }

    public static void main(String[] args) throws Exception {
        Button button = new ButtonImpl("Button", "My Button", "My Action");
        List<IndexedValue> values = new LinkedList<IndexedValue>();
        ComboBoxImpl comboBox = new ComboBoxImpl("Combo Box", "My Combo Box", values);
        IndexedValue iv1 = comboBox.new IndexedValue("1", "value 1");
        IndexedValue iv2 = comboBox.new IndexedValue("2", "value 2");
        values.add(iv1);
        values.add(iv2);
        comboBox.setSelected(iv2);
        GUIFactory factory = new GUIFactory(button, comboBox);
        System.out.println(factory.createButton());
        System.out.println(factory.createComboBox());
    }
}
