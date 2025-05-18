<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login - WeddingFlow</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
  <h2>Login</h2>
  <form action="login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required><br>
    <label>Password:</label>
    <input type="password" name="password" required><br>
    <button type="submit">Login</button>
  </form>
  <p><a href="register.jsp">Register</a></p>
  <% if (request.getAttribute("error") != null) { %>
  <p style="color: red;"><%= request.getAttribute("error") %></p>
  <% } %>
</div>
</body>
</html>