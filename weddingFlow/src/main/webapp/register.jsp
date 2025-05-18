<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Register - WeddingFlow</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
  <h2>Register</h2>
  <form action="register" method="post">
    <label>Username:</label>
    <input type="text" name="username" required><br>
    <label>Password:</label>
    <input type="password" name="password" required><br>
    <label>User Type:</label>
    <select name="userType" required>
      <option value="Basic">Basic</option>
      <option value="Guest">Guest</option>
    </select><br>
    <button type="submit">Register</button>
  </form>
  <p><a href="login.jsp">Login</a></p>
  <% if (request.getAttribute("error") != null) { %>
  <p style="color: red;"><%= request.getAttribute("error") %></p>
  <% } %>
</div>
</body>
</html>