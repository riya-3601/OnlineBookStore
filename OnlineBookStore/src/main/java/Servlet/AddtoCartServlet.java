package Servlet;

import DAO.BookDAO;
import Domain.Books;
import Domain.Customer;
import Service.AdminService;
import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Addtocart")
public class AddtoCartServlet extends HttpServlet {

    private CustomerService customerService;
    private  AdminService adminService;
    private BookDAO bookDAO;


    public void init() throws ServletException {
        customerService = new CustomerService();
        bookDAO = new BookDAO();
        adminService = new AdminService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        Books book = null;
        try {
            book = CustomerService.getBookById(bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        try {
            boolean addtocartsuccesful = customerService.addToCart(customer, book);
            if (addtocartsuccesful) {
                // Show success popup message
                String message = "Item added to cart successfully!";
                System.out.println(message);
                request.setAttribute("message", message);
                List<Books> books = adminService.getAllBooks();
                request.setAttribute("books", books);
            } else {
                // Show error popup message
                String message = "Failed to add item to cart.";
                request.setAttribute("message", message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("customer/Customerhome.jsp").forward(request, response);
    }

}
