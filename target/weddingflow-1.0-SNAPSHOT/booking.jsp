<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
  <title>Booking - WeddingFlow</title>
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
  <h2>Booking Details</h2>
  <% if (session.getAttribute("username") == null) { %>
  <p style="color: red;">Please log in to book vendors.</p>
  <a href="login.jsp">Login</a>
  <% } else { %>
  <form action="booking" method="post">
    <label>Your Name:</label>
    <input type="text" name="name" value="${sessionScope.username}" readonly><br>
    <label>Event Type:</label>
    <input type="text" name="eventType" required><br>
    <label>Event Date:</label>
    <input type="date" name="eventDate" required><br>
    <label>Selected Vendors:</label>
    <c:set var="totalPrice" value="0" />
    <c:forEach var="vendor" items="${sessionScope.selectedVendors}">
      <c:set var="vendorParts" value="${fn:split(vendor, ':')}" />
      <p>* ${vendorParts[0]} - Rs${vendorParts[1]} <a href="removeVendor?vendor=${vendor}" style="color: red;">Remove</a></p>
      <c:set var="totalPrice" value="${totalPrice + vendorParts[1]}" />
    </c:forEach>
    <label>Total Price:</label>
    <input type="text" name="totalPrice" value="Rs${totalPrice}.00" readonly><br>
    <label>Quantity:</label>
    <input type="number" name="quantity" value="1" min="1" required><br>
    <button type="submit">Confirm Booking and Proceed to Payment</button>
  </form>
  <% if (request.getAttribute("error") != null) { %>
  <p style="color: red;"><%= request.getAttribute("error") %></p>
  <% } %>
  <c:if test="${empty sessionScope.selectedVendors}">
    <p>No vendors selected. Please go back and select vendors.</p>
  </c:if>
  <a href="vendors">Back to Vendors</a>
  <% } %>
</div>
</body>
</html>