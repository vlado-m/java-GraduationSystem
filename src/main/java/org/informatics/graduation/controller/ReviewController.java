package org.informatics.graduation.controller;

import jakarta.validation.Valid;
import org.informatics.graduation.model.Review;
import org.informatics.graduation.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Validated
public class ReviewController {
    private final ReviewService service;
    public ReviewController(ReviewService inService){
        this.service = inService;
    }

    @PostMapping
    public ResponseEntity<Review> create(@Valid @RequestBody Review review) {
        Review saved = service.create(review);
        return ResponseEntity.created(URI.create("/api/reviews/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getById(@PathVariable Long id) {
        return service.getByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAll() {
        return ResponseEntity.ok(service.getAllReviews());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @Valid @RequestBody Review review) {
        return ResponseEntity.ok(service.update(id, review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/negative-count")
    public ResponseEntity<Integer> countByPassedIsFalse() {
        return ResponseEntity.ok(service.countByPassedIsFalse());
    }
}
