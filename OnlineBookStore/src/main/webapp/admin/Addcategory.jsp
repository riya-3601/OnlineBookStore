<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Category</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            width: 300px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>
    <h1>Add Category</h1>


    <div id="categoryForm">
        <form action="addcategory" method="post" enctype="multipart/form-data">
            <label for="name">Category Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="image">Image:</label>
            <input type="file" id="image" name="image" required><br>

            <button type="submit">Add Category</button>
        </form>
    </div>
</body>
</html>
