<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Previous Bookings - WeddingFlow</title>
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
  <h2>Previous Booking Details</h2>
  <h3>Your Booking Information</h3>
  <p>Name: ${sessionScope.username}</p>
  <c:forEach var="booking" items="${bookings}">
    <p>Event Type: ${booking.eventType}</p>
    <p>Event Date: ${booking.eventDate}</p>
    <p>Selected Vendor: ${booking.item}</p>
    <p>Price: Rs.${booking.price}</p>
    <hr>
  </c:forEach>
  <p>Total Price: Rs.<%= request.getAttribute("bookings") != null ? ((java.util.List<com.weddingflow.model.Booking>)request.getAttribute("bookings")).stream().mapToDouble(com.weddingflow.model.Booking::getPrice).sum() : 0 %></p>
  <a href="vendors">Back to Vendors</a>
</div>
</body>
</html>