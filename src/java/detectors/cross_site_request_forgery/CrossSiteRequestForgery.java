/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.cross_site_request_forgery;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class CrossSiteRequestForgery {

    // {fact rule=cross-site-request-forgery@v1.0 defects=1}
    public void configureCsrfNoncompliant(HttpSecurity security) throws Exception {
        // Noncompliant: disables CSRF protection.
        security.csrf().disable();
    }
    // {/fact}

    // {fact rule=cross-site-request-forgery@v1.0 defects=0}
    public void configureCsrfCompliant(HttpSecurity security) throws Exception {
        // Compliant: enables CSRF protection.
        security.csrf();
    }
    // {/fact}
}
