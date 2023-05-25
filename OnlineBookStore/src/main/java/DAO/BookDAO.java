package DAO;

import Config.JdbcConnection;
import Domain.Books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        // Initialize the database connection
        JdbcConnection jdbcConnection=new JdbcConnection();
        try {
            connection = jdbcConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getNextBookId() throws SQLException {
        int nextId = 1;

        // Execute the SELECT statement to fetch the maximum ID
        String query = "SELECT MAX(Book_id) FROM Books";
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

    public List<Books> getAllBooks() {
        List<Books> books = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = "select c.category_name,c.category_id,b.book_id,b.book_isbn,b.book_title,b.book_author,b.book_price,b.book_publisher,b.book_rating,b.book_image from books b,category c where b.fk_category_id=c.category_id";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Books book = new Books();
                book.setCategoryName(resultSet.getString("category_name"));
                book.setCategoryId(Integer.parseInt(resultSet.getString("category_id")));
                book.setBookId(resultSet.getInt("book_id"));
                book.setBookISBN(Integer.parseInt(resultSet.getString("book_isbn")));
                book.setBookTitle(resultSet.getString("book_title"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookPrice(Double.parseDouble(resultSet.getString("book_price")));
                book.setBookPublisher(resultSet.getString("book_publisher"));
                book.setBookRating(Integer.parseInt(resultSet.getString("book_rating")));
                book.setBookImage(resultSet.getString("book_image"));

                books.add(book);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public Books getBookById(int bookId) throws SQLException {
        String query = "SELECT * FROM Books b, category c WHERE b.fk_category_id=c.category_id and Book_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, bookId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Books book = new Books();
            book.setBookId(resultSet.getInt("Book_id"));
            book.setCategoryId(resultSet.getInt("fk_Category_id"));
            book.setBookISBN(resultSet.getInt("Book_ISBN"));
            book.setBookTitle(resultSet.getString("Book_title"));
            book.setBookAuthor(resultSet.getString("Book_author"));
            book.setBookPrice(Double.parseDouble(resultSet.getString("book_price")));
            book.setBookPublisher(resultSet.getString("Book_publisher"));
            book.setBookRating(resultSet.getInt("Book_rating"));
            book.setBookImage(resultSet.getString("Book_image"));
            book.setCategoryName(resultSet.getString("category_name"));

            return book;
        }

        return null; // Book not found
    }
    public List<Books> getBooksByCategory(int categoryId) throws SQLException {
        List<Books> books = new ArrayList<>();

        String query = "SELECT * FROM books b,category c WHERE b.fk_category_id=c.category_id and fk_category_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Books book = new Books();
                    book.setCategoryName(resultSet.getString("category_name"));
                    book.setCategoryId(Integer.parseInt(resultSet.getString("category_id")));
                    book.setBookId(resultSet.getInt("book_id"));
                    book.setBookISBN(Integer.parseInt(resultSet.getString("book_isbn")));
                    book.setBookTitle(resultSet.getString("book_title"));
                    book.setBookAuthor(resultSet.getString("book_author"));
                    book.setBookPrice(Double.parseDouble(resultSet.getString("book_price")));
                    book.setBookPublisher(resultSet.getString("book_publisher"));
                    book.setBookRating(Integer.parseInt(resultSet.getString("book_rating")));
                    book.setBookImage(resultSet.getString("book_image"));

                    books.add(book);
                }
            }
        }

        return books;
    }





    public boolean addBook(Books book) throws SQLException {
        int book_id = getNextBookId();
        try (
                PreparedStatement statement = connection.prepareStatement("INSERT INTO books  VALUES (?, ?, ?, ?, ?, ?, ?,?,?)")) {

            statement.setInt(1, book_id);
            statement.setInt(2, book.getCategoryId());
            statement.setInt(3, book.getBookISBN());
            statement.setString(4, book.getBookTitle());
            statement.setString(5, book.getBookAuthor());
            statement.setDouble(6, book.getBookPrice());
            statement.setString(7, book.getBookPublisher());
            statement.setInt(8, book.getBookRating());
            statement.setString(9, book.getBookImage());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBookById(int book_id)throws SQLException {
        String sql = "DELETE FROM books WHERE Book_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Set the book ID parameter in the SQL statement
        statement.setInt(1, book_id);

        // Execute the SQL statement to delete the book
        statement.executeUpdate();
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;

    }
}
