package Servlet;

import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
    private AdminService adminService;

    public void init() {
        adminService = new AdminService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        try {
            adminService.deleteCategory(categoryId);
            // Set a success message in the request attribute
            request.setAttribute("message", "Book deleted successfully!");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        // Redirect back to the book list page
        response.sendRedirect("yourcategory");
    }
}