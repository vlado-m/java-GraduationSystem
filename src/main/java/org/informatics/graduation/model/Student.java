package org.informatics.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Fill out a name")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Fill out a faculty number")
    @Pattern(regexp = "\\d{6}", message = "Faculty number must be 6 digits")
    @Column(unique = true, nullable = false)
    private String facultyNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Application> applications;

    @ManyToMany(mappedBy = "students")
    private Set<Defense> defenses;

    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public Long getId() {
        return id;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Set<Defense> getDefenses() {
        return defenses;
    }

    public void setDefenses(Set<Defense> defenses) {
        this.defenses = defenses;
    }
}
