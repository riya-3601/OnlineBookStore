<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Admin Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        h2 {
            margin-bottom: 10px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-bottom: 10px;
        }

        button:hover {
            background-color: #45a049;
        }

        p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>
    <h1>Welcome, Admin!</h1>

    <h2>Manage Books</h2>
     <form action="yourbook" method="get">
            <button type="submit">Books</button>
     </form>
    <form action="getcategory" method="get">
        <button type="submit">Add Book</button>
    </form>


    <h2>Manage Categories</h2>

    <form action="yourcategory" method="get">
            <button type="submit">Categories</button>
    </form>
    <form action="addcategory" method="get">
        <button type="submit">Add Category</button>
    </form>


    <h2>Customers</h2>
    <form action="AllCustomers" method="get">
        <button type="submit">Manage Customers</button>
    </form>

    <h2>Orders</h2>
    <form action="yourorder" method="get">
        <button type="submit">Manage Orders</button>
    </form>
</body>
</html>
