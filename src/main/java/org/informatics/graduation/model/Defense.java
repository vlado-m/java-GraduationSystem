package org.informatics.graduation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "defenses")
public class Defense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Fill out the defense date")
    @Future
    private LocalDate defenseDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "defense_students",
            joinColumns = @JoinColumn(name = "defense_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "defense_teachers",
            joinColumns = @JoinColumn(name = "defense_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers;
    private Integer grade;

    public Defense(){}

    public Long getId() {
        return id;
    }

    public LocalDate getDefenseDate() {
        return defenseDate;
    }

    public void setDefenseDate(LocalDate defenseDate) {
        this.defenseDate = defenseDate;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> committee) {
        this.teachers = committee;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
