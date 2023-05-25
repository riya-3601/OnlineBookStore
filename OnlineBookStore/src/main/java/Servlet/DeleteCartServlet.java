package Servlet;


import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/deletecart")
public class DeleteCartServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("cartId"));
        try {
            customerService.deleteCartItem(cartItemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("Cart"); // Redirect back to the cart page
    }
}