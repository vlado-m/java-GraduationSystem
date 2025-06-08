package org.informatics.graduation.service;

import org.informatics.graduation.model.Thesis;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ThesisService {
    Thesis create(Thesis thesis);
    Optional<Thesis> getByID(Long id);
    List<Thesis> getAll();
    Thesis update(Long id, Thesis thesis);
    void delete(Long id);
    void saveThesis(Thesis thesis, MultipartFile file) throws IOException;
    Thesis getById(Long id);
}
