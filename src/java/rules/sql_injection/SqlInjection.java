/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package rules.sql_injection;

import javax.servlet.http.HttpServletRequest;

public class SqlInjection {

    // {fact rule=sql-injection@v1.0 defects=1}
    public void sqlStatementNonCompliant(HttpServletRequest request, java.sql.Connection connection) throws java.sql.SQLException {
        final String favoriteColor = request.getParameter("favoriteColor");
        String sql = "SELECT * FROM people WHERE favorite_color='" + favoriteColor + "'";
        java.sql.Statement statement = connection.createStatement();
        // Noncompliant: uses unsanitized client input in a SQL query.
        statement.execute(sql);
    }
    // {/fact}

    // {fact rule=sql-injection@v1.0 defects=0}
    public void sqlStatementCompliant(HttpServletRequest request, java.sql.Connection connection) throws java.sql.SQLException {
        final String favoriteColor = request.getParameter("favoriteColor");
        // Compliant: client input is sanitized before using it in a SQL query.
        if (!favoriteColor.matches("[a-z]+")) {
            throw new IllegalArgumentException();
        }
        String sql = "SELECT * FROM people WHERE favorite_color='" + favoriteColor + "'";
        java.sql.Statement statement = connection.createStatement();
        statement.execute(sql);
    }
    // {/fact}

}
