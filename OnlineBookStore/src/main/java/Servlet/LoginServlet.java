package Servlet;

import DAO.LoginDao;
import Domain.Category;
import Domain.Customer;
import Service.AdminService;
import Service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    Customer customer = null;


    public void init() {
        loginService = new LoginService(new LoginDao());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            boolean loginSuccessful = loginService.login(username, password);
            boolean isAdmin=loginService.isAdmin(username,password);
            Customer customer = loginService.getCustomerDetails(username,password);




            if (loginSuccessful) {
                if(isAdmin){
                    response.sendRedirect("AdminHome");
                }
                else {

                    request.getSession().setAttribute("customer", customer);

                    response.sendRedirect("customerhome");
                }
            } else {
                String errorMessage = "Invalid username or password.";
                request.getSession().setAttribute("errorMessage", errorMessage);
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
        }
    }
}
