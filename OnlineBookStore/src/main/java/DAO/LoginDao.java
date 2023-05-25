package DAO;

import Config.JdbcConnection;
import Domain.Books;
import Domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao {
    public boolean validateCredentials(String username, String password) throws SQLException {
        String query = "SELECT * FROM Customer WHERE Customer_emailid = ? AND Customer_password = ?";

        JdbcConnection jdbcConnection = new JdbcConnection();
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public boolean isAdmin(String username, String password) throws SQLException {
        String query = "SELECT * FROM Customer WHERE Customer_emailid = ? AND Customer_password = ? AND User_type='1'";

        JdbcConnection jdbcConnection = new JdbcConnection();
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public Customer getCustomerDetails (String username, String password) throws SQLException {
        String query = "SELECT * FROM Customer WHERE Customer_emailid = ? AND Customer_password = ?";

        JdbcConnection jdbcConnection = new JdbcConnection();
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Customer cust = new Customer();

                cust.setid(resultSet.getInt("Customer_id"));


                return cust;
            }

            return null;
        }
    }


}

