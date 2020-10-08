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
package patterns.creational.abstractfactory;

import patterns.creational.carparts.Engine;
import patterns.creational.carparts.FamilyCarEngine;
import patterns.creational.carparts.FamilyCarHood;
import patterns.creational.carparts.FamilyCarWheel;
import patterns.creational.carparts.Hood;
import patterns.creational.carparts.Wheel;

/**
 * A factory for creating Family Car objects.
 */
public class FamilyCarFactory extends AbstractCarFactory {

    /*
     * (non-Javadoc)
     *
     * @see
     * patterns.creational.abstractfactory.AbstractCarFactory#createHood(java
     * .lang.String)
     */
    @Override
    public Hood createHood(String color) {
        Hood hood = new FamilyCarHood(color);
        hood.setId(getNextId());
        return hood;
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.creational.abstractfactory.AbstractCarFactory#createWheel()
     */
    @Override
    public Wheel createWheel() {
        // Implement this method exactly like the 'createHood' method,
        // only to return a the relevant FamilyCarWheel instance with it's id
        // property correctly set
        Wheel wheel = new FamilyCarWheel();
        wheel.setId(getNextId());
        return wheel;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * patterns.creational.abstractfactory.AbstractCarFactory#createEngine()
     */
    @Override
    public Engine createEngine() {
        // Implement this method exactly like the 'createHood' method,
        // only to return a the relevant FamilyCarEngine instance with it's id
        // property correctly set
        Engine engine = new FamilyCarEngine();
        engine.setId(getNextId());
        return engine;
    }
}
