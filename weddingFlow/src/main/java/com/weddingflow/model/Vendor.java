package com.weddingflow.model;

public class Vendor {
    private String name;
    private String category;
    private double price;
    private String contact;

    public Vendor(String category, String name, double price, String contact) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (category == null || category.isEmpty()) throw new IllegalArgumentException("Category cannot be empty");
        if (contact == null || contact.isEmpty()) throw new IllegalArgumentException("Contact cannot be empty");
        this.name = name;
        this.category = category;
        this.price = price;
        this.contact = contact;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getContact() { return contact; }

    public String toFileString() {
        return String.join(":", name, category, String.valueOf(price), contact);
    }

    public static Vendor fromFileString(String line) {
        String[] parts = line.split(":");
        if (parts.length == 4) {
            return new Vendor(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
        }
        throw new IllegalArgumentException("Invalid vendor data: " + line);
    }
}