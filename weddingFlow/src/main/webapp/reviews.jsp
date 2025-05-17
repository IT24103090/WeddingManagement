<%--
  Created by IntelliJ IDEA.
  User: sades
  Date: 5/17/2025
  Time: 6:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Reviews - WeddingFlow</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header>
  <!-- Site header with navigation menu -->
  <h1>WeddingFlow</h1>
  <nav>
    <a href="index.jsp">Home</a>
    <a href="vendors">Vendors</a>
    <a href="contact">Contact</a>
    <a href="aboutus.jsp">About</a>
    <!-- If user is logged in, show account icon linking to dashboard -->
    <% if (session.getAttribute("username") != null) { %>
    <a href="dashboard"><img src="images/user.png" alt="Account" class="account-logo"></a>
    <% } %>
  </nav>
</header>
<!-- Main content container -->
<div class="container">
  <!-- Display category name from URL parameter -->
  <h2>Reviews for ${param.category}</h2>
  <!-- If user is NOT logged in, show login prompt -->
  <% if (session.getAttribute("username") == null) { %>
  <p style="color: red;">Please log in to view or submit reviews.</p>
  <a href="login.jsp">Login</a>
  <!-- If user IS logged in, show reviews and review form -->
  <% } else { %>
  <h3>Reviews</h3>
  <!-- Loop through the list of reviews and display each -->
  <c:forEach var="review" items="${reviews}">
    <p><strong>${review.username}</strong>: ${review.comment} (Rating: ${review.rating}/5)</p>
  </c:forEach>
  <!-- Show a message if there are no reviews yet -->
  <c:if test="${empty reviews}">
    <p>No reviews yet for this vendor.</p>
  </c:if>
  <!-- Review submission form -->
  <h3>Submit a Review</h3>
  <form action="reviews" method="post">
    <!-- Send category name back to server on form submission -->
    <input type="hidden" name="category" value="${param.category}">
    <!-- Rating input (1 to 5) -->
    <label>Rating (1-5):</label>
    <input type="number" name="rating" min="1" max="5" required><br>
    <!-- Comment input -->
    <label>Comment:</label>
    <textarea name="comment" required></textarea><br>
    <!-- Submit button -->
    <button type="submit">Submit Review</button>
  </form>
  <!-- Link back to the vendors list -->
  <a href="vendors">Back to Vendors</a>
  <% } %>
</div>
</body>
</html>

