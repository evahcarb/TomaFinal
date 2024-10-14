package com.internal.tomafinal.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "films")
public class FilmDocument {
    @Id
    private String id;
    private String name;
    private Integer year;
    private String director;
    private String synopsis;
    private String genre;

    public FilmDocument(String name, Integer year, String director, String synopsis, String genre) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
        this.genre = genre;
    }

    public String getId() {
        return id;
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

    public String getGenre() {
        return genre;
    }
}
