package com.weddingflow.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String USER_FILE = "C:\\Users\\User\\IdeaProjects\\WeddingManagement\\weddingFlow\\src\\main\\resources\\data\\users.txt";
    private static final String VENDOR_FILE = "C:\\Users\\User\\IdeaProjects\\WeddingManagement\\weddingFlow\\src\\main\\resources\\data\\vendors.txt";
    private static final String BOOKING_FILE = "C:\\Users\\User\\IdeaProjects\\WeddingManagement\\weddingFlow\\src\\main\\resources\\data\\bookings.txt";
    private static final String REVIEW_FILE = "C:\\Users\\User\\IdeaProjects\\WeddingManagement\\weddingFlow\\src\\main\\resources\\data\\reviews.txt";
    private static final String MESSAGE_FILE = "C:\\Users\\User\\IdeaProjects\\WeddingManagement\\weddingFlow\\src\\main\\resources\\data\\messages.txt";

    public static List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
    public static LinkedList<String> readFileAsLinkedList(String filePath) throws IOException {
        LinkedList<String> lines = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void writeFile(String filePath, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        }
    }
    public static void writeFile(String filePath, List<String> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    public static void overwriteFile(String filePath, LinkedList<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String[] lineArray = lines.toArray(new String[0]);
            for (String line : lineArray) {
                writer.write(line);
                writer.newLine();
            }
        }
    }


    public static String getUserFile() { return USER_FILE; }
    public static String getVendorFile() { return VENDOR_FILE; }
    public static String getBookingFile() { return BOOKING_FILE; }
    public static String getReviewFile() { return REVIEW_FILE; }
    public static String getMessageFile() { return MESSAGE_FILE; }
}