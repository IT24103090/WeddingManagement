package com.weddingflow.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");


        if (username == null) {
            request.setAttribute("error", "You must be logged in to make a payment");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
            return;
        }

        //Handle Invalid Payment Details (card number)
        if (cardNumber == null || cardNumber.trim().length() != 16 ||
                expiryDate == null || !expiryDate.matches("\\d{2}/\\d{4}") ||
                cvv == null || cvv.trim().length() != 3) {
            request.setAttribute("error", "Invalid payment details. Please check card number (16 digits), expiry date (MM/YYYY), and CVV (3 digits).");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
            return;
        }


}
