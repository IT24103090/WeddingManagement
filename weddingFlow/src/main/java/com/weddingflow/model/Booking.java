package com.weddingflow.model;

public class Booking {
    private String username;
    private String orderId;
    private String item;
    private int quantity;
    private double price;
    private String status;
    private String eventType;
    private String eventDate;

    public Booking(String username, String orderId, String item, int quantity, double price, String status, String eventType, String eventDate) {
        this.username = username;
        this.orderId = orderId;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }
}