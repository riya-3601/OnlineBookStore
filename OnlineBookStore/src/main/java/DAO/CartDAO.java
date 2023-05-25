package DAO;

import Config.JdbcConnection;
import Domain.Books;
import Domain.Cart;
import Domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private Connection connection;

    public CartDAO() {
        // Initialize the database connection
        JdbcConnection jdbcConnection=new JdbcConnection();
        try {
            connection = jdbcConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNextCartId() throws SQLException {
        int nextId = 1;

        // Execute the SELECT statement to fetch the maximum ID
        String query = "SELECT MAX(Cart_id) FROM Cart";
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

    public boolean addToCart( Customer customer,Books book) throws SQLException {
        int cartId = getNextCartId();
        String query = "INSERT INTO Cart (Cart_id, fk_Customer_id, fk_Book_id, Cart_quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, cartId);
        statement.setInt(2, customer.getid());
        statement.setInt(3, book.getBookId());
        statement.setInt(4, 1); // Set

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }

    public List<Cart> getCartItemsByCustomerId(int customerId) {
        List<Cart> cartItems = new ArrayList<>();

        try {
            String query = "SELECT * FROM Cart c, Books b WHERE c.fk_book_id = b.book_id AND c.fk_customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int cartId = resultSet.getInt("cart_id");
                int bookId = resultSet.getInt("book_id");
                String bookTitle = resultSet.getString("book_title");
                int quantity=resultSet.getInt("Cart_quantity");
                double bookPrice = resultSet.getDouble("book_price");
                // Retrieve other relevant fields from the result set

                // Create a CartItem object
                Cart cartItem = new Cart();
                cartItem.setCartId(cartId);
                cartItem.setBookId(bookId);
                cartItem.setBookTitle(bookTitle);
                cartItem.setQuantity(quantity);
                cartItem.setBookPrice(cartId);
                cartItem.setBookPrice(bookPrice);
                // Add the cart item to the list
                cartItems.add(cartItem);
            }


            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    public void deleteCartItem( int cartItemId) throws SQLException {
        int cartId = cartItemId;
        String query = "DELETE FROM Cart WHERE cart_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, cartId);


        int rowsAffected = statement.executeUpdate();

    }

}


