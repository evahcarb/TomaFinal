package com.internal.tomafinal.service;

import com.internal.tomafinal.repository.FilmMongoRepository;
import com.internal.tomafinal.repository.ReviewMongoRepository;
import com.internal.tomafinal.repository.model.FilmDocument;
import com.internal.tomafinal.repository.model.ReviewDocument;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewMongoRepository reviewRepository;
    private FilmMongoRepository filmRepository;

    public ReviewService(ReviewMongoRepository reviewRepository, FilmMongoRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
    }

    public boolean putReview(String nameFilm, String comment, Integer rating) {
        FilmDocument film = filmRepository.findByName(nameFilm);
        if (film == null) {
            return false;
        }
        ReviewDocument review = new ReviewDocument(film.getId(), comment, rating);
        reviewRepository.save(review);
        return true;
    }

    public boolean deleteReview(String id) {
        reviewRepository.deleteById(id);
        return true;
    }

}
