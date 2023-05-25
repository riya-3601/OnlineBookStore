package DAO;

import Config.JdbcConnection;
import Domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//Customer_id int primary key,
//        Customer_emailid varchar(40) NOT NULL,
//        Customer_password varchar(40) NOT NULL,
//        Customer_name varchar(20) NOT NULL,
//        Customer_gender varchar(10) NOT NULL,
//        Customer_mobile_no
public class CustomerDao {
    private Connection connection;
    public CustomerDao() {
        // Initialize the database connection
        JdbcConnection jdbcConnection=new JdbcConnection();
        try {
            connection = jdbcConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean registerUser(Customer customer) {
        String query = "INSERT INTO Customer (Customer_emailid,Customer_password,Customer_name,Customer_gender,Customer_mobile_no,Customer_address) VALUES ( ?, ? , ? , ? , ?,?)";

        JdbcConnection jdbcConnection = new JdbcConnection();
        try (Connection connection = jdbcConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            //statement.setInt(1, 2);
            statement.setString(1, customer.getemailid());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getName());
            statement.setString(4, customer.getGender());
            statement.setString(5, customer.getMobile());
            statement.setString(6,customer.getCustomer_address());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEmailUnique(String email) throws SQLException {
            String query = "SELECT COUNT(*) FROM Customer WHERE Customer_emailid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }
        return false;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        String query = "SELECT * FROM customer where user_type='0'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int customerId = rs.getInt("Customer_id");
                String emailid = rs.getString("Customer_emailid");
                String password = rs.getString("Customer_password");
                String name = rs.getString("Customer_name");
                String gender = rs.getString("Customer_gender");
                String mobileNo = rs.getString("Customer_mobile_no");


                Customer customer = new Customer();
                customer.setid(customerId);
                customer.setemailid(emailid);
                customer.setPassword(password);
                customer.setName(name);
                customer.setGender(gender);
                customer.setMobile(mobileNo);

                customers.add(customer);
            }
        }

        return customers;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        System.out.println(customerId);
        Customer customer = null;
        String query = "SELECT * FROM customer where Customer_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customerId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            customer = new Customer();
            int Id = rs.getInt("Customer_id");
            String emailid = rs.getString("Customer_emailid");
            String password = rs.getString("Customer_password");
            String name = rs.getString("Customer_name");
            String gender = rs.getString("Customer_gender");
            String mobileNo = rs.getString("Customer_mobile_no");
            String address=rs.getString("Customer_address");

            customer.setid(customerId);
            customer.setemailid(emailid);
            customer.setPassword(password);
            customer.setName(name);
            customer.setGender(gender);
            customer.setMobile(mobileNo);
            customer.setCustomer_address(address);
        }
        return customer;
    }
    public boolean updateCustomer(int id,String address) {
        String query = "UPDATE customer SET customer_address = ? WHERE customer_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, address);
            statement.setInt(2, id);

            int rowsaffected=statement.executeUpdate();
            return rowsaffected>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
