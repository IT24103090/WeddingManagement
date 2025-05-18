package com.weddingflow.servlet;

import com.weddingflow.util.FileUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/updateDetails")
public class UpdateDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("updateDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String newPassword = request.getParameter("newPassword");

        if (username != null && newPassword != null && !newPassword.trim().isEmpty()) {
            if (newPassword.length() < 6) {
                request.setAttribute("error", "Password must be at least 6 characters long");
                request.getRequestDispatcher("updateDetails.jsp").forward(request, response);
                return;
            }
            String hashedPassword = Base64.getEncoder().encodeToString(newPassword.getBytes());
            List<String> users = FileUtil.readFile(FileUtil.getUserFile());
            users = users.stream()
                    .map(line -> {
                        if (line.startsWith(username + ":")) {
                            return username + ":" + hashedPassword;
                        }
                        return line;
                    })
                    .collect(Collectors.toList());
            Files.write(Paths.get(FileUtil.getUserFile()), users);
        }
        response.sendRedirect("dashboard.jsp");
    }
}