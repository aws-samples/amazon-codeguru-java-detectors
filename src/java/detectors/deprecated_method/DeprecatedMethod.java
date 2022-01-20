/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.deprecated_method;

import org.springframework.security.crypto.codec.Base64;

public class DeprecatedMethod {

    // {fact rule=deprecated-method@v1.0 defects=1}
    public String encodePasswordNoncompliant(String password) {
        // Noncompliant: uses deprecated Base64 class from Spring Framework.
        byte[] encodedId = Base64.encode(password.toLowerCase().getBytes());
        return new String(encodedId);
    }
    // {/fact}

    // {fact rule=deprecated-method@v1.0 defects=0}
    public String encodePasswordCompliant(String password) {
        // Compliant: uses the standard Base64 class from the Java SDK..
        byte[] encodedId = java.util.Base64.getEncoder().encode(password.toLowerCase().getBytes());
        return new String(encodedId);
    }
    // {/fact}
}
