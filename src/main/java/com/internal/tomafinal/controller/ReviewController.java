package com.internal.tomafinal.controller;

import com.internal.tomafinal.controller.model.ReviewDTO;
import com.internal.tomafinal.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PutMapping("/{nameFilm}")
    public ResponseEntity<ReviewDTO> postReview(@RequestBody ReviewDTO review, @PathVariable String nameFilm) {
        reviewService.putReview(nameFilm, review.getComment(), review.getRating());
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable String id) {
        if (reviewService.deleteReview(id)) {
            return "Review eliminada";
        }
        return "Review no ha sido eliminada";
    }

    @GetMapping("/randomReviews")
    public ResponseEntity<List<ReviewDTO>> getRandomReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getRandomReviews());
    }
}
