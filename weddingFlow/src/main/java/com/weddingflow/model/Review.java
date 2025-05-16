package com.weddingflow.model;

public class Review {
    private String category;
    private String username;
    private int rating;
    private String comment;

    public Review(String category, String username, int rating, String comment) {
        this.category = category;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
    }

    public String getCategory() { return category; }
    public String getUsername() { return username; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}