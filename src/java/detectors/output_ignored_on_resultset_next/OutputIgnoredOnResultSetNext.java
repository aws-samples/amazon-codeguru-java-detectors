package rules.output_ignored_on_resultset_next;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

class OutputIgnoredOnResultSetNext {

    DataSource dataSource;

    // {fact rule=output-ignored-on-resultset-next@v1.0 defects=1}
    public long getCountNonCompliant(String getCountSqlQuery) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(getCountSqlQuery);
            resultSet = preparedStatement.executeQuery();
            // Noncompliant: code does not check if the ResultSet is empty.
            resultSet.next();
            return resultSet.getLong(1);
        } finally {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
    }
    // {/fact}

    // {fact rule=output-ignored-on-resultset-next@v1.0 defects=0}
    public long getCountCompliant(String getCountSqlQuery) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(getCountSqlQuery);
            resultSet = preparedStatement.executeQuery();
            // Compliant: code handles the case when the ResultSet is empty.
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                return -1;
            }
        } finally {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
    }
    // {/fact}
}
