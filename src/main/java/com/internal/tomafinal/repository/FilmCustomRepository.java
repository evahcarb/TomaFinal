package com.internal.tomafinal.repository;

import com.internal.tomafinal.repository.model.FilmDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class FilmCustomRepository {
    private MongoTemplate mongoTemplate;
    private FilmMongoRepository filmMongoRepository;

    public FilmCustomRepository(MongoTemplate mongoTemplate, FilmMongoRepository filmMongoRepository) {
        this.mongoTemplate = mongoTemplate;
        this.filmMongoRepository = filmMongoRepository;
    }

    public List<FilmDocument> findByDynamicFilter(String name, Integer year, String genre) {

        Criteria criteria = new Criteria();

        if (name != null && !name.isEmpty()) {
            criteria.and("name").regex(".*" + Pattern.quote(name) + ".*", "i");
        }

        if (year != null) {
            criteria.and("year").is(year);
        }

        if (genre != null && !genre.isEmpty()) {
            criteria.and("genre").regex("^" + Pattern.quote(genre) + "$", "i");
        }

        Query query = new Query(criteria);
        return mongoTemplate.find(query, FilmDocument.class);
    }


    public FilmDocument findByName(String name) {
        return filmMongoRepository.findByName(name);
    }

    public List<FilmDocument> findAll() {
        return filmMongoRepository.findAll();
    }

    public void delete(FilmDocument film) {
        filmMongoRepository.delete(film);
    }

    public void save(FilmDocument film) {
        filmMongoRepository.save(film);
    }


}
