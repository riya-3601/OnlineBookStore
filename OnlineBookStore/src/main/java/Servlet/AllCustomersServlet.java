package Servlet;

import DAO.BookDAO;
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

@WebServlet("/AllCustomers")
public class AllCustomersServlet extends HttpServlet {


    private AdminService adminService;



    public void init() throws ServletException {

        adminService = new AdminService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Customer> customers = adminService.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("admin/Customers.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving customers", e);
        }
    }

}

