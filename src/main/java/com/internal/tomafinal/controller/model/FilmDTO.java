package com.internal.tomafinal.controller.model;

import java.util.List;

public class FilmDTO {
    private String name;
    private Integer year;
    private String director;
    private String synopsis;
    private GenreDTO genre;
    private Float avgRating;
    List<ReviewDTO> review;
    private String urlFilm;
    private List<String> providerNames;

    public enum GenreDTO {
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
        SPORT;

        public static GenreDTO fromString(String value) {
            for (GenreDTO v : GenreDTO.values()) {
                if (value.toUpperCase().equals(v.name())) {
                    return v;
                }
            }
            return null;
        }
    }

    public FilmDTO() {
    }

    public FilmDTO(String name, Integer year, String director, String synopsis, GenreDTO genre, Float avgRating, String urlFilm) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
        this.genre = genre;
        this.avgRating = avgRating;
        this.urlFilm = urlFilm;
    }

    public FilmDTO(String name, Integer year, String director, String synopsis, GenreDTO genre, Float avgRating, List<ReviewDTO> review, String urlFilm) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
        this.genre = genre;
        this.avgRating = avgRating;
        this.review = review;
        this.urlFilm = urlFilm;
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

    public GenreDTO getGenre() {
        return genre;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public List<ReviewDTO> getReview() {
        return review;
    }

    public String getUrlFilm() {
        return urlFilm;
    }

    public List<String> getProviderNames() {
        return providerNames;
    }

    public void setProviderNames(List<String> providerNames) {
        this.providerNames = providerNames;
    }

    public void setUrlFilm(String urlFilm) {
        this.urlFilm = urlFilm;
    }
}