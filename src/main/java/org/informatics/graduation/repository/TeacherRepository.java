package org.informatics.graduation.repository;

import org.informatics.graduation.model.Teacher;
import org.informatics.graduation.model.TeacherPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByPosition(TeacherPosition position);
}
