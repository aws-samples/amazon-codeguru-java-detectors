/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.os_command_injection;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class OsCommandInjection {

    // {fact rule=os-command-injection@v1.0 defects=1}
    public void createProcessNoncompliant(HttpServletRequest request) {
        String favoriteColor = request.getParameter("favoriteColor");
        // Noncompliant: user-supplied parameter is passed to an OS command and could be malicious.
        ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/program", "--color", favoriteColor);
        try {
            pb.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    // {/fact}

    // {fact rule=os-command-injection@v1.0 defects=0}
    public void createProcessCompliant(HttpServletRequest request) {
        String favoriteColor = request.getParameter("favoriteColor");
        // Compliant: user-supplied parameter is sanitized before passing it to an OS command.
        if (!favoriteColor.matches("[a-z]+")) {
            throw new IllegalArgumentException();
        }
        ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/program", "--color", favoriteColor);
        try {
            pb.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    // {/fact}
}
