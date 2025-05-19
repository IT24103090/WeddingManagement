package com.weddingflow.servlet;

import com.weddingflow.model.User;
import com.weddingflow.util.FileUtil;
import com.weddingflow.util.AdminUtil;
import com.weddingflow.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class AdminUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            // List all users with search and sort
            String search = request.getParameter("search");
            String sortBy = request.getParameter("sortBy");
            LinkedList<User> users = new LinkedList<>();
            LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getUserFile());
            String[] lineArray = lines.toArray(new String[0]);
            for (String line : lineArray) {
                try {
                    users.add(User.fromFileString(line));
                } catch (IllegalArgumentException e) {
                    // Log error if needed
                }
            }

            // Apply search
            if (search != null && !search.trim().isEmpty()) {
                final String searchLower = search.toLowerCase();
                LinkedList<User> filteredUsers = new LinkedList<>();
                User[] userArray = users.toArray(new User[0]);
                for (User user : userArray) {
                    if (user.getUsername().toLowerCase().contains(searchLower)) {
                        filteredUsers.add(user);
                    }
                }
                users = filteredUsers;
            }

            // Apply bubble sort
            if (sortBy != null && !sortBy.isEmpty()) {
                bubbleSort(users, sortBy); // Fixed: Changed sortType to sortBy
            }

            request.setAttribute("users", users.toArray(new User[0]));
            request.setAttribute("search", search);
            request.setAttribute("sortBy", sortBy);
            request.getRequestDispatcher("/adminusers.jsp").forward(request, response);
        } else if (action.equals("add") || action.equals("edit")) {
            if (action.equals("edit")) {
                String username = request.getParameter("username");
                LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getUserFile());
                String[] lineArray = lines.toArray(new String[0]);
                for (String line : lineArray) {
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
                AdminUtil.insertUser(user);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("edit")) {
            String oldUsername = request.getParameter("oldUsername");
            try {
                User user = new User(username, password, userType);
                AdminUtil.updateUser(oldUsername, user);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/userForm.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("delete")) {
            AdminUtil.deleteUser(username);
        }

        response.sendRedirect("users");
    }

    // Bubble sort implementation for users
    private void bubbleSort(LinkedList<User> users, String sortType) {
        int n = users.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                User current = users.get(j);
                User next = users.get(j + 1);
                boolean swap = false;

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
                    // Swap elements using set method
                    users.set(j, next);
                    users.set(j + 1, current);
                }
            }
        }
    }
}