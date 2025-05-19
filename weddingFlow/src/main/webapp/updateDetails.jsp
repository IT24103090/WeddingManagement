<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Details - WeddingFlow</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
    <h2>Update Details</h2>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="vendors">Vendors</a>
        <a href="contact">Contact</a>
        <a href="aboutus.jsp">About</a>
        <% if (session.getAttribute("username") != null) { %>
        <a href="dashboard"><img src="images/user.png" alt="Account" class="account-logo"></a>
        <% } %>
    </nav>
    <form action="updateDetails" method="post">
        <label>New Password:</label>
        <input type="password" name="newPassword" required><br>
        <button type="submit">Update</button>
    </form>
    <a href="dashboard.jsp">Back to Dashboard</a>
    <% if (request.getAttribute("error") != null) { %>
    <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
</div>
</body>
</html>