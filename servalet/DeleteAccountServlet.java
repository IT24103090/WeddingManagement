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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            List<String> users = FileUtil.readFile(FileUtil.getUserFile());
            users = users.stream()
                    .filter(line -> !line.startsWith(username + ":"))
                    .collect(Collectors.toList());
            Files.write(Paths.get(FileUtil.getUserFile()), users);

            List<String> bookings = FileUtil.readFile(FileUtil.getBookingFile());
            bookings = bookings.stream()
                    .filter(line -> !line.startsWith(username + ":"))
                    .collect(Collectors.toList());
            Files.write(Paths.get(FileUtil.getBookingFile()), bookings);

            List<String> messages = FileUtil.readFile(FileUtil.getMessageFile());
            messages = messages.stream()
                    .filter(line -> !line.startsWith(username + ":"))
                    .collect(Collectors.toList());
            Files.write(Paths.get(FileUtil.getMessageFile()), messages);

            request.getSession().invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}