package DAO;

import Config.JdbcConnection;
import Domain.Cart;
import Domain.Customer;
import Domain.Order;
import Domain.OrderDetails;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;
    private double totalPrice;
    private int orderId;
    private int orderdetailsId;
    private int customer_id;
    public OrderDAO() {
        // Initialize the database connection
        JdbcConnection jdbcConnection=new JdbcConnection();
        try {
            connection = jdbcConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNextOrderId() throws SQLException {
        int nextId = 1;

        // Execute the SELECT statement to fetch the maximum ID
        String query = "SELECT MAX(Order_id) FROM Orders";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Check if the result set has a valid value
        if (resultSet.next()) {
            int maxId = resultSet.getInt(1);
            nextId = maxId + 1;
        }

        // Close the result set and statement
        resultSet.close();
        statement.close();

        return nextId;
    }
    public int getNextOrderDetailsId() throws SQLException {
        int nextId = 1;

        // Execute the SELECT statement to fetch the maximum ID
        String query = "SELECT MAX(Orderdetail_id) FROM OrderDetails";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Check if the result set has a valid value
        if (resultSet.next()) {
            int maxId = resultSet.getInt(1);
            nextId = maxId + 1;
        }

        // Close the result set and statement
        resultSet.close();
        statement.close();

        return nextId;
    }
    public boolean placeorder(List<Cart> cartItems,int customer_id) throws SQLException {
        orderId = getNextOrderId();

        for (Cart item : cartItems) {
            double itemPrice = item.getBookPrice() * item.getQuantity();
            totalPrice += itemPrice;
        }
        String query = "INSERT INTO Orders (Order_id, fk_Customer_id,Order_date, Order_total) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1,orderId);
        statement.setInt(2, customer_id);
        statement.setDate(3, Date.valueOf("2023-03-14"));
        statement.setDouble(4, totalPrice);

        int rowsAffected = statement.executeUpdate();

        for(Cart item:cartItems){
            orderdetailsId=getNextOrderDetailsId();

            String orderDetailsQuery = "INSERT INTO OrderDetails VALUES (?, ?, ?, ?)";
            PreparedStatement orderDetailsStatement = connection.prepareStatement(orderDetailsQuery);
            orderDetailsStatement.setInt(1, orderdetailsId);
            orderDetailsStatement.setInt(2, orderId);
            orderDetailsStatement.setInt(3, item.getBookId());
            orderDetailsStatement.setInt(4, item.getQuantity());
            orderDetailsStatement.executeUpdate();
            orderDetailsStatement.close();
        }

            String query1="DELETE FROM CART WHERE fk_customer_id=?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setInt(1,customer_id);
            statement1.executeUpdate();

        return rowsAffected > 0;
    }

    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE fk_Customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customerId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getInt("Order_id"));
            order.setCustomerId(rs.getInt("fk_Customer_id"));
            order.setOrderDate(rs.getDate("Order_date"));
            order.setOrderStatus(rs.getString("Order_status"));
            order.setOrderTotal(rs.getDouble("Order_total"));
            orders.add(order);
        }

        return orders;
    }

    public List<OrderDetails> getOrderDetailsByOrderID(int order_id) throws SQLException {
        List<OrderDetails> orderdetails = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails od,Orders o,books b WHERE od.fk_order_id=o.order_id and od.fk_book_id=b.book_id and fk_Order_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, order_id);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            OrderDetails orderdetail = new OrderDetails();
            orderdetail.setOrderDetailId(rs.getInt("Orderdetail_id"));
            orderdetail.setQuantity(rs.getInt("Orderdetails_quantity"));
            orderdetail.setBookName(rs.getString("Book_title"));
            orderdetail.setBookPrice(rs.getInt("Book_price"));

            orderdetails.add(orderdetail);
        }

        return orderdetails;
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders o,Customer c where o.fk_customer_id=c.customer_id";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {

            Order order = new Order();
            order.setOrderId(rs.getInt("Order_id"));
            order.setCustomerId(rs.getInt("fk_Customer_id"));
            order.setOrderDate(rs.getDate("Order_date"));
            order.setOrderStatus(rs.getString("Order_status"));
            order.setOrderTotal(rs.getDouble("Order_total"));
            order.setCustomerName(rs.getString("Customer_name"));
            orders.add(order);
        }

        return orders;
    }
    public boolean updateOrderStatus(int orderId, String newStatus) throws SQLException {
        String query = "UPDATE Orders SET Order_status = ? WHERE Order_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, newStatus);
        statement.setInt(2, orderId);

        int rowsAffected = statement.executeUpdate();

        return rowsAffected > 0;
    }

}
