<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Books" %>
<html>
<head>
    <title>Book Details</title>
</head>
<body>
<%@ include file="navBar.jsp" %>
    <h1>Book Details</h1>

    <div>
        <% Books book = (Books) request.getAttribute("books"); %>
        <% if (book != null) { %>
        <img class="card-img-top" style="max-height: 200px;max-width:100%;width:auto" src="./assets/images/books/<%= book.getBookImage() %>" >
            <h2>Title: <%= book.getBookTitle() %></h2>
            <p>Author: <%= book.getBookAuthor() %></p>
            <p>Price: <%= book.getBookPrice() %></p>
            <p>Category: <%= book.getCategoryName() %></p>
            <form action="Addtocart" method="post">
                                <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                                <input type="submit" value="Add to Cart" onclick="showPopup('<%= request.getAttribute("message") %>')">
                            </form>
        <% } else { %>
            <p>Book details not found.</p>
        <% } %>

    </div>

</body>
</html>








