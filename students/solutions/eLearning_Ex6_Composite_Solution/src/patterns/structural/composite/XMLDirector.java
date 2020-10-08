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
 * The Class XMLDirector constructs an XML Document composed of different XML
 * Elements by delegating the work to it's dependent builder. Since we only have
 * on concrete builder (of type {@link FormattedXMLBuilder}), the work of
 * actually building the XML document is delegated to an instance of that type.
 */
public class XMLDirector {

    private XMLBuilder xmlBuilder;

    public XMLDirector(XMLBuilder xmlBuilder) {
        super();
        this.xmlBuilder = xmlBuilder;
    }

    public void build(XMLDocument xmlDocument) {
        // define the build operation: for example:
        xmlBuilder.buildVersion(xmlDocument.getVersion());
        xmlBuilder.build(xmlDocument.getRoot());
    }

    /*
     * Prints the xml.
     */
    public void printXML() {
        // implement the xml printing. For example:
        System.out.printf("%s%n",xmlBuilder.getFormattedXML());
    }

    public static void main(String[] args) {
        try {
            XMLElement root = new XMLElement("root"); // prepare a root element
            XMLElement course = new XMLElement("course"); // prepare a Course child
            course.addElement(new XMLElement("name").setData("Design Patterns"));
            course.addElement(new XMLElement("duration").setData("5 days"));
            root.addElement(course);
            course = new XMLElement("course"); // prepare another Course element
            course.addElement(new XMLElement("name").setData("Java Programming"));
            course.addElement(new XMLElement("duration").setData("5 days"));
            root.addElement(course);
            XMLDocument xmlDocument = new XMLDocument(root); // prepare an XML Document
            xmlDocument.setVersion("1.0"); // set the version
            XMLDirector xmlDirector = new XMLDirector(new FormattedXMLBuilder()); // build the document
            xmlDirector.build(xmlDocument);
            xmlDirector.printXML();
            System.out.println(">>> Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
