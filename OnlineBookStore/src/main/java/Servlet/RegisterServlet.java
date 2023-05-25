package Servlet;



import DAO.CustomerDao;
import Domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data

        String email = request.getParameter("email");

        // Check if email is unique
       CustomerDao customerDAO = new CustomerDao();
        boolean isEmailUnique = false;
        try {
            isEmailUnique = customerDAO.isEmailUnique(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isEmailUnique) {
            String emailid = request.getParameter("email");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String mobileno = request.getParameter("mobile");
            String address=request.getParameter("address");

            System.out.println(emailid);

            // Create a new user object
            Customer customer = new Customer();
            customer.setemailid(emailid);
            customer.setPassword(password);
            customer.setName(name);
            customer.setGender(gender);
            customer.setMobile(mobileno);
            customer.setCustomer_address(address);

            // Call the UserDao to register the user
            CustomerDao customerDao = new CustomerDao();
            boolean success = CustomerDao.registerUser(customer);

            // Redirect to appropriate page based on registration result
            if (success) {

                response.sendRedirect("index.jsp");
            }
        } else {
            // Email ID already exists, display an error message
            request.setAttribute("errorMessage", "Email ID already exists. Please choose a different email.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
