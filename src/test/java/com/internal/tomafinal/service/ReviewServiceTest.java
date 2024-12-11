package com.internal.tomafinal.service;

import com.internal.tomafinal.controller.model.ReviewDTO;
import com.internal.tomafinal.repository.FilmMongoRepository;
import com.internal.tomafinal.repository.ReviewMongoRepository;
import com.internal.tomafinal.repository.model.FilmDocument;
import com.internal.tomafinal.repository.model.ReviewDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewMongoRepository reviewRepository;

    @Mock
    private FilmMongoRepository filmRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPutReview_Success() {
        String filmName = "Test Film";
        String comment = "Great movie!";
        Float rating = 4.5f;
        Integer year = 2000;

        FilmDocument filmDocument = new FilmDocument("film123", filmName, year, null, null, null, null);
        when(filmRepository.findByName(filmName)).thenReturn(filmDocument);

        boolean result = reviewService.putReview(filmName, comment, rating);

        assertTrue(result);
        verify(filmRepository, times(1)).findByName(filmName);
        verify(reviewRepository, times(1)).save(any(ReviewDocument.class));
    }

    @Test
    void testPutReview_FilmNotFound() {
        String filmName = "Nonexistent Film";
        String comment = "Not great.";
        Float rating = 2.0f;

        when(filmRepository.findByName(filmName)).thenReturn(null);

        boolean result = reviewService.putReview(filmName, comment, rating);

        assertFalse(result);
        verify(filmRepository, times(1)).findByName(filmName);
        verifyNoInteractions(reviewRepository);
    }

    @Test
    void testDeleteReview_Success() {
        String reviewId = "review123";
        boolean result = reviewService.deleteReview(reviewId);

        assertTrue(result);
        verify(reviewRepository, times(1)).deleteById(reviewId);
    }

    @Test
    void testGetRandomReviews() {
        List<ReviewDocument> reviews = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ReviewDocument review = new ReviewDocument("film" + i, "Comment " + i, (float) i);
            reviews.add(review);
        }

        when(reviewRepository.findAll()).thenReturn(reviews);

        List<ReviewDTO> randomReviews = reviewService.getRandomReviews();

        assertNotNull(randomReviews);
        assertTrue(randomReviews.size() <= 6);
        verify(reviewRepository, times(1)).findAll();
    }
}
