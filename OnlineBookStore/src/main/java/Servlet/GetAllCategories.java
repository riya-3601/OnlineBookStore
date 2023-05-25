package Servlet;


import Domain.Category;
import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/getcategory")
public class GetAllCategories extends HttpServlet {
    //private BookService bookService;

    AdminService adminService;

    public void init() {
        // Initialize the bookService object
        // bookService = new BookService();
        adminService = new AdminService();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService adminService = new AdminService();
        List<Category> categories = null;
        try {
            categories = adminService.getAllCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("admin/AddBook.jsp").forward(request, response);
    }
}
