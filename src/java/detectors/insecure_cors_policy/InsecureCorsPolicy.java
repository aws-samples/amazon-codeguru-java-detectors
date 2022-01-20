/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.insecure_cors_policy;

import javax.servlet.http.HttpServletResponse;

public class InsecureCorsPolicy {
    // {fact rule=insecure-cors-policy@v1.0 defects=1}
    public void allowOriginNoncompliant(HttpServletResponse response) {
        // Noncompliant: the Access-Control-Allow-Origin is set to allow any domain.
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
    // {/fact}

    // {fact rule=insecure-cors-policy@v1.0 defects=0}
    public void allowOriginCompliant(HttpServletResponse response) {
        // Compliant: the Access-Control-Allow-Origin is set to allow only a specific list of trusted domains.
        response.setHeader("Access-Control-Allow-Origin", "mytrustedsite.com");
    }
    // {/fact}
}
