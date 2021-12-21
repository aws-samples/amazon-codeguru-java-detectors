/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.weak_obfuscation_of_request;

import org.apache.http.client.methods.HttpPost;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;

public class BasicAuthentication {
    // {fact rule=weak-obfuscation-of-request@v1.0 defects=1}
    public void setHeaderNonCompliant(final URL url, final String password) throws URISyntaxException {
        final String encoding = Base64.getEncoder().encode(password.getBytes()).toString();
        HttpPost httppost = new HttpPost(url.toURI());
        // Noncompliant: enables HTTP Basic Authentication making it insecure.
        httppost.setHeader("Authorization", "Basic " + encoding);
    }
    // {/fact}
}
