package com.internal.tomafinal.repository;

import com.internal.tomafinal.repository.model.FilmDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FilmMongoRepository extends MongoRepository<FilmDocument, String> {
    FilmDocument findByName(String name);
}
