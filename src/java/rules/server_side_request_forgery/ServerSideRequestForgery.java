/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: MIT-0
 */

package rules.server_side_request_forgery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerSideRequestForgery {

    // {begin-fact rule=server-side-request-forgery defects=1}
    public void createConnNonCompliant(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        URL url = new URL(req.getParameter("url"));
        // Noncompliant: user-supplied URL is not sanitized before creating a connection.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }
    // {/end-fact}

    // {begin-fact rule=server-side-request-forgery defects=0}
    public void createConnCompliant(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String urlAllowedPrefix = "https://example.com/";
        String inputUrl = req.getParameter("url");
        if (!inputUrl.startsWith(urlAllowedPrefix))
            throw new IllegalArgumentException();
        URL url = new URL(inputUrl);
        // Compliant: user-supplied URL is sanitized before creating a connection.
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
    }
    // {/end-fact}
}
