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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        if (username == null || username.trim().isEmpty() || username.length() < 3) {
            request.setAttribute("error", "Username must be at least 3 characters long");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (password == null || password.length() < 6) {
            request.setAttribute("error", "Password must be at least 6 characters long");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if (userType == null || !userType.matches("Basic|Standard|Premium")) {
            request.setAttribute("error", "Invalid user type");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        List<String> users = FileUtil.readFile(FileUtil.getUserFile());
        if (users.stream().anyMatch(line -> line.startsWith(username + ":"))) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        String hashedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        FileUtil.writeFile(FileUtil.getUserFile(), username + ":" + hashedPassword + ":" + userType);
        response.sendRedirect("login.jsp");
    }
}