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

import java.util.ResourceBundle;

import patterns.creational.carparts.Engine;
import patterns.creational.carparts.Hood;
import patterns.creational.carparts.Wheel;

/**
 * The Class FactoryManager manages the creation of a the concrete
 * {@link AbstractCarFactory} that is returned by it's factory method
 * {@link FactoryManager#getFactory()}. The concrete factory class name is
 * configured in a properties file read by this manager. The manger then
 * instantiates the factory by using standard reflection API.
 */
public class FactoryManager {

    static final String PROP_KEY = "cars.factory.name";
    static final String DEFAULT_FACTORY = "patterns.creational.abstractfactory.MiniVanFactory";
    static final String RESOURCE_BUNDLE_NAME = "patterns.creational.abstractfactory.abstractfactory";
    private AbstractCarFactory factory;
    private String factoryClassName;
    public static volatile FactoryManager INSTANCE;

    /**
     * Instantiates a new factory manager.
     */
    private FactoryManager() {
        super();
        /*
         * Read the properties file using a resource bundle. This is typically done by code like this: ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME);
         */
        ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
        /*
         * Initialize the factoryClassName property. It's value is the outcome of reading the 'PROP_KEY' property from the resource bundle [e.g. rb.getString(PROP_KEY)];
         */
        factoryClassName = rb.getString(PROP_KEY);
        /*
         * Make sure if the property does not exist, to initialize the 'factoryClassName' with a default value. [e.g. if (factoryClassName == null) factoryClassName = DEFAULT_FACTORY]
         */
        if (factoryClassName == null) {
            factoryClassName = DEFAULT_FACTORY;
        }
        /*
         *  Now that you got a 'factoryClassName' value, use reflection [e.g. factory = Class.forName(factoryClassName).newInstance()] to instantiate the factory
         */
        try {
            factory = (AbstractCarFactory) Class.forName(factoryClassName).getDeclaredConstructor(new Class<?>[]{}).newInstance(new Object[] {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the single instance of FactoryManager.
     *
     * @return single instance of FactoryManager
     */
    public synchronized static FactoryManager getInstance() {
        if (INSTANCE == null) {
            synchronized (FactoryManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FactoryManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Gets the factory.
     *
     * @return the factory
     */
    public synchronized AbstractCarFactory getFactory() {
        assert factory != null : "member 'factory' is null";
        // Once the constructor is implemented and all fields are initialized, return the factory
        return factory;
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        FactoryManager factoryManager = FactoryManager.getInstance();
        AbstractCarFactory factory = factoryManager.getFactory();
        System.out.println("Factory of type: " + factory.getClass().getSimpleName());
        Hood hood = factory.createHood("red");
        System.out.println(hood);
        Wheel wheel = factory.createWheel();
        System.out.println(wheel);
        Engine engine = factory.createEngine();
        System.out.println(engine);
    }
}