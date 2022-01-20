/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.untrusted_data_in_decision;

import javax.servlet.http.HttpServletRequest;

public class UntrustedDataInDecision {

    // {fact rule=untrusted-data-in-decision@v1.0 defects=1}
    public void logSessionIdNoncompliant(HttpServletRequest request) {
        final String sessionId = request.getRequestedSessionId();
        // Noncompliant: user-supplied session ID is used to make a decision.
        if (sessionId != null && sessionId.equals("ImportantSession")) {
            System.out.println("Client-provided session ID: " + sessionId + " is important");
        }
    }
    // {/fact}

    // {fact rule=untrusted-data-in-decision@v1.0 defects=0}
    public void logSessionIdCompliant(HttpServletRequest request) {
        // Compliant: user-supplied session ID is not used to make decisions.
        System.out.println("Client-provided session ID: " + request.getRequestedSessionId());
    }
    // {/fact}
}
