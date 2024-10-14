package com.internal.tomafinal.controller.model;

import java.util.List;

public class FilmDTO {
    private String name;
    private Integer year;
    private String director;
    private String synopsis;
    private Genre genre;
    private Float avgRating;
    List<ReviewDTO> review;

    public enum Genre {
        ACTION,
        ADVENTURE,
        COMEDY,
        DRAMA,
        FANTASY,
        HORROR,
        MYSTERY,
        ROMANCE,
        SCIENCE_FICTION,
        THRILLER,
        DOCUMENTARY,
        ANIMATION,
        MUSICAL,
        CRIME,
        WAR,
        WESTERN,
        BIOGRAPHY,
        SPORT
    }

    public FilmDTO() {
    }

    public FilmDTO(String name, Integer year, String director, String synopsis, Genre genre, Float avgRating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
        this.genre = genre;
        this.avgRating = avgRating;
    }

    public FilmDTO(String name, Integer year, String director, String synopsis, Genre genre, Float avgRating, List<ReviewDTO> review) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
        this.genre = genre;
        this.avgRating = avgRating;
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

    public Genre getGenre() {
        return genre;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public List<ReviewDTO> getReview() {
        return review;
    }
}