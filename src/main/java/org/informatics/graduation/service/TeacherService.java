package org.informatics.graduation.service;

import org.informatics.graduation.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Teacher create(Teacher teacher);
    Optional<Teacher> getById(Long id);
    List<Teacher> getAll();
    Teacher update(Long id, Teacher teacher);
    void delete(Long id);
}
