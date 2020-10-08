/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation  and/or other materials provided with the distribution.
 * 3. Neither the names of the copyright holders nor the names of any
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package patterns.structural.composite;

/**
 * The Class XMLDirector constructs an XML Document composed of different XML
 * Elements by delegating the work to it's dependent builder. Since we only have
 * on concrete builder (of type {@link FormattedXMLBuilder}), the work of
 * actually building the XML document is delegated to an instance of that type.
 */
public class XMLDirector {

	// TODO: 8. remove this annotation when done
	@SuppressWarnings("unused")
	private XMLBuilder xmlBuilder;

	/**
	 * Instantiates a new XML director.
	 *
	 * @param xmlBuilder the xml builder
	 */
	public XMLDirector(XMLBuilder xmlBuilder) {
		super();
		this.xmlBuilder = xmlBuilder;
	}

	/**
	 * Builds the.
	 *
	 * @param xmlDocument the xml document
	 */
	public void build(XMLDocument xmlDocument) {
		/*
		 * TODO: 6. define the build operation. This is a step by step building of the
		 * xml document so we want to delegate to out builder:
		 *
		 * STEP 1: builder.buildVersion(xmlDocument.getVersion()) STEP 2:
		 * builder.build(xmlDocument.getRoot());
		 */
		throw new UnsupportedOperationException("method XMLDirector.build(XMLDocument xmlDocument) is not implemented yet!");
	}

	/**
	 * Prints the xml.
	 */
	public void printXML() {
		/*
		 * TODO: 7. implement the xml printing by, for example, printing the
		 * builder.getFormattedXML() string to the standard out
		 */
		throw new UnsupportedOperationException("method XMLDirector.printXML() is not implemented yet!");
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			// prepare a root element
			XMLElement root = new XMLElement("root");
			// prepare a Course child
			XMLElement course = new XMLElement("course");
			course.addElement(new XMLElement("name").setData("Design Patterns"));
			course.addElement(new XMLElement("duration").setData("5 days"));
			root.addElement(course);
			// prepare another Course element
			course = new XMLElement("course");
			course.addElement(new XMLElement("name").setData("Java Programming"));
			course.addElement(new XMLElement("duration").setData("5 days"));
			root.addElement(course);
			// prepare an XML Document
			XMLDocument xmlDocument = new XMLDocument(root);
			// set the version
			xmlDocument.setVersion("1.0");
			// build the document
			XMLDirector xmlDirector = new XMLDirector(new FormattedXMLBuilder());
			xmlDirector.build(xmlDocument);
			xmlDirector.printXML();
			System.out.println(">>> Done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
