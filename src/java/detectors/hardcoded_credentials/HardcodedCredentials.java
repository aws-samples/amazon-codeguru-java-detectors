/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package detectors.hardcoded_credentials;

import java.sql.Connection;
import java.sql.DriverManager;

public class HardcodedCredentials {

    // {fact rule=hardcoded-credentials@v1.0 defects=1}
    public void createSqlConnectionNoncompliant() throws Exception {
        // Noncompliant: password is hardcoded.
        final Connection connection = DriverManager.getConnection("some url",
                "username", "password");
        connection.close();
    }
    // {/fact}

    // {fact rule=hardcoded-credentials@v1.0 defects=0}
    public void createSqlConnectionCompliant() throws Exception {
        // Compliant: password is obtained from environment.
        final Connection connection = DriverManager.getConnection("some url",
                "user", System.getProperty("pwd"));
        connection.close();
    }
    // {/fact}
}
