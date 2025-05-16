package com.weddingflow.servlet;

import com.weddingflow.model.Vendor;
import com.weddingflow.util.FileUtil;
import com.weddingflow.util.SapareUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/adminvendors")
public class AdminVendorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            // List all vendors with search and sort
            String search = request.getParameter("search");
            String sortBy = request.getParameter("sortBy");
            List<Vendor> vendors = new ArrayList<>();
            List<String> lines = FileUtil.readFile(FileUtil.getVendorFile());
            for (String line : lines) {
                try {
                    vendors.add(Vendor.fromFileString(line));
                } catch (IllegalArgumentException e) {
                    // Log error if needed
                }
            }

            // Apply search
            if (search != null && !search.trim().isEmpty()) {
                final String searchLower = search.toLowerCase();
                vendors = vendors.stream()
                        .filter(vendor -> vendor.getName().toLowerCase().contains(searchLower))
                        .collect(Collectors.toList());
            }

            // Apply bubble sort
            if (sortBy != null && !sortBy.isEmpty()) {
                bubbleSort(vendors, sortBy);
            }

            request.setAttribute("vendors", vendors);
            request.setAttribute("search", search);
            request.setAttribute("sortBy", sortBy);
            request.getRequestDispatcher("/adminvendors.jsp").forward(request, response);
        } else if (action.equals("add") || action.equals("edit")) {
            if (action.equals("edit")) {
                String name = request.getParameter("name");
                List<String> lines = FileUtil.readFile(FileUtil.getVendorFile());
                for (String line : lines) {
                    Vendor vendor = Vendor.fromFileString(line);
                    if (vendor.getName().equals(name)) {
                        request.setAttribute("vendor", vendor);
                        break;
                    }
                }
            }
            request.getRequestDispatcher("/vendorForm.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String contact = request.getParameter("contact");

        if (action.equals("add")) {
            try {
                Vendor vendor = new Vendor(name, category, Double.parseDouble(price), contact);
                SapareUtil.insertVendor(vendor);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/vendorForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("edit")) {
            String oldName = request.getParameter("oldName");
            try {
                Vendor vendor = new Vendor(name, category, Double.parseDouble(price), contact);
                SapareUtil.updateVendor(oldName, vendor);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/vendorForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("delete")) {
            SapareUtil.deleteVendor(name);
        }

        response.sendRedirect("adminvendors");
    }

    // Bubble sort implementation for vendors
    private void bubbleSort(List<Vendor> vendors, String sortType) {
        int n = vendors.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean swap = false;
                Vendor current = vendors.get(j);
                Vendor next = vendors.get(j + 1);

                if ("revenueAsc".equals(sortType)) {
                    if (current.getPrice() > next.getPrice()) {
                        swap = true;
                    }
                } else if ("revenueDesc".equals(sortType)) {
                    if (current.getPrice() < next.getPrice()) {
                        swap = true;
                    }
                }

                if (swap) {
                    // Swap elements
                    vendors.set(j, next);
                    vendors.set(j + 1, current);
                }
            }
        }
    }
}