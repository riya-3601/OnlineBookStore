<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Order" %>

<html>
<head>
    <title>Admin Orders</title>
    <!-- Add your CSS styling here -->
    <style>
        /* Styling for the My Orders page */

        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
        }

        #orders {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        #orders th, #orders td {
            padding: 10px;
            text-align: center;
        }

        #orders th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        #orders tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #orders tr:hover {
            background-color: #e6e6e6;
        }

        </style>
</head>
<body>
<%@ include file="navbar.jsp" %>
    <%-- Include your navigation bar here --%>

    <h1>All Orders</h1>

    <table id="orders">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Customer Name</th>

                <th>Total</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%-- Iterate over the order list and display the order details --%>
            <% List<Order> orders = (List<Order>) request.getAttribute("orders");
               if (orders != null && !orders.isEmpty()) {
                   for (Order order : orders) {
            %>
            <tr>
                <td><%= order.getOrderId() %></td>
                <td><%= order.getOrderDate() %></td>
                <td><%= order.getCustomerName() %></td>
                <td><%= order.getOrderTotal() %></td>
                <td><%= order.getOrderStatus() %></td>
                <td>
                                    <form method="POST" action="updateorderstatus">
                                        <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                                        <select name="status">
                                            <option value="Placed" <% if (order.getOrderStatus().equals("Placed")) { %>selected<% } %>>Placed</option>
                                            <option value="Processing" <% if (order.getOrderStatus().equals("Processing")) { %>selected<% } %>>Processing</option>
                                            <option value="Completed" <% if (order.getOrderStatus().equals("Completed")) { %>selected<% } %>>Completed</option>
                                        </select>
                                        <button type="submit">Update</button>
                                    </form>
                                </td>
            </tr>
            <% }
               } %>
        </tbody>
    </table>
</body>
</html>
