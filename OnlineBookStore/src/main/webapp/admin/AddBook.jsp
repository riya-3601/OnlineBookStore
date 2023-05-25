<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Category "%>
<html>
<head>
    <title>Add Book</title>

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

        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        select {
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
    <h1>Add Book</h1>

    <div id="bookForm" >
        <form action="AddBook" method="post" enctype="multipart/form-data">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>

            <label for="author">Author:</label>
            <input type="text" id="author" name="author" required>

            <label for="isbn">ISBN:</label>
            <input type="number" id="isbn" name="isbn" required>


            <label for="price">Price:</label>
            <input type="number" id="price" name="price" min="0" step="0.01" required>

            <label for="publisher">Publisher:</label>
            <input type="text" id="publisher" name="publisher"  required>
            <label for="rating">Ratings:</label>
            <input type="number" id="rating" name="rating" min="0" max="5"required>


             <label for="image">Image:</label>
                    <input type="file" id="image" name="image" required><br>
            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <% List<Category> categories = (List<Category>) request.getAttribute("categories");
                   if (categories != null) {
                       for (Category category : categories) {
                %>
                <option value="<%= category.getCategoryId() %>"><%= category.getCategoryName() %></option>
                <%   }
                   }
                %>

            </select>
            <input type="hidden" id="categoryId" name="categoryId">

            <button type="button" onclick="submitForm()">Add Book</button>
        </form>
    </div>
    <script>
        function submitForm() {
            var categorySelect = document.getElementById('category');
            var categoryIdInput = document.getElementById('categoryId');

            // Set the selected category ID to the hidden input field
            categoryIdInput.value = categorySelect.value;

            // Submit the form
            document.forms[0].submit();
        }
    </script>
</body>
</html>
