package org.informatics.graduation.service;

import org.informatics.graduation.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentService
{
    Student createStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    Student getStudentFromID(Long id);
    List<Student> getAllStudents();

    List<Student> getStudentsGraduatedBetween(LocalDate start, LocalDate end);

}
