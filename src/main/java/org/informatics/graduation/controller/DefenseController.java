package org.informatics.graduation.controller;

import jakarta.validation.Valid;
import org.informatics.graduation.model.Defense;
import org.informatics.graduation.service.DefenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/defenses")
@Validated
public class DefenseController {
    private final DefenseService service;

    @Autowired
    public DefenseController(DefenseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Defense> create(@Valid @RequestBody Defense defense) {
        Defense saved = service.create(defense);
        return ResponseEntity
                .created(URI.create("/api/defenses/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Defense> getById(@PathVariable Long id)
    {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Defense>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Defense> update(@PathVariable Long id,@Valid @RequestBody Defense defense){
        return ResponseEntity.ok(service.update(id, defense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/schedule")
    public ResponseEntity<Defense> schedule(
            @RequestParam LocalDate date,
            @RequestParam Set<Long> studentIds,
            @RequestParam Set<Long> teacherIds
    ) {
        Defense def = service.scheduleDefense(date, studentIds, teacherIds);
        return ResponseEntity
                .created(URI.create("/api/defenses/" + def.getId()))
                .body(def);
    }

    @PutMapping("/{id}/students/{studentId}")
    public ResponseEntity<Defense> addStudent(
            @PathVariable Long id,
            @PathVariable Long studentId
    ) {
        Defense def = service.addStudent(id, studentId);
        return ResponseEntity.ok(def);
    }

    @DeleteMapping("/{id}/students/{studentId}")
    public ResponseEntity<Defense> removeStudent(
            @PathVariable Long id,
            @PathVariable Long studentId
    ) {
        Defense def = service.removeStudent(id, studentId);
        return ResponseEntity.ok(def);
    }

    @PutMapping("/{id}/teachers/{teacherId}")
    public ResponseEntity<Defense> addTeacher(
            @PathVariable Long id,
            @PathVariable Long teacherId
    ) {
        Defense def = service.addTeacher(id, teacherId);
        return ResponseEntity.ok(def);
    }

    @DeleteMapping("/{id}/teachers/{teacherId}")
    public ResponseEntity<Defense> removeTeacher(
            @PathVariable Long id,
            @PathVariable Long teacherId
    ) {
        Defense def = service.removeTeacher(id, teacherId);
        return ResponseEntity.ok(def);
    }
}
