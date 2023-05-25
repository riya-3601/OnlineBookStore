<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Cart" %>

<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" type="text/css" href="css/Cart.css">
</head>
<body>
<%@ include file="navBar.jsp" %>
<h1>Your Cart</h1>

<% List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");

   if (cartItems != null && !cartItems.isEmpty()) {
%>
<table id="cart">
    <thead>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <%-- Iterate over the cart items and display the data --%>
        <% for (Cart item : cartItems) { %>
        <tr>
            <td><%= item.getBookTitle() %></td>
            <td><%= item.getBookPrice() %></td>
            <td>
                <input type="number" class="quantity-input" value="<%= item.getQuantity() %>" min="1">
            </td>
            <td><%= item.getQuantity() %></td>
            <td>
                <form action="deletecart" method="post">
                    <input type="hidden" name="cartId" value="<%= item.getCartId() %>">
                    <button class="delete-btn" type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% }
        %>
    </tbody>
</table>
<h6>Total Price:<%= request.getAttribute("total") %></h6>
<form action="placeorder" method="post">
    <input type="submit" value="Place Order">
</form>
<% } else { %>
<p>Sorry, Your cart is empty.</p>
<% } %>
</body>
</html>
