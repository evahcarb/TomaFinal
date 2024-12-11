package com.internal.tomafinal.controller;

import com.internal.tomafinal.controller.model.ReviewDTO;
import com.internal.tomafinal.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostReview() {
        ReviewDTO reviewDTO = new ReviewDTO("1", "Great movie!", 4.5f);
        String nameFilm = "Film1";

        ResponseEntity<ReviewDTO> response = reviewController.postReview(reviewDTO, nameFilm);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reviewDTO, response.getBody());
        verify(reviewService, times(1)).putReview(nameFilm, reviewDTO.getComment(), reviewDTO.getRating());
    }

    @Test
    void testDeleteReview_Success() {
        String id = "1";
        when(reviewService.deleteReview(id)).thenReturn(true);

        String result = reviewController.deleteReview(id);

        assertNotNull(result);
        assertEquals("Review eliminada", result);
        verify(reviewService, times(1)).deleteReview(id);
    }

    @Test
    void testDeleteReview_Failure() {
        String id = "1";
        when(reviewService.deleteReview(id)).thenReturn(false);

        String result = reviewController.deleteReview(id);

        assertNotNull(result);
        assertEquals("Review no ha sido eliminada", result);
        verify(reviewService, times(1)).deleteReview(id);
    }

    @Test
    void testGetRandomReviews() {
        List<ReviewDTO> mockReviews = List.of(
                new ReviewDTO("1", "Great movie!", 4.5f),
                new ReviewDTO("2", "Not bad", 3.0f)
        );
        when(reviewService.getRandomReviews()).thenReturn(mockReviews);

        ResponseEntity<List<ReviewDTO>> response = reviewController.getRandomReviews();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(reviewService, times(1)).getRandomReviews();
    }
}
