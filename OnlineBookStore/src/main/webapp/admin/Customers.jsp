<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Customer" %>
<html>
<head>
    <title>All Customers</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>
    <h1>All Customers</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Gender<th>
            <th>Phone</th>
        </tr>
        <% List<Customer> customers = (List<Customer>) request.getAttribute("customers");
           if (customers != null) {
               for (Customer customer : customers) {
        %>
        <tr>
            <td><%= customer.getid() %></td>
            <td><%= customer.getName() %></td>
            <td><%= customer.getemailid() %></td>
            <td><%= customer.getGender() %></td>
            <td><%= customer.getMobile() %></td>
        </tr>
        <%   }
           }
        %>
    </table>
</body>
</html>
