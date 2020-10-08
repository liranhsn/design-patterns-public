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
import patterns.creational.carparts.Hood;
import patterns.creational.carparts.Wheel;

/**
 * A base factory for creating car parts. This class sets the contract for every
 * other car parts factory.
 */
public abstract class AbstractCarFactory {

    // Nothing to do here - just understand the role of each method

    /** A global counter indicating the amount of parts created by the factory */
    private static int partsCounter = 0;

    /**
     * This method is used in every sub-class method that creates a car part. It
     * is the implementor's role to call this method to generate a car part id
     *
     * @return the next id for a car part and increments the global counter
     */
    public static int getNextId() {
        return partsCounter++;
    }

    /**
     * Creates a new Hood object. This method is to be implemented in every
     * factory sub-class to generate a hierarchy dependent car part
     *
     * @param color the color of the hood
     * @return the hood
     */
    public abstract Hood createHood(String color);

    /**
     * Creates a new Wheel object. This method is to be implemented in every
     * factory sub-class to generate a hierarchy dependent car part
     *
     * @return the wheel
     */
    public abstract Wheel createWheel();

    /**
     * Creates a new Engine object. This method is to be implemented in every
     * factory sub-class to generate a hierarchy dependent car part
     *
     * @return the engine
     */
    public abstract Engine createEngine();
}