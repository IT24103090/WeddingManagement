package com.weddingflow.servlet;

import com.weddingflow.model.User;
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

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            // List all users with search and sort
            String search = request.getParameter("search");
            String sortBy = request.getParameter("sortBy");
            List<User> users = new ArrayList<>();
            List<String> lines = FileUtil.readFile(FileUtil.getUserFile());
            for (String line : lines) {
                try {
                    users.add(User.fromFileString(line));
                } catch (IllegalArgumentException e) {
                    // Log error if needed
                }
            }

            // Apply search
            if (search != null && !search.trim().isEmpty()) {
                final String searchLower = search.toLowerCase();
                users = users.stream()
                        .filter(user -> user.getUsername().toLowerCase().contains(searchLower))
                        .collect(Collectors.toList());
            }

            // Apply bubble sort
            if (sortBy != null && !sortBy.isEmpty()) {
                bubbleSort(users, sortBy);
            }

            request.setAttribute("users", users);
            request.setAttribute("search", search);
            request.setAttribute("sortBy", sortBy);
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        } else if (action.equals("add") || action.equals("edit")) {
            if (action.equals("edit")) {
                String username = request.getParameter("username");
                List<String> lines = FileUtil.readFile(FileUtil.getUserFile());
                for (String line : lines) {
                    User user = User.fromFileString(line);
                    if (user.getUsername().equals(username)) {
                        request.setAttribute("user", user);
                        break;
                    }
                }
            }
            request.getRequestDispatcher("/userForm.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        if (action.equals("add")) {
            try {
                User user = new User(username, password, userType);
                SapareUtil.insertUser(user);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("edit")) {
            String oldUsername = request.getParameter("oldUsername");
            try {
                User user = new User(username, password, userType);
                SapareUtil.updateUser(oldUsername, user);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("delete")) {
            SapareUtil.deleteUser(username);
        }

        response.sendRedirect("users");
    }

    // Bubble sort implementation for users
    private void bubbleSort(List<User> users, String sortType) {
        int n = users.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean swap = false;
                User current = users.get(j);
                User next = users.get(j + 1);

                if ("usernameAsc".equals(sortType)) {
                    if (current.getUsername().compareTo(next.getUsername()) > 0) {
                        swap = true;
                    }
                } else if ("usernameDesc".equals(sortType)) {
                    if (current.getUsername().compareTo(next.getUsername()) < 0) {
                        swap = true;
                    }
                }

                if (swap) {
                    // Swap elements
                    users.set(j, next);
                    users.set(j + 1, current);
                }
            }
        }
    }
}