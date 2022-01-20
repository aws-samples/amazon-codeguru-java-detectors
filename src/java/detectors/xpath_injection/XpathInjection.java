/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.xpath_injection;

import javax.servlet.http.HttpServletRequest;
import org.w3c.dom.Document;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;

public class XpathInjection {

    // {fact rule=xpath-injection@v1.0 defects=1}
    public void evaluateExpressionNoncompliant(HttpServletRequest request, XPath xpath, Document xml)
            throws XPathExpressionException {
        String employeeID = request.getParameter("employeeID");
        String expression = "/employees/" + employeeID + "/sales/monthly";
        // Noncompliant: evaluating expression built from user-supplied parameter can lead to XPath injection.
        xpath.evaluate(expression, xml);
    }
    // {/fact}

    // {fact rule=xpath-injection@v1.0 defects=0}
    public void evaluateExpressionCompliant(HttpServletRequest request, XPath xpath, Document xml)
            throws XPathExpressionException {
        String employeeID = request.getParameter("employeeID");
        // Compliant: user-supplied parameter is sanitized before its inclusion in the expression.
        if (!employeeID.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }
        String expression = "/employees/" + employeeID + "/sales/monthly";
        xpath.evaluate(expression, xml);
    }
    // {/fact}
}
