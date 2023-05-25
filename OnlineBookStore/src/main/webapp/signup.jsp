<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            width: 400px;
            margin: 0 auto;
        }

        label {
            display: inline-block;
            width: 120px;
            margin-bottom: 10px;
        }

        input[type="email"],
        input[type="password"],
        input[type="text"],
        input[type="tel"],
        select {
            width: 220px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .error {
            color: red;
            font-size: 12px;
        }

        .password-toggle {
            background-color: #fff;
            border: none;
            color: #007bff;
            text-decoration: underline;
            cursor: pointer;
            padding: 0;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>

    <script>
        function togglePasswordVisibility(inputId, buttonId) {
            var passwordInput = document.getElementById(inputId);
            var passwordVisibilityToggle = document.getElementById(buttonId);

            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                passwordVisibilityToggle.textContent = "Hide";
            } else {
                passwordInput.type = "password";
                passwordVisibilityToggle.textContent = "Show";
            }
        }

        function validateForm() {
            // Clear previous error messages
            var errorElements = document.getElementsByClassName("error");
            for (var i = 0; i < errorElements.length; i++) {
                errorElements[i].innerText = "";
            }

            var valid = true;

            // Mobile Number validation
            var mobile = document.getElementById("mobile").value;
            var mobileError = document.getElementById("mobileError");
            var mobileRegex = /^\d+$/; // Regular expression to match digits only
                if (!mobile.match(mobileRegex)) {
                    mobileError.innerText = "Mobile number should only contain digits.";
                    valid = false;
                } else if (mobile.length !== 10) {
                    mobileError.innerText = "Mobile number should be 10 digits.";
                    valid = false;
                }
            // Name validation
            var name = document.getElementById("name").value;
            var nameError = document.getElementById("nameError");
            var nameRegex = /^[A-Za-z]+$/; // Regular expression to match letters only
            if (!name.match(nameRegex)) {
                nameError.innerText = "Name should only contain letters.";
                valid = false;
            } else if (name.length > 20) {
                nameError.innerText = "Name should not exceed 20 characters.";
                valid = false;
            }

            // Confirm Password validation
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var confirmPasswordError = document.getElementById("confirmPasswordError");
            if (password !== confirmPassword) {
                confirmPasswordError.innerText = "Passwords do not match.";
                valid = false;
            }

            if (!valid) {
                return false; // Prevent form submission if there are validation errors
            } else {
                // All fields are satisfied, submit the form
                return true;
            }
        }

    </script>
</head>


<body>
    <h1>Signup</h1>
    <%-- Display error message if exists --%>
        <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
        <% } %>
    <form method="post" action="register" onsubmit="return validateForm()">
        <label>Email ID:</label>
        <input type="email" name="email" required><br><br>

        <label>Password:</label>
        <input type="password" name="password" id="password" required>
        <button type="button" id="passwordVisibilityToggle" onclick="togglePasswordVisibility()">Show</button><br>

        <label>Confirm Password:</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required>
        <span class="error" id="confirmPasswordError"></span>
        <button type="button" id="confirmpasswordVisibilityToggle" onclick="togglePasswordVisibility()">Show</button><br>

        <label>Name:</label>
        <input type="text" name="name" id="name" required>
        <span class="error" id="nameError"></span><br>

        <label>Gender:</label>
        <select name="gender" required>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select><br><br>

        <label>Mobile Number:</label>
        <input type="text" name="mobile" id="mobile" required>
        <span class="error" id="mobileError"></span><br>

        <label>Address</label>
         <input type="text" name="address" id="address" required>

        <input type="submit" value="Signup">
        <p> <a href="index.jsp">Log in</a></p>
    </form>
</body>
</html>

