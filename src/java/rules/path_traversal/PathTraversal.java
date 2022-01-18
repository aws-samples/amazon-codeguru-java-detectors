/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.path_traversal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class PathTraversal {

    // {fact rule=path-traversal@v1.0 defects=1}
    public void createFileNonCompliant(HttpServletRequest request, HttpServletResponse response) {
        String basePath = "/var/example/base/";
        String relativePath = request.getParameter("relativePath");
        // Noncompliant: user-supplied relative path is not sanitized and could contain malicious special characters.
        File fileTarget = new File(basePath + relativePath);
    }
    // {/fact}

    // {fact rule=path-traversal@v1.0 defects=0}
    public void createFileCompliant(HttpServletRequest request) {
        String basePath = "/var/example/base/";
        // Compliant: user-supplied relative path is sanitized before use.
        String relativePath = request.getParameter("relativePath").replaceAll("..", "");
        File fileTarget = new File(basePath + relativePath);
    }
    // {/fact}
}
