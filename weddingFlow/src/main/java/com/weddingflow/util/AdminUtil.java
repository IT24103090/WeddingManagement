package com.weddingflow.util;

import com.weddingflow.model.User;
import com.weddingflow.model.Vendor;

import java.io.*;

public class AdminUtil {

    // User Management
    public static void insertUser(User user) throws IOException {
        String userData = user.toFileString();
        FileUtil.writeFile(FileUtil.getUserFile(), userData);
    }

    public static void updateUser(String oldUsername, User updatedUser) throws IOException {
        LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getUserFile());
        boolean found = false;

        String[] lineArray = lines.toArray(new String[0]);
        for (int i = 0; i < lineArray.length; i++) {
            User existingUser = User.fromFileString(lineArray[i]);
            if (existingUser.getUsername().equals(oldUsername)) {
                lines.update(lineArray[i], updatedUser.toFileString());
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("User not found: " + oldUsername);
        }

        FileUtil.overwriteFile(FileUtil.getUserFile(), lines);
    }

    public static void deleteUser(String username) throws IOException {
        LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getUserFile());
        String[] lineArray = lines.toArray(new String[0]);
        LinkedList<String> updatedLines = new LinkedList<>();

        for (String line : lineArray) {
            User user = User.fromFileString(line);
            if (!user.getUsername().equals(username)) {
                updatedLines.add(line);
            }
        }

        FileUtil.overwriteFile(FileUtil.getUserFile(), updatedLines);
    }

    // Vendor Management
    public static void insertVendor(Vendor vendor) throws IOException {
        String vendorData = vendor.toFileString();
        FileUtil.writeFile(FileUtil.getVendorFile(), vendorData);
    }

    public static void updateVendor(String oldName, Vendor updatedVendor) throws IOException {
        LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getVendorFile());
        boolean found = false;

        String[] lineArray = lines.toArray(new String[0]);
        for (int i = 0; i < lineArray.length; i++) {
            Vendor existingVendor = Vendor.fromFileString(lineArray[i]);
            if (existingVendor.getName().equals(oldName)) {
                lines.update(lineArray[i], updatedVendor.toFileString());
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Vendor not found: " + oldName);
        }

        FileUtil.overwriteFile(FileUtil.getVendorFile(), lines);
    }

    public static void deleteVendor(String name) throws IOException {
        LinkedList<String> lines = FileUtil.readFileAsLinkedList(FileUtil.getVendorFile());
        String[] lineArray = lines.toArray(new String[0]);
        LinkedList<String> updatedLines = new LinkedList<>();

        for (String line : lineArray) {
            Vendor vendor = Vendor.fromFileString(line);
            if (!vendor.getName().equals(name)) {
                updatedLines.add(line);
            }
        }

        FileUtil.overwriteFile(FileUtil.getVendorFile(), updatedLines);
    }
}