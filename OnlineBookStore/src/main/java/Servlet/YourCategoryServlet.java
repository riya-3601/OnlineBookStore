package Servlet;

import Domain.Books;
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

@WebServlet("/yourcategory")
public class YourCategoryServlet extends HttpServlet {
    private AdminService adminService;

    public void init() {
        adminService = new AdminService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories;
        try {
            categories = adminService.getAllCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("admin/YourCategory.jsp").forward(request, response);
    }
}
