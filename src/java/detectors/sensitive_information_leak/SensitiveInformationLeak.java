/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.sensitive_information_leak;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;

@Slf4j
public class SensitiveInformationLeak {
    // {fact rule=sensitive-information-leak@v1.0 defects=1}
    public void processResponseNoncompliant(Map<String, String> response) {
        // Noncompliant: logs sensitive data without redaction resulting in a possible sensitive information leak.
        final String name = response.get("Name");
        String redacted = RedactionUtil.redact(name);
        log.info(name);
    }
    // {/fact}

    // {fact rule=sensitive-information-leak@v1.0 defects=0}
    public void processResponseCompliant(Map<String, String> response) {
        final String name = response.get("Name");
        String redacted = RedactionUtil.redact(name);
        // Compliant: sensitive data is passed through redaction before logging, preventing possible sensitive information leak.
        log.info(redacted);
    }
    // {/fact}

    static class RedactionUtil {

        static String redact(String name) {
            return name.replaceAll("[^a-zA-Z]", "");
        }
    }
}
