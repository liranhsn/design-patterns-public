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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import patterns.creational.prototype.ComboBox;

/**
 * The Class ComboBoxImpl is a {@link ComboBox} implementation. It has an 'id'
 * and 'label' properties and a list of 'IndexedValue'. The {@link IndexedValue}
 * class is just a label/value wrapper that any combo box uses.
 */
public class ComboBoxImpl implements ComboBox, Cloneable {

    private String id;
    private String label;
    private List<IndexedValue> values;
    private IndexedValue selected;

    /**
     * Instantiates a new combo box impl.
     */
    public ComboBoxImpl() {
        super();
    }

    /**
     * Instantiates a new combo box impl.
     *
     * @param id the id
     * @param label the label
     * @param values the values
     */
    public ComboBoxImpl(String id, String label, List<IndexedValue> values) {
        super();
        this.id = id;
        this.label = label;
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<IndexedValue> getValues() {
        return values;
    }

    public void setValues(List<IndexedValue> values) {
        this.values = values;
    }

    public IndexedValue getSelected() {
        return selected;
    }

    public void setSelected(IndexedValue selected) {
        this.selected = selected;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() {
        // this ComboBoxImpl is cloneable. You must therefore implement
        // the clone method. The easiest way is to return a clone obtained via a
        // call to super.clone(). [e.g. return (ComboBoxImpl) super.clone();].
        // This will not take care of the 'selected' property and the list of
        // IndexedValues cloning. We need to take care of this manually,
        // Of course, if this, for any reason, throws an exception, we must
        // handle it and return some sort of an alternative...
        try {
            // Yoy'll need to call the super.clone() method to get a
            // reference to a ComboBoxImpl copy
            ComboBoxImpl copy = (ComboBoxImpl) super.clone();
            // You'll need to call the clone() method of this.selected to clone
            // it
            copy.selected = selected.clone();
            // TODO: ...and clone every property
            copy.id = new String(id);
            copy.label = new String(label);
            // You'll need to instantiate the the copy.values properties
            // with something like a new LinkedList<IndexedValue>();
            copy.values = new LinkedList<IndexedValue>();
            // Then, you'll need to add a clone of each indexed value in the
            // original list
            for (IndexedValue indexedValue : values)
                copy.values.add(indexedValue.clone());
            return copy;
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
            List<IndexedValue> copyOfValues = new LinkedList<>();
            Collections.copy(copyOfValues, values);
            return new ComboBoxImpl(new String(id), new String(label), copyOfValues);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ComboBoxImpl [id=" + id + ", label=" + label + ", selected=" + selected);
        if (values != null) {
            sb.append(", values=\n");
            for (IndexedValue value : values) {
                sb.append("\t" + value + "\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * The Class IndexedValue is just a wrapper around an index (key) and value
     * properties. Any combo box uses an similar object for keeping reference to
     * a list of identifiable values
     */
    class IndexedValue implements Cloneable {

        private String index;
        private String value;

        public IndexedValue(String index, String value) {
            super();
            this.index = index;
            this.value = value;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#clone()
         */
        @Override
        public IndexedValue clone() throws CloneNotSupportedException {
            try {
                return (IndexedValue) super.clone();
            } catch (CloneNotSupportedException cnsex) {
                System.err.println("Error: " + cnsex.getMessage());
                cnsex.printStackTrace(System.err);
                return new IndexedValue(index, value);
            }
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((index == null) ? 0 : index.hashCode());
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            IndexedValue other = (IndexedValue) obj;
            if (index == null) {
                if (other.index != null)
                    return false;
            } else if (!index.equals(other.index))
                return false;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "IndexedValue [index=" + index + ", value=" + value + "]";
        }
    }
}
