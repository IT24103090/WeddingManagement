
package com.weddingflow.servlet;

import com.weddingflow.model.Review;
import com.weddingflow.util.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        List<Review> reviews = new ArrayList<>();
        List<String> reviewLines = FileUtil.readFile(FileUtil.getReviewFile());

        for (String line : reviewLines) {
            String[] parts = line.split(":");
            if (parts.length == 4 && parts[0].equals(category)) {
                reviews.add(new Review(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
            }
        }

        request.setAttribute("reviews", reviews);
        request.getRequestDispatcher("reviews.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String category = request.getParameter("category");
        String ratingStr = request.getParameter("rating");
        String comment = request.getParameter("comment");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int rating;
        try {
            rating = Integer.parseInt(ratingStr);
            if (rating < 1 || rating > 5) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid rating. Please enter a number between 1 and 5.");
            request.getRequestDispatcher("reviews.jsp").forward(request, response);
            return;
        }

        if (category == null || category.trim().isEmpty() || comment == null || comment.trim().isEmpty()) {
            request.setAttribute("error", "Category and comment are required.");
            request.getRequestDispatcher("reviews.jsp").forward(request, response);
            return;
        }

        String reviewData = category + ":" + username + ":" + rating + ":" + comment;
        FileUtil.writeFile(FileUtil.getReviewFile(), reviewData);
        response.sendRedirect("reviews?category=" + category);
    }
}
