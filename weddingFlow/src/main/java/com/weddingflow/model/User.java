package com.weddingflow.model;

public class User {
    private String username;
    private String password;
    private String userType;

    public User(String username, String password, String userType) {
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username cannot be empty");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password cannot be empty");
        if (userType == null || userType.isEmpty()) throw new IllegalArgumentException("User type cannot be empty");
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getUserType() { return userType; }

    public String toFileString() {
        return String.join(":", username, password, userType);
    }

    public static User fromFileString(String line) {
        String[] parts = line.split(":");
        if (parts.length >= 2) {
            String username = parts[0];
            String password = parts[1];
            String userType = parts.length == 3 ? parts[2] : "Basic";
            return new User(username, password, userType);
        }
        throw new IllegalArgumentException("Invalid user data: " + line);
    }
}