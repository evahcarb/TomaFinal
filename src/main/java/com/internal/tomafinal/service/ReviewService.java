package com.internal.tomafinal.service;

import com.internal.tomafinal.controller.model.ReviewDTO;
import com.internal.tomafinal.repository.FilmMongoRepository;
import com.internal.tomafinal.repository.ReviewMongoRepository;
import com.internal.tomafinal.repository.model.FilmDocument;
import com.internal.tomafinal.repository.model.ReviewDocument;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {
    private ReviewMongoRepository reviewRepository;
    private FilmMongoRepository filmRepository;

    public ReviewService(ReviewMongoRepository reviewRepository, FilmMongoRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
    }

    public boolean putReview(String nameFilm, String comment, Float rating) {
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

    public List<ReviewDTO> getRandomReviews() {
        List<ReviewDocument> reviews = reviewRepository.findAll();
        Collections.shuffle(reviews);
        List<ReviewDocument> randomReviews = reviews.stream().limit(6).toList();
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (ReviewDocument i : randomReviews) {
            ReviewDTO reviewDTO = new ReviewDTO(i.getId(), i.getComment(), i.getRating());
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }
}
