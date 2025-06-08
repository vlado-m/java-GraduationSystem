package org.informatics.graduation.controller;

import jakarta.validation.Valid;
import org.informatics.graduation.model.Teacher;
import org.informatics.graduation.model.TeacherPosition;
import org.informatics.graduation.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Validated
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@Valid @RequestBody Teacher teacher) {
        return ResponseEntity.ok(service.create(teacher));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id,
                                          @Valid @RequestBody Teacher teacher) {
        return ResponseEntity.ok(service.update(id, teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-position/{position}")
    public ResponseEntity<List<Teacher>> byPosition(@PathVariable TeacherPosition position) {
        return ResponseEntity.ok(service.getAll()
                .stream()
                .filter(t -> t.getPosition() == position)
                .toList());
    }
}
