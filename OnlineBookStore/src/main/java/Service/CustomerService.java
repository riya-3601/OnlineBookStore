package Service;

import DAO.BookDAO;
import DAO.CartDAO;
import DAO.CustomerDao;
import DAO.OrderDAO;
import Domain.*;

import java.sql.SQLException;
import java.util.List;


public class CustomerService {
    private static BookDAO bookDao;
    private  static CartDAO cartDAO;
    private static OrderDAO orderDAO;
    private static CustomerDao customerDAO;

    public CustomerService() {
        this.bookDao = new BookDAO();
        this.cartDAO=new CartDAO();
        this.orderDAO=new OrderDAO();
        this.customerDAO=new CustomerDao();

    }

    public static List<Books> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public static Books getBookById(int book_id) throws SQLException {return bookDao.getBookById(book_id);}

    public static Customer getCustomerById(int Customer_id ) throws SQLException {return customerDAO.getCustomerById(Customer_id);}

    public static List<Books> getBooksByCategory(int categoryId) throws SQLException {return bookDao.getBooksByCategory(categoryId);}

    public static List<Order> getOrdersByCustomerId(int customerId) throws SQLException {return orderDAO.getOrdersByCustomerId(customerId);}

    public static List<OrderDetails> getOrderDetailsByOrderID(int order_id) throws SQLException {return orderDAO.getOrderDetailsByOrderID(order_id);}

    public static boolean addToCart(Customer cust,Books book) throws SQLException {return cartDAO.addToCart(cust,book);}

    public static List<Cart> getCartItemsByCustomerId(int customer_id){return cartDAO.getCartItemsByCustomerId(customer_id);}

    public void deleteCartItem(int cartItemId) throws SQLException {
        cartDAO.deleteCartItem(cartItemId);
    }

    public boolean placeorder(List<Cart> cartItems,int customerId) throws SQLException {return orderDAO.placeorder(cartItems,customerId);}

    public boolean updateCustomer(int id,String address) throws SQLException {return customerDAO.updateCustomer(id,address);}
}


