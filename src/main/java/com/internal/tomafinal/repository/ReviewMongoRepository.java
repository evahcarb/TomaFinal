package com.internal.tomafinal.repository;

import com.internal.tomafinal.repository.model.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {

    //Los métodos son heredados de MongoRepository y sus otras intefaces.

}
