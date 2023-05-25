<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Category" %>
<html>
<head>
    <title>Category List</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
    <h1>Category List</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>

        </tr>
        <tr>
            <% List<Category> categories = (List<Category>) request.getAttribute("categories");
               if (categories != null) {
                   for (Category cat : categories) {
            %>
            <td><%= cat.getCategoryId() %></td>
            <td><%= cat.getCategoryName() %></td>


            <td>
                <form action="DeleteCategory" method="post">
                    <input type="hidden" name="categoryId" value="<%= cat.getCategoryId() %>">
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
