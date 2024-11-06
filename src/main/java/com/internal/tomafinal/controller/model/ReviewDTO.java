package com.internal.tomafinal.controller.model;

public class ReviewDTO {
    private String id;
    private String comment;
    private Float rating;
    private String user;

    public ReviewDTO() {
    }

    public ReviewDTO(String id, String comment, Float rating) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.user = "anonymous";
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(Float rating) {
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

    public Float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
