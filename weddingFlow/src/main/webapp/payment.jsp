<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Payment - WeddingFlow</title>
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
  <h2>Payment Details</h2>
  <% if (session.getAttribute("username") == null) { %>
  <p style="color: red;">Please log in to make a payment.</p>
  <a href="login.jsp">Login</a>
  <% } else { %>
  <!-- Display booking summary -->
  <h3>Booking Summary</h3>
  <c:set var="totalPrice" value="0" />
  <c:forEach var="booking" items="${sessionScope.pendingBookings}">
    <p>* ${booking.item} - $${booking.price} (Quantity: ${booking.quantity})</p>
    <c:set var="totalPrice" value="${totalPrice + booking.price * booking.quantity}" />
  </c:forEach>
  <p><strong>Total Price:</strong> $${totalPrice}</p>

  <form action="payment" method="post">
    <label>Card Number(XXXXXX):</label>   <!---Label change--->
    <input type="text" name="cardNumber" maxlength="16" required><br>
    <label>Expiry Date (MM/YYYY):</label>
    <input type="text" name="expiryDate" placeholder="MM/YYYY" required><br>
    <label>CVV:</label>
    <input type="text" name="cvv" maxlength="3" required><br>
    <button type="submit">Pay Now</button>
  </form>
  <% if (request.getAttribute("error") != null) { %>
  <p style="color: red;"><%= request.getAttribute("error") %></p>
  <% } %>
  <a href="booking.jsp">Back to Booking</a>
  <% } %>
</div>
</body>
</html>
