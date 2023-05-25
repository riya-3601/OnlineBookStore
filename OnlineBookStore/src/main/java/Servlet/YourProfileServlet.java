package Servlet;

import Domain.Customer;
import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/yourprofile")
public class YourProfileServlet extends HttpServlet {
    private CustomerService customerService;

    public void init() {
        // Initialize the customerService object
        customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the logged-in customer's ID from the session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int id=customer.getid();


        try {
            // Retrieve the customer's details from the database
            Customer customer1 = customerService.getCustomerById(id);
            System.out.println(customer1.getid());
            System.out.println(customer1.getName());
            // Set the customer object as an attribute in the request
            request.setAttribute("customer", customer1);

            // Forward the request to the profile.jsp page
            request.getRequestDispatcher("customer/MyProfile.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving customer details", e);
        }
    }
}