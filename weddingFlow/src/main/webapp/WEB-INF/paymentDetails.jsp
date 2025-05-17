<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Details - WeddingFlow</title>
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

<!---add details functionality--->

</body>
</html>
