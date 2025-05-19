<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Details - WeddingFlow</title>
    <link rel="stylesheet" href="css/styles.css">
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
    <h2>Order Details</h2>
    <h3>User Information</h3>
    <p>Username: ${sessionScope.username}</p>
    <p>User Type: ${sessionScope.userType}</p>
    <h3>Your Orders</h3>
    <c:forEach var="booking" items="${bookings}">
        <p>Order ID: ${booking.orderId}</p>
        <p>Item: ${booking.item}</p>
        <p>Event Type: ${booking.eventType}</p>
        <p>Event Date: ${booking.eventDate}</p>
        <p>Quantity: ${booking.quantity}</p>
        <hr>
    </c:forEach>
    <c:if test="${empty bookings}">
        <p>No orders found.</p>
    </c:if>
    <a href="dashboard">Back to Dashboard</a>
</div>
</body>
</html>