package Servlet;

import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteBook")
public class DeleteBookServlet extends HttpServlet{
    private AdminService adminService;

    public void init() {
        adminService = new AdminService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try {
            adminService.deleteBook(bookId);
            // Set a success message in the request attribute
            request.setAttribute("message", "Book deleted successfully!");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        // Redirect back to the book list page
        response.sendRedirect("yourbook");
    }
}
