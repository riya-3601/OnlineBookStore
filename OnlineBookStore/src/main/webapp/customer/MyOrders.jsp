<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Order" %>

<html>
<head>
    <title>My Orders</title>
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
<%@ include file="navBar.jsp" %>
    <h1>My Orders</h1>

    <table id="orders">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Date</th>
                <th>Total</th>
                <th>Status</th>
                <th>View Order</th>
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
            <td><%= order.getOrderTotal() %></td>
            <td><%= order.getOrderStatus() %></td>
            <td>
                        <form method="get" action="orderdetails">
                            <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                            <button class="order-button" type="submit">View Details</button>
                        </form>
                    </td>
        </tr>
        <% }
            }
        %>
        </tbody>
    </table>
</body>
</html>
