<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>WeddingFlow</title>
  <link rel="stylesheet" href="css/styles.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
  <style>
    .account-logo {
      width: 24px;
      height: 24px;
      vertical-align: middle;
    }
    .container {
      max-width: 1000px;
      margin: 0 auto;
      padding: 20px;
      font-family: 'Poppins', sans-serif;
      text-align: center;
    }
    .container h2 {
      font-size: 2.5rem;
      font-weight: 600;
      margin-bottom: 10px;
    }
    .container p {
      font-size: 1.2rem;
      margin-bottom: 20px;
    }
    .auth-links {
      display: flex;
      justify-content: center;
      gap: 15px;
      margin-bottom: 30px;
    }
    .auth-links a {
      font-size: 1rem;
      padding: 10px 20px;
      text-decoration: none;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }
    .cta-button {
      display: inline-block;
      padding: 12px 30px;
      font-size: 1.1rem;
      font-weight: 500;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      text-decoration: none;
      transition: background-color 0.3s ease, transform 0.2s ease;
    }
    .cta-button:hover {
      transform: translateY(-2px);
    }
    .features {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      gap: 20px;
      margin-top: 40px;
    }
    .feature-card {
      flex: 1;
      min-width: 250px;
      max-width: 300px;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
      transition: transform 0.3s ease;
    }
    .feature-card:hover {
      transform: translateY(-5px);
    }
    .feature-card h3 {
      font-size: 1.3rem;
      font-weight: 500;
      margin-bottom: 10px;
    }
    .feature-card p {
      font-size: 1rem;
      line-height: 1.5;
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
  <p>Plan your perfect wedding with ease!</p>
    <% if (session.getAttribute("username") == null) { %>
  <div class="auth-links">
    <a href="login.jsp">Login</a>
    <a href="register.jsp">Register</a>
  </div>
    <% } else { %>
  <a href="dashboard" class="cta-button">Start Planning Now</a>
    <% } %>
  <div class="features">
    <div class="feature-card">
      <h3>Find Top Vendors</h3>
      <p>Discover the best vendors for catering, photography, and more, tailored to your style and budget.</p>
    </div>
    <div class="feature-card">
      <h3>Perfectly Paired,Professionally Planned</h3>
      <p>We connect you with trusted wedding vendors to turn your vision into a flawless reality.</p>
    </div>
    <div class="feature-card">
      <h3>Wedding Magic Starts with the Right Vendors</h3>
      <p>Emphasizes the transformative role of vendors in wedding planning.</p>
    </div>
  </div>
</body>
</html>