/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.insecure_cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class InsecureCookie {
    // {fact rule=insecure-cookie@v1.0 defects=1}
    public static void cookieInsecureByDefaultNoncompliant(HttpServletResponse response) {
        Cookie cookie = new Cookie("name", "value");
        // Noncompliant: by default, the Cookie is not secure and not httpOnly.
        response.addCookie(cookie);
    }
    // {/fact}

    // {fact rule=insecure-cookie@v1.0 defects=0}
    public static void cookieSecureCompliant(HttpServletResponse response) {
        Cookie cookie = new Cookie("name", "value");
        // Compliant: the Cookie is secured.
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
    // {/fact}
}
