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

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String eventType = request.getParameter("eventType");
        String eventDate = request.getParameter("eventDate");
        int quantity;
        double totalPrice;
        String status = "Pending";

        // Input validation
        if (username == null) {
            request.setAttribute("error", "You must be logged in to book vendors");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
            totalPrice = Double.parseDouble(request.getParameter("totalPrice").replace("Rs", "").replace(".00", ""));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid quantity or total price");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        if (eventType == null || eventType.trim().isEmpty() || eventDate == null || eventDate.trim().isEmpty() || quantity <= 0) {
            request.setAttribute("error", "All fields must be filled with valid data");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        @SuppressWarnings("unchecked")
        List<String> selectedVendors = (List<String>) request.getSession().getAttribute("selectedVendors");
        if (selectedVendors == null || selectedVendors.isEmpty()) {
            request.setAttribute("error", "No vendors selected");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        // Store booking details in session instead of writing to file
        List<Booking> pendingBookings = new ArrayList<>();
        for (String vendor : selectedVendors) {
            String[] parts = vendor.split(":");
            if (parts.length == 2) {
                String item = parts[0];
                double price = Double.parseDouble(parts[1]);
                String orderId = String.valueOf(System.currentTimeMillis());
                pendingBookings.add(new Booking(username, orderId, item, quantity, price, status, eventType, eventDate));
            }
        }

        // Save pending bookings in session
        request.getSession().setAttribute("pendingBookings", pendingBookings);

        // Forward to payment.jsp
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        List<Booking> bookings = new ArrayList<>();
        List<String> bookingLines = FileUtil.readFile(FileUtil.getBookingFile());

        for (String line : bookingLines) {
            String[] parts = line.split(":");
            if (parts.length >= 6 && parts[0].equals(username)) {
                String eventType = parts.length > 6 ? parts[6] : "";
                String eventDate = parts.length > 7 ? parts[7] : "";
                bookings.add(new Booking(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5], eventType, eventDate));
            }
        }

        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("previousBookings.jsp").forward(request, response);
    }
}