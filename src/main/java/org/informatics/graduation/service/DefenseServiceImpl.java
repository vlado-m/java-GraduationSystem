package org.informatics.graduation.service;

import jakarta.persistence.EntityNotFoundException;
import org.informatics.graduation.model.Defense;
import org.informatics.graduation.model.Student;
import org.informatics.graduation.model.Teacher;
import org.informatics.graduation.repository.DefenseRepository;
import org.informatics.graduation.repository.StudentRepository;
import org.informatics.graduation.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DefenseServiceImpl implements DefenseService{
    private final DefenseRepository defenseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    public DefenseServiceImpl(DefenseRepository defenseRepository, StudentRepository studentRepository
                                , TeacherRepository teacherRepository){
        this.defenseRepository = defenseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Defense create(Defense defense) {
        return defenseRepository.save(defense);
    }

    @Override
    public Optional<Defense> getById(Long id) {
        return defenseRepository.findById(id);
    }

    @Override
    public List<Defense> getAll() {
        return defenseRepository.findAll();
    }

    @Override
    public Defense update(Long id, Defense updated) {
        return defenseRepository.findById(id)
                .map(def -> {
                    def.setDefenseDate(updated.getDefenseDate());
                    def.setGrade(updated.getGrade());
                    def.setStudents(updated.getStudents());
                    def.setTeachers(updated.getTeachers());
                    return defenseRepository.save(def);
                })
                .orElseThrow(() -> new RuntimeException("Defense " + id + " not found!"));
    }

    @Override
    public void delete(Long id) {
        defenseRepository.deleteById(id);
    }

    private Defense getExisting(Long id){
        return defenseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Defense " + id + " not found!"));
    }

    @Override
    public Defense scheduleDefense(LocalDate date, Set<Long> studentIds, Set<Long> committeeIds){
        Defense newDefense = new Defense();
        newDefense.setDefenseDate(date);

        Set<Student> students = new HashSet<>();
        for (Long studentID : studentIds){
            Student student = studentRepository.findById(studentID)
                                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

            students.add(student);
        }
        newDefense.setStudents(students);

        Set<Teacher> teachers = new HashSet<>();
        for (Long teacherID : committeeIds){
            Teacher teacher = teacherRepository.findById(teacherID)
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
            teachers.add(teacher);
        }
        newDefense.setTeachers(teachers);

        return defenseRepository.save(newDefense);
    }

    @Override
    public Defense addStudent(Long defenseId, Long studentId) {
        Defense defense = this.getExisting(defenseId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + studentId));
        defense.getStudents().add(student);
        return defenseRepository.save(defense);
    }

    @Override
    public Defense removeStudent(Long defenseId, Long studentID){
        Defense defense = getExisting(defenseId);
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id " + studentID));
        defense.getStudents().remove(student);
        return defenseRepository.save(defense);
    }

    @Override
    public Defense addTeacher(Long defenseId, Long teacherId) {
        Defense defense = getExisting(defenseId);
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id " + teacherId));
        defense.getTeachers().add(teacher);
        return defenseRepository.save(defense);
    }

    @Override
    public Defense removeTeacher(Long defenseId, Long teacherId) {
        Defense defense = getExisting(defenseId);
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id " + teacherId));
        defense.getTeachers().remove(teacher);
        return defenseRepository.save(defense);
    }
}
