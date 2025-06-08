package org.informatics.graduation.controller;

import jakarta.validation.Valid;
import org.informatics.graduation.model.Application;
import org.informatics.graduation.service.ApplicationService;
import org.informatics.graduation.service.StudentService;
import org.informatics.graduation.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@Validated
public class ApplicationController {
    private final ApplicationService service;
    public ApplicationController(ApplicationService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Application> create(@Valid @RequestBody Application app){
        return ResponseEntity.ok(service.create(app));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAll() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> update(@PathVariable Long id, @Valid @RequestBody Application app) {
        return ResponseEntity.ok(service.update(id, app));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // custom queries
    @GetMapping("/approved")
    public ResponseEntity<List<Application>> approved() {
        return ResponseEntity.ok(service.getAllApproved());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Application>> search(@RequestParam String topic) {
        return ResponseEntity.ok(service.searchByTopic(topic));
    }

    @GetMapping("/supervisor/{teacherId}")
    public ResponseEntity<List<Application>> bySupervisor(@PathVariable Long teacherId) {
        return ResponseEntity.ok(service.getApprovedBySupervisor(teacherId));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Application> approve(@PathVariable Long id) {
        Application app = service.approve(id);
        return ResponseEntity.ok(app);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Application> reject(@PathVariable Long id) {
        Application app = service.reject(id);
        return ResponseEntity.ok(app);
    }
}


