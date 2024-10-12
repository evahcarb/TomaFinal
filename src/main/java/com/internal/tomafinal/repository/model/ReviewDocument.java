package com.internal.tomafinal.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "review")
public class ReviewDocument {
    @Id
    private String id;
    private String idFilm;
    private String comment;
    private Integer rating;

    public ReviewDocument(String id, String idFilm, String comment, Integer rating) {
        this.id = id;
        this.idFilm = idFilm;
        this.comment = comment;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getIdFilm() {
        return idFilm;
    }
}
