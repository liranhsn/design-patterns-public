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

import patterns.creational.prototype.Button;

/**
 * The Class ButtonImpl.
 */
public class ButtonImpl implements Button, Cloneable {

    private String id;
    private String label;
    private String action;

    public ButtonImpl() {
        super();
    }

    /**
     * Instantiates a new button impl.
     *
     * @param id the id
     * @param label the label
     * @param action the action
     */
    public ButtonImpl(String id, String label, String action) {
        super();
        this.id = id;
        this.label = label;
        this.action = action;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#clone()
     */
    @Override
    public Button clone() {
        // this ButtonImpl is cloneable. You must therefore implement the
        // clone method. The easiest way is to return a clone obtained via a
        // call to super.clone(). [e.g. return (Button) super.clone();].
        // However, if this, for any reason, throws an exception, we must handle
        // it and return some sort of an alternative...
        try {
            ButtonImpl clone = (ButtonImpl) super.clone();
            clone.id = new String(id);
            clone.label = new String(label);
            clone.action = new String(action);
            return clone;
        } catch (CloneNotSupportedException cnsex) {
            cnsex.printStackTrace();
            return new ButtonImpl(new String(id), new String(label), new String(action));
        }
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ButtonImpl [id=" + id + ", label=" + label + ", action=" + action + "]";
    }
}
