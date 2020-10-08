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

import java.util.List;
import java.util.stream.IntStream;

/**
 * The Class FormattedXMLBuilder is an {@link XMLBuilder} that can build a well
 * structured XML document. This class is provided for you. Nothing else to do
 * here, other than review the code
 */
public class FormattedXMLBuilder implements XMLBuilder {

    /*
     *  TODO: 1. review only
     */

    public final static String INDENT_STRING = "  ";
    private StringBuilder sb = new StringBuilder();

    /*
     * (non-Javadoc)
     *
     * @see
     * patterns.structural.composite.XMLBuilder#build(patterns.structural.composite
     * .XMLElement)
     */
    @Override
    public void build(XMLElement element) {
        // start at level '0' (no indentation)
        this.buildInternal(element, 0);
    }

    /**
     * Add an indentation string as many times as required by the given level
     *
     * @param level the level of indentation
     */
    private void indent(int level) {
    	IntStream.range(0, level).forEach(i -> this.sb.append(INDENT_STRING));
    }

    /**
     * Builds the XML document recursively by adding the name of the XMLElement and
     * iterating through it's children, adding each child's name and data,
     * indenting as necessary and closing the element's XML tag.
     *
     * @param element the XML element
     * @param level the level of indentation
     */
    private void buildInternal(XMLElement element, int level) {
        this.indent(level);
        this.sb.append("<").append(element.getName()).append(">").append("\n");
        List<XMLElement> children = element.getChildren();
        children.forEach(child -> this.buildInternal(child, level + 1));
        if (element.getData() != null) {
            this.indent(level + 1);
            this.sb.append(element.getData()).append("\n");
        }
        this.indent(level);
        this.sb.append("</").append(element.getName()).append(">").append("\n");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * patterns.structural.composite.XMLBuilder#buildVersion(java.lang.String)
     */
    @Override
    public void buildVersion(String xmlVersion) {
        this.sb.append("<?xml version=\"").append(xmlVersion).append("\" encoding=\"UTF-8\"?>\n");
    }

    /*
     * (non-Javadoc)
     *
     * @see patterns.structural.composite.XMLBuilder#getFormattedXML()
     */
    @Override
    public String getFormattedXML() {
        return this.sb.toString();
    }
}
