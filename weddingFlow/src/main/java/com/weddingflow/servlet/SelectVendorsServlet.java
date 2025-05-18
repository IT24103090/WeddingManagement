
package com.weddingflow.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectVendors")
public class SelectVendorsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedVendors = request.getParameterValues("selectedVendors");
        if (selectedVendors != null && selectedVendors.length > 0) {
            List<String> vendorList = new ArrayList<>();
            for (String vendor : selectedVendors) {
                vendorList.add(vendor); // Format: "category:price"
            }
            request.getSession().setAttribute("selectedVendors", vendorList);
        }
        response.sendRedirect("booking.jsp");
    }
}


