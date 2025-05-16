package com.weddingflow.util;

import com.weddingflow.model.User;
import com.weddingflow.model.Vendor;
import com.weddingflow.model.Booking;
import com.weddingflow.model.Review;
import com.weddingflow.model.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SapareUtil {

    // User Management
    public static void insertUser(User user) throws IOException {
        String userData = user.toFileString();
        FileUtil.writeFile(FileUtil.getUserFile(), userData);
    }

    public static void updateUser(String oldUsername, User updatedUser) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getUserFile());
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            User existingUser = User.fromFileString(line);
            if (existingUser.getUsername().equals(oldUsername)) {
                updatedLines.add(updatedUser.toFileString());
                found = true;
            } else {
                updatedLines.add(line);
            }
        }

        if (!found) {
            throw new IllegalArgumentException("User not found: " + oldUsername);
        }

        overwriteFile(FileUtil.getUserFile(), updatedLines);
    }

    public static void deleteUser(String username) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getUserFile());
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            User user = User.fromFileString(line);
            if (!user.getUsername().equals(username)) {
                updatedLines.add(line);
            }
        }

        overwriteFile(FileUtil.getUserFile(), updatedLines);
    }

    // Vendor Management
    public static void insertVendor(Vendor vendor) throws IOException {
        String vendorData = vendor.toFileString();
        FileUtil.writeFile(FileUtil.getVendorFile(), vendorData);
    }

    public static void updateVendor(String oldName, Vendor updatedVendor) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getVendorFile());
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            Vendor existingVendor = Vendor.fromFileString(line);
            if (existingVendor.getName().equals(oldName)) {
                updatedLines.add(updatedVendor.toFileString());
                found = true;
            } else {
                updatedLines.add(line);
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Vendor not found: " + oldName);
        }

        overwriteFile(FileUtil.getVendorFile(), updatedLines);
    }

    public static void deleteVendor(String name) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getVendorFile());
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            Vendor vendor = Vendor.fromFileString(line);
            if (!vendor.getName().equals(name)) {
                updatedLines.add(line);
            }
        }

        overwriteFile(FileUtil.getVendorFile(), updatedLines);
    }

    // Booking Management
    public static void insertBooking(Booking booking) throws IOException {
        String bookingData = String.join(":",
                booking.getUsername(),
                booking.getOrderId(),
                booking.getItem(),
                String.valueOf(booking.getQuantity()),
                String.valueOf(booking.getPrice()),
                booking.getStatus(),
                booking.getEventType(),
                booking.getEventDate()
        );
        FileUtil.writeFile(FileUtil.getBookingFile(), bookingData);
    }

    public static void updateBooking(String orderId, Booking updatedBooking) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getBookingFile());
        List<String> updatedLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 8 && parts[1].equals(orderId)) {
                updatedLines.add(String.join(":",
                        updatedBooking.getUsername(),
                        updatedBooking.getOrderId(),
                        updatedBooking.getItem(),
                        String.valueOf(updatedBooking.getQuantity()),
                        String.valueOf(updatedBooking.getPrice()),
                        updatedBooking.getStatus(),
                        updatedBooking.getEventType(),
                        updatedBooking.getEventDate()
                ));
                found = true;
            } else {
                updatedLines.add(line);
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Booking not found: " + orderId);
        }

        overwriteFile(FileUtil.getBookingFile(), updatedLines);
    }

    public static void deleteBooking(String orderId) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getBookingFile());
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 8 && !parts[1].equals(orderId)) {
                updatedLines.add(line);
            }
        }

        overwriteFile(FileUtil.getBookingFile(), updatedLines);
    }

    // Review Management
    public static void insertReview(Review review) throws IOException {
        String reviewData = String.join(":",
                review.getCategory(),
                review.getUsername(),
                String.valueOf(review.getRating()),
                review.getComment()
        );
        FileUtil.writeFile(FileUtil.getReviewFile(), reviewData);
    }

    public static void deleteReview(String username, String category) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getReviewFile());
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 4 && !(parts[1].equals(username) && parts[0].equals(category))) {
                updatedLines.add(line);
            }
        }

        overwriteFile(FileUtil.getReviewFile(), updatedLines);
    }

    // Message Management
    public static void insertMessage(Message message) throws IOException {
        String messageData = String.join(":", message.getUsername(), message.getMessage());
        FileUtil.writeFile(FileUtil.getMessageFile(), messageData);
    }

    public static void deleteMessage(String username, String message) throws IOException {
        List<String> lines = FileUtil.readFile(FileUtil.getMessageFile());
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2 && !(parts[0].equals(username) && parts[1].equals(message))) {
                updatedLines.add(line);
            }
        }

        overwriteFile(FileUtil.getMessageFile(), updatedLines);
    }

    // Helper method to overwrite file with updated content
    private static void overwriteFile(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}