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
package patterns.structural.composite;

/**
 * The Interface XMLBuilder defines the behavior of any XML Builder. We'll use
 * this interface to define an adhering implementation named
 * {@link FormattedXMLBuilder} later.
 */
public interface XMLBuilder {

    /**
     * Builds the xml element taking care of it's children
     *
     * @param element the element
     */
    public void build(XMLElement element);

    /**
     * Builds the version of the xml element (e.g. <xml version="1.0">)
     *
     * @param version the xml version
     */
    public void buildVersion(String version);

    /**
     * Gets the formatted XML. (this returns the assembeled 'product' of the
     * builder)
     *
     * @return the formatted XML document
     */
    public String getFormattedXML();
}
