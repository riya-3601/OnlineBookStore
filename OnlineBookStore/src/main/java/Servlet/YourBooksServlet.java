package Servlet;

import Domain.Books;
import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/yourbook")
public class YourBooksServlet extends HttpServlet {
    private AdminService adminService;

    public void init() {
        adminService = new AdminService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Books> books;
        books = adminService.getAllBooks();

        request.setAttribute("books", books);
        request.getRequestDispatcher("admin/YourBooks.jsp").forward(request, response);
    }
}
