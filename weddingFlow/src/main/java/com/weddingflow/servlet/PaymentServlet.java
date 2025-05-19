package com.weddingflow.servlet;

import com.weddingflow.model.Booking;
import com.weddingflow.util.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    @Override
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

        //check and retrieve pending bookings into the system
        List<Booking> pendingBookings = (List<Booking>) request.getSession().getAttribute("pendingBookings");
        if (pendingBookings == null || pendingBookings.isEmpty()) {
            request.setAttribute("error", "No pending bookings found.");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
            return;
        }

        //write bookings to the file bookings.txt
        for (Booking booking : pendingBookings) {
            booking.setStatus("Paid");
            String bookingData = booking.getUsername() + ":" + booking.getOrderId() + ":" + booking.getItem() + ":" +
                    booking.getQuantity() + ":" + booking.getPrice() + ":" + booking.getStatus() + ":" +
                    booking.getEventType() + ":" + booking.getEventDate();
            FileUtil.writeFile(FileUtil.getBookingFile(), bookingData); // Changed from appendFile to writeFile
        }

        //clear attributes and redirect to dashboard
        request.getSession().removeAttribute("pendingBookings");
        request.getSession().removeAttribute("selectedVendors");

        response.sendRedirect("dashboard");
    }

}
