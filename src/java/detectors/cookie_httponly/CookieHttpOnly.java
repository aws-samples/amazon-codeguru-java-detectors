/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.cookie_httponly_rule;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class CookieHttpOnly{
    // {fact rule=cookie-httponly@v1.0 defects=1}
    public void cookieHttpOnlyNoncompliant(HttpServletResponse res) {
        Cookie cookie = new Cookie("name", "not-null");
        cookie.setSecure(true);
        // Noncompliant: HttpOnly is not set before adding the cookie.
        res.addCookie(cookie);
    }
    // {/fact}

    // {fact rule=cookie-httponly@v1.0 defects=0}
    public void cookieHttpOnlyCompliant(HttpServletResponse res) {
        Cookie cookie = new Cookie("name", "not-null");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        // Compliant: HttpOnly is set before adding the cookie.
        res.addCookie(cookie);
    }
    // {/fact}
}
