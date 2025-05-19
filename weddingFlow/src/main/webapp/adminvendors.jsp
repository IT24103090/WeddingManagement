<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Vendor List - WeddingFlow</title>
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
            max-width: 800px;
            margin: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #d4af37;
            color: white;
        }
        .action-buttons input[type="submit"] {
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 5px;
        }
        .action-buttons input[type="submit"][value="Update"] {
            background-color: #28a745;
            color: white;
        }
        .action-buttons input[type="submit"][value="Delete"] {
            background-color: #dc3545;
            color: white;
        }
        .add-button, .back-button {
            background-color: #d4af37;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin: 10px 5px;
            transition: all 0.3s ease;
        }
        .back-button {
            background-color: #6c757d;
        }
        .add-button:hover, .back-button:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }
        .search-sort {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .search-sort input[type="text"], .search-sort select {
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        .vendor-actions {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 20px;
        }
        @media (max-width: 768px) {
            .search-sort {
                flex-direction: column;
                gap: 10px;
            }
            .vendor-actions {
                flex-direction: column;
                gap: 10px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Vendor List</h2>
    <div class="search-sort">
        <form action="adminvendors" method="get">
            <input type="text" name="search" placeholder="Search vendor..." value="${search}">
        </form>
        <form action="adminvendors" method="get">
            <label>Sort by:</label>
            <select name="sortBy" onchange="this.form.submit()">
                <option value="">Revenue: Low to High</option>
                <option value="revenueAsc" ${sortBy == 'revenueAsc' ? 'selected' : ''}>Revenue: Low to High</option>
                <option value="revenueDesc" ${sortBy == 'revenueDesc' ? 'selected' : ''}>Revenue: High to Low</option>
            </select>
        </form>
    </div>
    <table>
        <tr>
            <th>Vendor Name</th>
            <th>Category</th>
            <th>Revenue</th>
            <th>Contact Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="vendor" items="${vendors}">
            <tr>
                <td>${vendor.name}</td>
                <td>${vendor.category}</td>
                <td>${vendor.price}</td>
                <td>${vendor.contact}</td>
                <td class="action-buttons">
                    <form action="adminvendors" method="get" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="name" value="${vendor.name}">
                        <input type="submit" value="Update">
                    </form>
                    <form action="adminvendors" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="name" value="${vendor.name}">
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure?');">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="vendor-actions">
        <a href="admindashboard.jsp" class="back-button">Back to Dashboard</a>
        <a href="adminvendors?action=add" class="add-button">Add a New Vendor</a>
    </div>
</div>
</body>
</html>