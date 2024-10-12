package com.internal.tomafinal.controller.model;

public class ReviewDTO {
    private String comment;
    private Integer rating;
    private String user;

    public ReviewDTO(String comment, Integer rating, String user) {
        this.comment = comment;
        this.rating = rating;
        this.user = "anonymous";
    }

    public String getUser() {
        return user;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
