package rules.output_ignored_on_resultset_next;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class OutputIgnoredOnResultSetNext {

    public long getCountNonCompliant(String getCountSqlQuery) {
        Connection connection = connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(getCountSqlQuery);
            resultSet = preparedStatement.executeQuery();
            // Noncompliant: code does not check if the ResultSet is empty.
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException ex) {
            throw new RuntimeException("Error reading from db connection and getting count", ex);
        } finally {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
    }

    public long getCountCompliant(String getCountSqlQuery) {
        Connection connection = connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(getCountSqlQuery);
            resultSet = preparedStatement.executeQuery();
            // Compliant: code handles the case when the ResultSet is empty.
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error reading from db connection and getting count", ex);
        } finally {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
    }
}