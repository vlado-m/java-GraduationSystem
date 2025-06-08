package org.informatics.graduation.service;

import org.informatics.graduation.model.Student;
import org.informatics.graduation.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        // find student in repository first
        Student existing = getStudentFromID(id);

        // set name
        //set facnumber

        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getStudentFromID(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found by ID!"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsGraduatedBetween(LocalDate start, LocalDate end){
        return studentRepository.getStudentsGraduatedBetween(start, end);
    }

}
