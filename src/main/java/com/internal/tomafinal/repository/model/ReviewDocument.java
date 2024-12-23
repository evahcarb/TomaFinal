package com.internal.tomafinal.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class ReviewDocument {
    @Id
    private String id;
    private String idFilm;
    private String comment;
    private Float rating;

    public ReviewDocument(String idFilm, String comment, Float rating) {
        this.idFilm = idFilm;
        this.comment = comment;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public Float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getIdFilm() {
        return idFilm;
    }
}
