package org.informatics.graduation.service;

import org.informatics.graduation.model.Defense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DefenseService {
    Defense create(Defense defense);
    Optional<Defense> getById(Long id);
    List<Defense> getAll();
    Defense update(Long id, Defense defense);
    void delete(Long id);

    Defense scheduleDefense(LocalDate date, Set<Long> studentIds, Set<Long> committeeIds);
    Defense addStudent(Long targetDefenseId, Long studentId);
    Defense removeStudent(Long targetDefenseId, Long studentId);
    Defense addTeacher(Long targetDefenseId, Long teacherId);
    Defense removeTeacher(Long targetDefenseId, Long teacherId);
}
