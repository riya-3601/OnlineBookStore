package Servlet;

import Domain.Books;
import Domain.Category;
import Domain.Customer;
import Service.AdminService;
import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customerhome")
public class CustomerHomeServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Books> books = CustomerService.getAllBooks();
        AdminService adminService = new AdminService();

        List<Category> categories = new ArrayList<>();
        try {
            categories = adminService.getAllCategories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        int id=customer.getid();
        Customer customer1 = null;
        try {
            customer1 = customerService.getCustomerById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("categories", categories);
        request.setAttribute("customer", customer1);


        request.setAttribute("books", books);
        request.getRequestDispatcher("customer/Customerhome.jsp").forward(request, response);
    }
}
