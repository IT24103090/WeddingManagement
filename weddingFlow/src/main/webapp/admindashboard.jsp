<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>WeddingFlow Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        body {
            background: url('images/bg1.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Arial', sans-serif;
        }
        .container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            padding: 2rem;
            max-width: 600px;
            margin: 5rem auto;
            transition: transform 0.3s ease-in-out;
        }
        .container:hover {
            transform: translateY(-5px);
        }
        .btn {
            background-color: #d4af37;
            color: white;
            padding: 0.75rem 1.5rem; /* Reduced padding for smaller size */
            border-radius: 8px;
            font-size: 1rem; /* Reduced font size */
            font-weight: 600;
            transition: background-color 0.3s ease, transform 0.2s ease;
            width: auto; /* Changed to auto to fit content */
            text-align: center;
            margin-bottom: 1rem;
            display: inline-block; /* Ensure it behaves like a button */
        }
        .btn:hover {
            background-color: #b8972e;
            transform: scale(1.05);
        }
        .btn:focus {
            outline: none;
            ring: 2px solid #b8972e;
        }
        .header {
            color: #1a202c;
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 2rem;
            text-align: center;
        }
        .button-container {
            display: flex;
            justify-content: center;
            gap: 1rem; /* Add spacing between buttons */
            flex-wrap: wrap; /* Allow wrapping on smaller screens */
        }
        @media (max-width: 768px) {
            .button-container {
                flex-direction: column;
                gap: 0.75rem;
            }
            .btn {
                width: 100%; /* Full width on smaller screens */
            }
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="container">
    <h1 class="header">WeddingFlow Admin Dashboard</h1>
    <div class="button-container">
        <a href="adminvendors" class="btn">Vendor Management</a>
        <a href="users" class="btn">User Management</a>
    </div>
</div>
</body>
</html>