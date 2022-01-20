/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.weak_obfuscation_of_request;

import org.apache.http.client.methods.HttpPost;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;

public class WeakObfuscationOfRequest {
    // {fact rule=weak-obfuscation-of-request@v1.0 defects=1}
    public void httpAuthenticationNoncompliant(final URL url, final String password) throws URISyntaxException {
        final String encoding = Base64.getEncoder().encode(password.getBytes()).toString();
        HttpPost httppost = new HttpPost(url.toURI());
        // Noncompliant: uses HTTP Basic Authentication.
        httppost.setHeader("Authorization", "Basic " + encoding);
    }
    // {/fact}
}
