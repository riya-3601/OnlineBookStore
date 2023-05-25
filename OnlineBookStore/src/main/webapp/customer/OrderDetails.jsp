<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.OrderDetails" %>
<html>
<head>
    <title>Order Details</title>
    <style>
        /* Styling for the Order Details page */

        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
        }

        #order {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        #order th, #order td {
            padding: 10px;
            text-align: center;
        }

        #order th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        #order tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #order tr:hover {
            background-color: #e6e6e6;
        }

    </style>
</head>
<body>
<%@ include file="navBar.jsp" %>
    <h1>Order Details</h1>
    <table id="order">
        <thead>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
        <% List<OrderDetails> orderdetails = (List<OrderDetails>) request.getAttribute("orderdetails");
            if (orderdetails != null && !orderdetails.isEmpty()) {
                for (OrderDetails orderdetail : orderdetails) {
        %>
        <tr>
            <td><%= orderdetail.getBookName() %></td>
            <td><%= orderdetail.getQuantity() %></td>
            <td><%= orderdetail.getBookPrice() %></td>
        </tr>
        <% }
            } %>
        </tbody>
    </table>
</body>
</html>
