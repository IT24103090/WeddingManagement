<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${user == null ? 'Add User' : 'Update User'}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: url('images/bg1.png') no-repeat center center fixed;
            background-size: cover;
        }
        .container {
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 10px;
            max-width: 400px;
            margin: auto;
        }
        .error {
            color: red;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"], select {
            width: 100%;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-bottom: 10px;
        }
        input[type="submit"], a {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }
        input[type="submit"] {
            background-color: #d4af37;
            color: white;
        }
        a {
            background-color: #6c757d;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>${user == null ? 'Add User' : 'Update User'}</h2>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <form action="users" method="post">
        <input type="hidden" name="action" value="${user == null ? 'add' : 'edit'}">
        <c:if test="${user != null}">
            <input type="hidden" name="oldUsername" value="${user.username}">
        </c:if>
        <label>Username:</label>
        <input type="text" name="username" value="${user != null ? user.username : ''}" required>
        <label>Password:</label>
        <input type="text" name="password" value="${user != null ? user.password : ''}" required>
        <label>User Type:</label>
        <select name="userType" required>
            <option value="Admin" ${user != null && user.userType == 'Admin' ? 'selected' : ''}>Admin</option>
            <option value="Basic" ${user != null && user.userType == 'Basic' ? 'selected' : ''}>Basic</option>
            <option value="Guest" ${user != null && user.userType == 'Guest' ? 'selected' : ''}>Guest</option>
        </select>
        <input type="submit" value="${user == null ? 'Add User' : 'Update User'}">
        <a href="users">Back to Home</a>
    </form>
</div>
</body>
</html>