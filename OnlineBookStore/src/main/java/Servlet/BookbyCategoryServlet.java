package Servlet;

import Domain.Books;
import Domain.Category;
import Service.AdminService;
import Service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Bookbycategory")
public class BookbyCategoryServlet extends HttpServlet {
    private CustomerService customerService;

    public void init() {
        // Initialize the bookService object
        customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the categoryId parameter from the request
        String categoryId = request.getParameter("categoryId");

        AdminService adminService = new AdminService();

        List<Category> categories = new ArrayList<>();
        List<Books> books = null;
        try {
            books = customerService.getBooksByCategory(Integer.parseInt(categoryId));
            categories = adminService.getAllCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Set the books as a request attribute
        request.setAttribute("books", books);
        request.setAttribute("categories", categories);

        // Forward the request to the Customer Home page or any other page to display the books
        request.getRequestDispatcher("customer/Customerhome.jsp").forward(request, response);
    }
}
