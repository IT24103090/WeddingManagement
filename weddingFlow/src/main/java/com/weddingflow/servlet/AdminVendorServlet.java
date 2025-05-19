package com.weddingflow.servlet;

import com.weddingflow.model.Vendor;
import com.weddingflow.util.FileUtil;
import com.weddingflow.util.AdminUtil;
import com.weddingflow.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminvendors")
public class AdminVendorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            // List all vendors with search and sort
            String search = request.getParameter("search");
            String sortBy = request.getParameter("sortBy");
            LinkedList<Vendor> vendors = new LinkedList<>();
            LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getVendorFile());
            String[] lineArray = lines.toArray(new String[0]);
            for (String line : lineArray) {
                try {
                    vendors.add(Vendor.fromFileString(line));
                } catch (IllegalArgumentException e) {
                    // Log error
                }
            }

            // Apply search
            if (search != null && !search.trim().isEmpty()) {
                final String searchLower = search.toLowerCase();
                LinkedList<Vendor> filteredVendors = new LinkedList<>();
                Vendor[] vendorArray = vendors.toArray(new Vendor[0]);
                for (Vendor vendor : vendorArray) {
                    if (vendor.getName().toLowerCase().contains(searchLower)) {
                        filteredVendors.add(vendor);
                    }
                }
                vendors = filteredVendors;
            }

            // Apply bubble sort
            if (sortBy != null && !sortBy.isEmpty()) {
                bubbleSort(vendors, sortBy);
            }

            request.setAttribute("vendors", vendors.toArray(new Vendor[0]));
            request.setAttribute("search", search);
            request.setAttribute("sortBy", sortBy);
            request.getRequestDispatcher("/adminvendors.jsp").forward(request, response);
        } else if (action.equals("add") || action.equals("edit")) {
            if (action.equals("edit")) {
                String name = request.getParameter("name");
                LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getVendorFile());
                String[] lineArray = lines.toArray(new String[0]);
                for (String line : lineArray) {
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
                Vendor vendor = new Vendor(category, name, Double.parseDouble(price), contact);
                AdminUtil.insertVendor(vendor);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/vendorForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("edit")) {
            String oldName = request.getParameter("oldName");
            try {
                Vendor vendor = new Vendor(category, name, Double.parseDouble(price), contact);
                AdminUtil.updateVendor(oldName, vendor);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/vendorForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("delete")) {
            AdminUtil.deleteVendor(name);
        }

        response.sendRedirect("adminvendors");
    }

    // Bubble sort implementation for vendors
    private void bubbleSort(LinkedList<Vendor> vendors, String sortType) {
        int n = vendors.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Vendor current = vendors.get(j);
                Vendor next = vendors.get(j + 1);
                boolean swap = false;

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
                    // Swap elements using set method
                    vendors.set(j, next);
                    vendors.set(j + 1, current);
                }
            }
        }
    }
}