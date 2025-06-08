package org.informatics.graduation.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.informatics.graduation.model.Thesis;
import org.informatics.graduation.service.ApplicationService;
import org.informatics.graduation.service.ThesisService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/theses")
@Validated
public class ThesisController {
    private final ThesisService service;
    private final ApplicationService applicationService;
    public ThesisController(ThesisService inService, ApplicationService inAppService){
        this.service = inService;
        this.applicationService = inAppService;
    }

    @PostMapping
    public ResponseEntity<Thesis> create(@Valid @RequestBody Thesis thesis) {
        return ResponseEntity.ok(service.create(thesis));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Thesis> getById(@PathVariable Long id) {
        return service.getByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Thesis>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Thesis> update(@PathVariable Long id, @Valid @RequestBody Thesis thesis) {
        return ResponseEntity.ok(service.update(id, thesis));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
