package Servlet;

import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateorderstatus")
public class UpdateOrderStatusServlet extends HttpServlet {
    private AdminService adminService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String newStatus = request.getParameter("status");

        try {
            // Update the order status using the OrderDAO or a similar data access object

            boolean updated = adminService.updateOrderStatus(orderId, newStatus);

            if (updated) {
                // Order status updated successfully
                response.sendRedirect("yourorder");
            } else {
                // Failed to update order status
                // Handle the error appropriately
                // Redirect or show an error message
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            // Redirect or show an error message
        }
    }
}

