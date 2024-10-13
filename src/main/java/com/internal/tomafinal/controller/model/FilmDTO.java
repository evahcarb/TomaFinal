package com.internal.tomafinal.controller.model;

import java.util.List;

public class FilmDTO {
    private String name;
    private Integer year;
    private String director;
    private String synopsis;
    List<ReviewDTO> review;

    public FilmDTO() {
    }

    public FilmDTO(String name, Integer year, String director, String synopsis) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
    }

    public FilmDTO(String name, Integer year, String director, String synopsis, List<ReviewDTO> review) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
        this.review = review;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setReview(List<ReviewDTO> review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public List<ReviewDTO> getReview() {
        return review;
    }
}
