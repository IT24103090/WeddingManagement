<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Contact Us - WeddingFlow</title>
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
  <h2>Contact Us</h2>
  <h3>Submit Your Message:</h3>
  <form action="contact" method="post">
    <label>Your Name:</label>
    <input type="text" value="${sessionScope.username}" readonly><br>
    <label>Your Message:</label>
    <input type="text" name="message" required><br>
    <button type="submit">Submit</button>
  </form>
  <h3>Previous Messages:</h3>
  <c:forEach var="message" items="${messages}">
    <p class="message">${message.message}</p>
  </c:forEach>
</div>
</body>
</html>