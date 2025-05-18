package com.weddingflow.servlet;

import com.weddingflow.util.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Username and password are required");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        String hashedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        List<String> users = FileUtil.readFile(FileUtil.getUserFile());
        for (String user : users) {
            String[] parts = user.split(":");
            if (parts.length < 2 || parts[0] == null || parts[1] == null) {
                continue;
            }
            if (parts[0].equals(username) && parts[1].equals(hashedPassword)) {
                request.getSession().setAttribute("username", username);
                String userType = parts.length > 2 ? parts[2] : "Basic";
                request.getSession().setAttribute("userType", userType);
                response.sendRedirect("dashboard");
                return;
            }
        }

        request.setAttribute("error", "Invalid username or password");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}