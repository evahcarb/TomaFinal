package com.internal.tomafinal.repository;

import com.internal.tomafinal.repository.model.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {

    List<ReviewDocument> findAllByIdFilm(String idFilm);

}
