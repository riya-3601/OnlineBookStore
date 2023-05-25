package Servlet;

import DAO.OrderDAO;
import Domain.Order;
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

@WebServlet("/yourorder")
public class YourOrderServlet extends HttpServlet {
    private AdminService adminService;

    public void init() {
        // Initialize the customerService object
        adminService = new AdminService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve all orders using the OrderDAO or a similar data access object

            List<Order> orders = adminService.getAllOrders();

            // Set the orders as a request attribute
            request.setAttribute("orders", orders);

            // Forward the request to the allOrders.jsp page
            request.getRequestDispatcher("admin/Orders.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            // Redirect or show an error message
        }
    }
}