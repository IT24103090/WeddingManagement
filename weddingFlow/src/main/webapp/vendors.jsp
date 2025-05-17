<%--
  Created by IntelliJ IDEA.
  User: sades
  Date: 5/17/2025
  Time: 6:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vendors - WeddingFlow</title>
    <!-- Link to external CSS file for styling -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<!-- Header section with site title and navigation links -->
<header>
    <h1>WeddingFlow</h1>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="vendors">Vendors</a>
        <a href="contact">Contact</a>
        <a href="aboutus.jsp">About</a>
        <!-- Show account icon linking to dashboard only if user is logged in -->
        <% if (session.getAttribute("username") != null) { %>
        <a href="dashboard"><img src="images/user.png" alt="Account" class="account-logo"></a>
        <% } %>
    </nav>
</header>
<!-- Main content container -->
<div class="container">
    <h2>Vendor Details</h2>
    <!-- Search and sort form -->
    <form action="vendors" method="get">
        <!-- Input for search term, preserves previous value using EL -->
        <input type="text" name="search" placeholder="Search vendors..." value="${param.search}">
        <!-- Sort dropdown: auto-selects based on current query parameter -->
        <select name="sort">
            <option value="" ${param.sort == null || param.sort.isEmpty() ? 'selected' : ''}>Sort</option>
            <option value="price" ${param.sort == 'price' ? 'selected' : ''}>Price (Low to High)</option>
            <option value="category" ${param.sort == 'category' ? 'selected' : ''}>Name (A-Z)</option>
            <option value="category-desc" ${param.sort == 'category-desc' ? 'selected' : ''}>Name (Z-A)</option>
        </select>
        <button type="submit">Search</button>
        <!-- Search button -->
    </form>
    <!-- Form for selecting vendors to book -->
    <form action="selectVendors" method="post" onsubmit="return validateForm()">
        <div class="vendor-list">
            <!-- Loop through the list of vendors and display each one -->
            <c:forEach var="vendor" items="${vendors}">
                <div class="vendor-card">
                    <h3>${vendor.category}</h3>
                    <p>Category: ${vendor.name}</p>
                    <p>Price: Rs.${vendor.price}</p>
                    <p>Contact: ${vendor.contact}</p>
                    <!-- Checkbox to select this vendor for booking -->
                    <input type="checkbox" name="selectedVendors" value="${vendor.category}:${vendor.price}">
                    <a href="reviews?category=${vendor.category}">View Reviews</a>
                    <!-- Link to view reviews for this vendor -->
                </div>
            </c:forEach>
        </div>
        <!-- Submit button to proceed with selected vendors -->
        <button type="submit">Proceed to Booking</button>
    </form>
</div>
<!-- JavaScript for form validation -->
<script>
    function validateForm() {
        const checkboxes = document.getElementsByName("selectedVendors");
        let isChecked = false;
        // Check if at least one vendor is selected
        for (let i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                isChecked = true;
                break;
            }
        }
        // If no vendors selected, show alert and prevent form submission
        if (!isChecked) {
            alert("Please select at least one vendor before proceeding to booking.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>