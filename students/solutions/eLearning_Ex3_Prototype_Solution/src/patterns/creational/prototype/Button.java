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
package patterns.creational.prototype;

import patterns.creational.prototype.gui.GUIFactory;

/**
 * The Interface Button represents a Button object that the {@link GUIFactory}
 * returns. It must be a cloneable instance but the decision if this interface
 * is cloneable, or the implementation is a cloneable, can be postponed.
 */
// decide if this is also a cloneable. If it is, you may extend interface Cloneable
public interface Button extends Cloneable {
}
