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
import java.sql.SQLException;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    private CustomerService customerService;
    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerService(); // Initialize the customerService object
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the updated address value from the request parameters
        String address = request.getParameter("address");

        // Retrieve the customer object from the session
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int id=customer.getid();

        // Update the customer's address
        //customer.setCustomer_address(address);

        // Update the customer in the database
        try {
            boolean succesfullyupdated=customerService.updateCustomer(id,address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Redirect back to the profile page
        response.sendRedirect("yourprofile");
    }
}
