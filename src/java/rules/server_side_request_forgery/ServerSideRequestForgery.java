/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.server_side_request_forgery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerSideRequestForgery {

    // {fact rule=server-side-request-forgery@v1.0 defects=1}
    public void createConnectionNonCompliant(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        URL url = new URL(request.getParameter("url"));
        // Noncompliant: user-supplied URL is not sanitized before creating a connection.
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
    }
    // {/fact}

    // {fact rule=server-side-request-forgery@v1.0 defects=0}
    public void createConnectionCompliant(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String urlAllowedPrefix = "https://amazon.com/";
        String inputUrl = request.getParameter("url");
        if (!inputUrl.startsWith(urlAllowedPrefix))
            throw new IllegalArgumentException();
        URL url = new URL(inputUrl);
        // Compliant: user-supplied URL is sanitized before creating a connection.
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
    }
    // {/fact}
}
