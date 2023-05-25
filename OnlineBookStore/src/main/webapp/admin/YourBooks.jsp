<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Books" %>
<html>
<head>
    <title>Book List</title>
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
    <h1>Book List</h1>

    <table id="orders">
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Actions</th>
        </tr>
        <% List<Books> books = (List<Books>) request.getAttribute("books");
           if (books != null) {
               for (Books book : books) {
        %>
        <tr>
            <td><%= book.getBookTitle() %></td>
            <td><%= book.getBookAuthor() %></td>
            <td><%= book.getBookISBN() %></td>
            <td>
                <form action="DeleteBook" method="post">
                    <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <%   }
           }
        %>
    </table>
</body>
</html>
