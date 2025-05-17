<%--
  Created by IntelliJ IDEA.
  User: sades
  Date: 5/17/2025
  Time: 1:39 PM
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
  <h2>Reviews for ${param.category}</h2>
  <% if (session.getAttribute("username") == null) { %>
  <p style="color: red;">Please log in to view or submit reviews.</p>
  <a href="login.jsp">Login</a>
  <% } else { %>
  <h3>Reviews</h3>
  <c:forEach var="review" items="${reviews}">
    <p><strong>${review.username}</strong>: ${review.comment} (Rating: ${review.rating}/5)</p>
  </c:forEach>
  <c:if test="${empty reviews}">
    <p>No reviews yet for this vendor.</p>
  </c:if>
  <h3>Submit a Review</h3>
  <form action="reviews" method="post">
    <input type="hidden" name="category" value="${param.category}">
    <label>Rating (1-5):</label>
    <input type="number" name="rating" min="1" max="5" required><br>
    <label>Comment:</label>
    <textarea name="comment" required></textarea><br>
    <button type="submit">Submit Review</button>
  </form>
  <a href="vendors">Back to Vendors</a>
  <% } %>
</div>
</body>
</html>