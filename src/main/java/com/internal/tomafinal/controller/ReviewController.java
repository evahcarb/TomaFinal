package com.internal.tomafinal.controller;

import com.internal.tomafinal.controller.model.ReviewDTO;
import com.internal.tomafinal.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PutMapping("/{nameFilm}")
    public String postReview(@RequestBody ReviewDTO review, @PathVariable String nameFilm) {
        if (reviewService.putReview(nameFilm, review.getComment(), review.getRating())) {
            return "Review añadida";
        }
        return "Película no encontrada";
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable String id) {
        if (reviewService.deleteReview(id)) {
            return "Review eliminada";
        }
        return "Review no ha sido eliminada";
    }
}
