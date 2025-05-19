<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${vendor == null ? 'Add Vendor' : 'Update Vendor'}</title>
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
        input[type="text"], input[type="number"] {
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
    <h2>${vendor == null ? 'Add Vendor' : 'Update Vendor'}</h2>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <form action="adminvendors" method="post">
        <input type="hidden" name="action" value="${vendor == null ? 'add' : 'edit'}">
        <c:if test="${vendor != null}">
            <input type="hidden" name="oldName" value="${vendor.name}">
        </c:if>
        <label>Vendor Name:</label>
        <input type="text" name="name" value="${vendor != null ? vendor.name : ''}" required>
        <label>Category:</label>
        <input type="text" name="category" value="${vendor != null ? vendor.category : ''}" required>
        <label>Revenue:</label>
        <input type="number" name="price" step="0.01" value="${vendor != null ? vendor.price : ''}" required>
        <label>Contact Email:</label>
        <input type="text" name="contact" value="${vendor != null ? vendor.contact : ''}" required>
        <input type="submit" value="${vendor == null ? 'Add Vendor' : 'Update Vendor'}">
        <a href="adminvendors">Back to Home</a>
    </form>
</div>
</body>
</html>