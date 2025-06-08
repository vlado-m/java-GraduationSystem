package org.informatics.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Fill out a name")
    @Size(max = 100)
    private String name;

    @NotNull(message = "Select a position")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeacherPosition position;

    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL)
    private Set<Application> applications;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @ManyToMany(mappedBy = "teachers")
    private Set<Defense> defenses;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherPosition getPosition() {
        return position;
    }

    public void setPosition(TeacherPosition position) {
        this.position = position;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Defense> getDefenses() {
        return defenses;
    }

    public void setDefenses(Set<Defense> defenses) {
        this.defenses = defenses;
    }
}
