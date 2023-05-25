package Servlet;

import Domain.OrderDetails;
import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orderdetails")
public class OrderDetailsServlet extends HttpServlet {
    private CustomerService customerService;

    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        try {
            List<OrderDetails> orderdetails = customerService.getOrderDetailsByOrderID(orderId);
            request.setAttribute("orderdetails", orderdetails);
            request.getRequestDispatcher("customer/OrderDetails.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

