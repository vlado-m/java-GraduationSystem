package org.informatics.graduation.service;

import org.informatics.graduation.model.Review;
import org.informatics.graduation.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;
    public ReviewServiceImpl(ReviewRepository inRepo){
        this.repository = inRepo;
    }

    @Override
    public Review create(Review review) {
        return repository.save(review);
    }

    @Override
    public Optional<Review> getByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    @Override
    public Review update(Long id, Review updated) {
        return repository.findById(id)
                .map(rv -> {
                    rv.setReviewDate(updated.getReviewDate());
                    rv.setText(updated.getText());
                    rv.setPassed(updated.getPassed());
                    rv.setThesis(updated.getThesis());
                    rv.setReviewer(updated.getReviewer());
                    return repository.save(rv);
                })
                .orElseThrow(() -> new RuntimeException("Review not found: " + id));
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public int countByPassedIsFalse(){
        return repository.countByPassedIsFalse();
    }
}
