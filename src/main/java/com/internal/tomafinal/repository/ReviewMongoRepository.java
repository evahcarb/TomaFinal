package com.internal.tomafinal.repository;

import com.internal.tomafinal.repository.model.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {

    //Los métodos son heredados de MongoRepository y sus otras intefaces.
    List<ReviewDocument> findAllByIdFilm(String idFilm);

}
