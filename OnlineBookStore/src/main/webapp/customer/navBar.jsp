<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Books" %>
<%@ page import="Domain.Category" %>
<%@ page import="Domain.Customer" %>
<html>
<head>
    <title>Customer Home</title>
    <link rel="stylesheet" type="text/css" href="css/CustomerHome.css">
</head>
<body>
<!-- Sidebar -->
    <div class="w3-sidebar w3-bar-block w3-border-right" style="display:none" id="mySidebar">
      <button onclick="w3_close()" class="w3-bar-item w3-large">Close &times;</button>
      <ul>
                      <%-- Retrieve the list of categories from your data source --%>
                      <% List<Category> categories = (List<Category>) request.getAttribute("categories");
                         if (categories != null && !categories.isEmpty()) {
                             for (Category category : categories) { %>
                                 <li>
                                     <a href="Bookbycategory?categoryId=<%= category.getCategoryId() %>"><%= category.getCategoryName() %></a>
                                 </li>
                             <% }
                         } %>
                  </ul>
    </div>


</div>
<ul>
    <li style="color: white;">
          <button class="w3-button  w3-xlarge" onclick="w3_open()">☰</button>
</li>
    <li><a class="active" href="customerhome">Book Store</a></li>
    <li><a style="float:right" href="Cart">Cart</a></li>
    <li><a style="float:right" href="myorders">My Orders</a></li>
    <li><a style="float:right" href="yourprofile">Profile</a></li>

    <li style="float:right"><a href="logout">Logout</a></li>
</ul>
</body>
</html>
