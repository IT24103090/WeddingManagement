<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard - WeddingFlow</title>
  <link rel="stylesheet" href="css/styles.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
  <style>
    .container {
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
      font-family: 'Poppins', sans-serif;
    }
    .container h2 {
      font-size: 2rem;
      font-weight: 600;
      text-align: center;
      margin-bottom: 20px;
    }
    .container h3 {
      font-size: 1.5rem;
      font-weight: 500;
      margin-top: 20px;
      margin-bottom: 10px;
    }
    .user-info {
      display: grid;
      grid-template-columns: max-content 1fr;
      gap: 10px 20px;
      padding: 15px;
      border-radius: 5px;
      margin-bottom: 20px;
    }
    .user-info p {
      font-size: 1rem;
      margin: 0;
      line-height: 1.6;
    }
    .user-info .label {
      font-weight: 500;
      text-align: right;
    }
    .dashboard-options {
      display: flex;
      flex-wrap: wrap;
      gap: 10px;
      justify-content: center;
      margin-top: 20px;
    }
    .dashboard-options form {
      display: inline-block;
    }
    .dashboard-options button {
      padding: 10px 20px;
      font-size: 1rem;
      font-family: 'Poppins', sans-serif;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
  </style>
</head>
<body>
<header>
  <h1>WeddingFlow</h1>
  <nav>
    <a href="index.jsp">Home</a>
    <a href="vendors">Vendors</a>
    <a href="contact">Contact</a>
    <a href="aboutus.jsp">About</a>
    <% if (session.getAttribute("username") != null) { %>
    <a href="dashboard"><img src="images/user.png" alt="Account" class="account-logo"></a>
    <% } %>
  </nav>
</header>
<div class="container">
  <h2>Welcome to WeddingFlow</h2>
  <h3>User Information</h3>
  <div class="user-info">
    <p class="label">Username:</p>
    <p>${sessionScope.username}</p>
    <p class="label">User Type:</p>
    <p>${sessionScope.userType}</p>
    <p class="label">Password:</p>
    <p>********</p>
  </div>
  <h3>Dashboard Options</h3>
  <div class="dashboard-options">
    <form action="orderDetails" method="get">
      <button type="submit">View Order Details</button>
    </form>
    <form action="paymentDetails" method="get">
      <button type="submit">View Payment Details</button>
    </form>
    <form action="index.jsp" method="get">
      <button type="submit">Go to Home</button>
    </form>
    <form action="deleteAccount" method="post">
      <button type="submit" class="delete-button">Delete Account</button>
    </form>
    <form action="updateDetails" method="get">
      <button type="submit">Update Details</button>
    </form>
  </div>
</div>
</body>
</html>