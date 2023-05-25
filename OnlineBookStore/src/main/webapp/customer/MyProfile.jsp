<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Domain.Customer" %>
<html>
<head>
    <title>Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 300px;
            margin: 0 auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
</head>
<body>
<%@ include file="navBar.jsp" %>
<h1>Profile</h1>

<table>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
    %>

    <form action="updateProfile" method="post">
        <tr>
            <th>Name:</th>
            <td><%= customer.getName() %></td>
        </tr>
        <tr>
            <th>Email:</th>
            <td><%= customer.getemailid() %></td>
        </tr>
        <tr>
            <th>Gender:</th>
            <td><%= customer.getGender() %></td>
        </tr>
        <tr>
            <th>Phone:</th>
            <td><%= customer.getMobile() %></td>
        </tr>
        <tr>
            <th>Address:</th>
            <td>
                <input type="text" name="address" value="<%= customer.getCustomer_address() %>">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Update">
            </td>
        </tr>
    </form>
</table>
</body>
</html>
