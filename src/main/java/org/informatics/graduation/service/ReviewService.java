package org.informatics.graduation.service;

import org.informatics.graduation.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review create(Review review);
    Optional<Review> getByID(Long id);
    List<Review> getAllReviews();
    Review update(Long id, Review newReview);
    void delete(Long id);
    int countByPassedIsFalse();
}
