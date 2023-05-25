package Servlet;

import DAO.BookDAO;
import Domain.Books;
import Domain.Cart;
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

@WebServlet("/placeorder")
public class PlaceOrderServlet extends HttpServlet {
    private CustomerService customerService;
    private AdminService adminService;
    private BookDAO bookDAO;


    public void init() throws ServletException {
        customerService = new CustomerService();
        bookDAO = new BookDAO();
        adminService = new AdminService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the cart items from the session
        List<Cart> cartItems = (List<Cart>) request.getSession().getAttribute("cartItems");
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int customerId = customer.getid();
        boolean placeordersuccesful = false;
        try {
            placeordersuccesful = customerService.placeorder(cartItems,customerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (placeordersuccesful) {
            // Show success popup message
            String message = "Order Placed Successfully!";
            System.out.println(message);
            request.setAttribute("message", message);
            response.sendRedirect("Cart");

        } else {
            // Show error popup message
            String message = "Order not placed";
            request.setAttribute("message", message);
        }
        cartItems.clear();

//        request.getRequestDispatcher("customer/OrderConfirmation.jsp").forward(request, response);

        // Clear the cart after placing the order

    }





}

