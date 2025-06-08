package org.informatics.graduation.service;

import org.informatics.graduation.model.Teacher;
import org.informatics.graduation.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;
    public TeacherServiceImpl(TeacherRepository repo){
        this.repository = repo;
    }

    @Override
    public Teacher create(Teacher teacher) {
        return repository.save(teacher);
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return repository.findAll();
    }

    @Override
    public Teacher update(Long id, Teacher updated) {
        return repository.findById(id)
                .map(t -> {
                    t.setName(updated.getName());
                    t.setPosition(updated.getPosition());
                    return repository.save(t);
                })
                .orElseThrow(() -> new RuntimeException("Teacher not found: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
