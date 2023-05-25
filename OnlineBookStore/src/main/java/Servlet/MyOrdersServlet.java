package Servlet;

import Domain.Customer;
import Domain.Order;
import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/myorders")
public class MyOrdersServlet extends HttpServlet {
    private CustomerService customerService;

    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the customer ID from the session
        Customer customer = ((Customer) request.getSession().getAttribute("customer"));
        int customerId=customer.getid();

        try {
            // Retrieve the orders for the customer
            List<Order> orders = customerService.getOrdersByCustomerId(customerId);

            // Set the orders as a request attribute
            request.setAttribute("orders", orders);

            // Forward the data to the myorders.jsp page
            request.getRequestDispatcher("customer/MyOrders.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
