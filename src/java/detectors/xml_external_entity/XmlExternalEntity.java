/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.xml_external_entity;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

public class XmlExternalEntity {

    // {fact rule=xml-external-entity@v1.0 defects=1}
    public DocumentBuilder createDocumentBuilderNoncompliant(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        // Noncompliant: not configured to handle external entities.
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(inputStream);
        return builder;
    }
    // {/fact}

    // {fact rule=xml-external-entity@v1.0 defects=0}
    public DocumentBuilder createDocumentBuilderCompliant(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        // Compliant: configured to disable external entities.
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.parse(inputStream);
        return builder;
    }
    // {/fact}

}
