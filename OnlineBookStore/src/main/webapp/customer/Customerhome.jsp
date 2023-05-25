<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Books" %>
<%@ page import="Domain.Category" %>

<html>
<head>
    <title>Customer Home</title>
    <link rel="stylesheet" type="text/css" href="css/CustomerHome.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <body>
    <style>
    /* Sidebar container */
    .sidebar {
      width: 250px;
      background-color: #f1f1f1;
      height: 100%; /* Adjust the height as needed */
      position: fixed;
      top: 0;
      left: 0;
      overflow-x: hidden;
      padding-top: 20px;
    }

    /* Sidebar links */
    .sidebar a {
      display: block;
      color: black;
      padding: 16px;
      text-decoration: none;
    }

    /* Change the color of the active link */
    .sidebar a.active {
      background-color: #4CAF50;
      color: white;
    }

    /* Sidebar on small screens */
    @media screen and (max-height: 450px) {
      .sidebar {
        padding-top: 15px;
      }
      .sidebar a {
        font-size: 18px;
      }
    }

    </style>
</head>

<body>


    <%@ include file="navBar.jsp" %>
    <div class="book-container">
        <%
            List<Books> books = (List<Books>) request.getAttribute("books");
            if (books != null && !books.isEmpty()) {
                for (Books book : books) {
        %>
            <div class="book-tile">
                <img class="card-img-top" style="max-height: 200px;max-width:100%;width:auto" src="./assets/images/books/<%= book.getBookImage() %>" >

                <h3><%= book.getBookTitle() %></h3>
                <p><%= book.getBookAuthor() %></p>
                <p>Price: <%= book.getBookPrice() %></p>
                <p>Category: <%= book.getCategoryName() %></p>
                <form action="Addtocart" method="post">
                    <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                    <input type="submit" value="Add to Cart" ">
                </form>
                <form action="bookdetail" method="get">
                    <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                    <input type="submit" value="View Details">
                </form>
            </div>
        <% }
            }
        %>
    </div>



    <script>


        function w3_open() {
          document.getElementById("mySidebar").style.display = "block";
        }

        function w3_close() {
          document.getElementById("mySidebar").style.display = "none";
        }

    </script>
</body>
</html>
