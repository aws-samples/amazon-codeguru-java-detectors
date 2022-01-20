/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.http_response_splitting;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpResponseSplitting {
    // {fact rule=http-response-splitting@v1.0 defects=1}
    public void headerSplittingProtectionDisabledNoncompliant() {
        // Noncompliant: false argument disables header validation.
        final DefaultHttpHeaders headers = new DefaultHttpHeaders(false);
        headers.clear();
    }
    // {/fact}

    // {fact rule=http-response-splitting@v1.0 defects=1}
    public void addToCookieWithoutSanitizationNoncompliant(HttpServletRequest request, HttpServletResponse response) {
        final String name = request.getParameter("name");
        // Noncompliant: parameter added to cookie might contain special chars.
        Cookie cookie = new Cookie("name", name);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
    // {/fact}

    // {fact rule=http-response-splitting@v1.0 defects=0}
    public void headerSplittingProtectionEnabledCompliant() {
        // Compliant: header validation is enabled by default.
        final DefaultHttpHeaders headers = new DefaultHttpHeaders();
        headers.clear();
        // Compliant: header validation is enabled explicitly.
        final DefaultHttpHeaders moreHeaders = new DefaultHttpHeaders(true);
        moreHeaders.clear();
    }
    // {/fact}

    // {fact rule=http-response-splitting@v1.0 defects=0}
    public void addToCookieWithSanitizationCompliant(HttpServletRequest request, HttpServletResponse response) {
        // Compliant: parameter sanitized before adding to cookie.
        final String name = request.getParameter("name").replaceAll("[^a-zA-Z ]", "");
        Cookie cookie = new Cookie("name", name);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }
    // {/fact}
}
