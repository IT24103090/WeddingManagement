<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>About Us - WeddingFlow</title>
    <link rel="stylesheet" href="css/styles.css">
    <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            text-align: center;
        }
        .container h2 {
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 10px;
        }
        .container .section {
            margin-bottom: 20px;
            font-size: 1.1rem;
            line-height: 1.6;
        }
        .container .cta-button {
            display: inline-block;
            padding: 12px 24px;
            font-size: 1.1rem;
            font-weight: 500;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }
        .container .cta-button:hover {
            transform: translateY(-2px);
        }
        .hero-image {
            width: 100%;
            max-width: 600px;
            height: auto;
            margin: 20px auto;
            border-radius: 10px;
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
    <h2>About Us</h2>
    <div class="section">
        <p>Welcome to WeddingFlow, your ultimate wingman for crafting the perfect wedding vibe! We’re here to help couples slay their big day and vendors shine with our slick management system. Launched with love, we connect dreamers and doers to make wedding planning a breeze.</p>
    </div>
    <div class="section">
        <p>Our mission? To simplify the chaos of wedding prep. With WeddingFlow, couples can explore top-tier vendors, book services, and track plans—all in one spot. Vendors get a platform to flex their skills, manage bookings, and grow their biz. It’s all about good vibes and zero stress!</p>
    </div>
    <div class="section">
        <p>Why choose us? We bring you a seamless experience with features like vendor directories, real-time updates, and personalized dashboards. Whether you’re saying “I do” or “Let’s book,” WeddingFlow’s got your back.</p>
    </div>
    <% if (session.getAttribute("username") == null) { %>
    <a href="register.jsp" class="cta-button">Join the Wedding Crew Now!</a>
    <% } %>
    <img src="images/bg1.png" alt="About Us Inspiration" class="hero-image">
</div>
</body>
</html>