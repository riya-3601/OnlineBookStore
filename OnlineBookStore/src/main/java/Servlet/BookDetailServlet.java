package Servlet;


import DAO.BookDAO;
import Domain.Books;
import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookdetail")
public class BookDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the bookId parameter from the request
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        // Create an instance of CustomerService and BookDAO
        CustomerService customerService = new CustomerService();

        try {
            // Get the book details using the BookDAO
            Books books = CustomerService.getBookById(bookId);

            // Add the book object to the request attributes
            request.setAttribute("books", books);

            // Forward the request to viewdetails.jsp
            request.getRequestDispatcher("customer/ViewBookDetail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that occur during book retrieval
            // You can redirect to an error page or display an error message
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}