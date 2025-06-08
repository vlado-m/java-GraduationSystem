package org.informatics.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    private LocalDate reviewDate = LocalDate.now();
    @NotBlank(message = "Fill out a review text.")
    private String text;

    @NotNull(message = "Select a review conclusion.")
    private Boolean passed = false;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Thesis thesis;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Teacher reviewer;

    public Review(){}

    public Long getId() {
        return id;
    }
    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getPassed() {
        return passed;
    }

    public void setPassed(boolean pass) {
        this.passed = pass;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public Teacher getReviewer() {
        return reviewer;
    }

    public void setReviewer(Teacher reviewer) {
        this.reviewer = reviewer;
    }

}
