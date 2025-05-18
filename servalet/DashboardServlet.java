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

@WebServlet({"/dashboard", "/orderDetails", "/paymentDetails"})
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        List<Booking> bookings = new ArrayList<>();
        if (username != null) {
            List<String> bookingLines = FileUtil.readFile(FileUtil.getBookingFile());
            for (String line : bookingLines) {
                String[] parts = line.split(":");
                if (parts.length >= 6 && parts[0].equals(username)) {
                    String eventType = parts.length > 6 ? parts[6] : "";
                    String eventDate = parts.length > 7 ? parts[7] : "";
                    bookings.add(new Booking(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5], eventType, eventDate));
                }
            }
        }
        request.setAttribute("bookings", bookings);

        String path = request.getServletPath();
        if ("/orderDetails".equals(path)) {
            request.getRequestDispatcher("orderDetails.jsp").forward(request, response);
        } else if ("/paymentDetails".equals(path)) {
            request.getRequestDispatcher("paymentDetails.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}