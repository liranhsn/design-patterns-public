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
package com.acme.memento;

/**
 * The interface IMemento is a flag interface to represent a memento
 *
 * The implementation stores internal state of the Originator object
 * {@link ICalculator}. The state can include any number of state variables.
 *
 * The Memento must have two interfaces:
 *
 * 1. An interface to the caretaker: This interface must not allow any
 * operations or any access to internal state stored by the memento and thus
 * honors encapsulation.
 *
 * 2. The other interface is to the originator and allows the originator to
 * access any state variables necessary to for the originator to restore
 * previous state.
 */
public interface IMemento {
	// no operations permitted for the caretaker
}
