package Servlet;

import Domain.Books;
import Domain.Category;
import Domain.Customer;
import Service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
@WebServlet("/AdminHome")
public class AdminHomeServlet extends HttpServlet {
    private AdminService adminService;

    public void init() {
        // Initialize the adminService object
        adminService = new AdminService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("AdminHomeServlet doGet method called");
//        // Rest of the code
//        List<Books> books = adminService.getAllBooks();
//        request.setAttribute("books", books);
//        System.out.println(request.getAttribute("books"));

        request.getRequestDispatcher("admin/AdminHome.jsp").forward(request, response);


    }
}
