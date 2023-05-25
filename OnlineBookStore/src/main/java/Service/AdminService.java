package Service;

import DAO.BookDAO;
import DAO.CategoryDAO;
import DAO.CustomerDao;
import DAO.OrderDAO;
import Domain.Books;
import Domain.Category;
import Domain.Customer;
import Domain.Order;

import java.sql.SQLException;
import java.util.List;

public class AdminService {

        private static BookDAO bookDAO;
        private static  CategoryDAO categoryDAO;

        private static CustomerDao customerDao;

        private static OrderDAO orderDAO;

        public AdminService() {
            bookDAO = new BookDAO();
            categoryDAO=new CategoryDAO();
            customerDao=new CustomerDao();
            orderDAO=new OrderDAO();
        }

        public static List<Books> getAllBooks() {
            return bookDAO.getAllBooks();
        }

        public static List<Category> getAllCategories() throws SQLException {
            return categoryDAO.getAllCategories();
        }

        public static List<Customer> getAllCustomers() throws SQLException {
            return customerDao.getAllCustomers();
        }

        public static List<Order> getAllOrders() throws SQLException{
            return orderDAO.getAllOrders();
        }


    public static boolean addBook(Books book) throws SQLException {
            return bookDAO.addBook(book);
        }

        public static boolean addCategory(Category category) throws SQLException {
            return CategoryDAO.addCategory(category);
        }

        public static boolean updateOrderStatus(int orderId,String newStatus)throws SQLException {
            return orderDAO.updateOrderStatus(orderId,newStatus);
        }
        public boolean deleteBook(int bookId) throws SQLException {
            // Perform the necessary operations to delete the book with the given bookId from the database
            // For example:
            return bookDAO.deleteBookById(bookId);
        }
    public boolean deleteCategory(int categoryId) throws SQLException {
        // Perform the necessary operations to delete the book with the given bookId from the database
        // For example:
        return CategoryDAO.deleteCategoryById(categoryId);
    }
}
