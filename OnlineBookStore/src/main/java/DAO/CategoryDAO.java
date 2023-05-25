package DAO;

import Config.JdbcConnection;
import Domain.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static Connection connection;

    public CategoryDAO() {
        // Initialize the database connection
        JdbcConnection jdbcConnection=new JdbcConnection();
        try {
            connection = jdbcConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getNextCategoryId() throws SQLException {
        int nextId = 1;

        // Execute the SELECT statement to fetch the maximum ID
        String query = "SELECT MAX(Category_id) FROM Category";
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

    public static List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("Category_id");
                String categoryName = resultSet.getString("category_name");
                String categoryImage = resultSet.getString("category_image");

                Category category = new Category(categoryId, categoryName, categoryImage);
                categories.add(category);
            }


        return categories;
    }

    public static boolean addCategory(Category category) throws SQLException {
        int category_id=getNextCategoryId();
        String sql = "INSERT INTO Category  VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Set the category name parameter in the SQL statement
        statement.setInt(1, category_id);
        statement.setString(2, category.getCategoryName());
        statement.setString(3, category.getCategoryImage());

        // Execute the SQL statement to insert the category

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;

    }

    public static boolean deleteCategoryById(int Category_id)throws SQLException {
        String sql = "DELETE FROM Category WHERE Category_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Set the book ID parameter in the SQL statement
        statement.setInt(1, Category_id);

        // Execute the SQL statement to delete the book
        statement.executeUpdate();
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;

    }

}

