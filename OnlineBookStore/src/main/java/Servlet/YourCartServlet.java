package Servlet;

import Domain.Cart;
import Domain.Customer;
import Service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Cart")
public class YourCartServlet extends HttpServlet {
    private CustomerService customerService;

    public void init() {
        customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the logged-in customer's ID from the session
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int customerId = customer.getid();

        // Retrieve the cart items for the customer from the service
        List<Cart> cartItems = customerService.getCartItemsByCustomerId(customerId);
        double totalPrice = 0.0;

        for (Cart cartItem : cartItems) {
            double price = cartItem.getBookPrice();
            totalPrice += price;
        }



        // Forward the cart items to the JSP for display
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("total", totalPrice);
        request.getSession().setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("customer/Cart.jsp").forward(request, response);
    }
}

