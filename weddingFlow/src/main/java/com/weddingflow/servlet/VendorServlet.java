package com.weddingflow.servlet;

import com.weddingflow.model.Vendor;
import com.weddingflow.util.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/vendors")
public class VendorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        String sort = request.getParameter("sort");

        List<Vendor> vendors = new ArrayList<>();
        List<String> vendorLines = FileUtil.readFile(FileUtil.getVendorFile());

        for (String line : vendorLines) {
            String[] parts = line.split(":");
            vendors.add(new Vendor(parts[1], parts[0], Double.parseDouble(parts[2]), parts[3]));
        }

        if (search != null && !search.trim().isEmpty()) {
            final String searchLower = search.toLowerCase();
            vendors = vendors.stream()
                    .filter(v -> v.getCategory().toLowerCase().contains(searchLower) || v.getName().toLowerCase().contains(searchLower))
                    .collect(Collectors.toList());
        }

        // Apply bubble sort based on sort parameter
        if ("price".equals(sort)) {
            bubbleSort(vendors, "price");
        } else if ("category".equals(sort)) {
            bubbleSort(vendors, "category");
        } else if ("category-desc".equals(sort)) {
            bubbleSort(vendors, "category-desc");
        }

        request.setAttribute("vendors", vendors);
        request.getRequestDispatcher("vendors.jsp").forward(request, response);
    }

    // Bubble sort implementation for vendors
    private void bubbleSort(List<Vendor> vendors, String sortType) {
        int n = vendors.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean swap = false;
                Vendor current = vendors.get(j);
                Vendor next = vendors.get(j + 1);

                if ("price".equals(sortType)) {
                    if (current.getPrice() > next.getPrice()) {
                        swap = true;
                    }
                } else if ("category".equals(sortType)) {
                    if (current.getCategory().compareTo(next.getCategory()) > 0) {
                        swap = true;
                    }
                } else if ("category-desc".equals(sortType)) {
                    if (current.getCategory().compareTo(next.getCategory()) < 0) {
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