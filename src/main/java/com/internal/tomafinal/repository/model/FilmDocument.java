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

    public FilmDocument(String id, String name, Integer year, String director, String synopsis) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.synopsis = synopsis;
    }

    public String getId() {
        return id;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Integer getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }
}
