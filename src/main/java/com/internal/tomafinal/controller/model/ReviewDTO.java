package com.internal.tomafinal.controller.model;

public class ReviewDTO {
    private String id;
    private String comment;
    private Integer rating;
    private String user;

    public ReviewDTO() {
    }

    public ReviewDTO(String id, String comment, Integer rating) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.user = "anonymous";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
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
