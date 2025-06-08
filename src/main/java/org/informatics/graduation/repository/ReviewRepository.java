package org.informatics.graduation.repository;

import org.informatics.graduation.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Get amount of students who received a negative review
    int countByPassedIsFalse();
}
