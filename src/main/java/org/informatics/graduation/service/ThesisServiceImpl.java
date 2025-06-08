package org.informatics.graduation.service;

import jakarta.persistence.EntityNotFoundException;
import org.informatics.graduation.model.Thesis;
import org.informatics.graduation.repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ThesisServiceImpl implements ThesisService {
    @Value("${file.upload-dir}")
    private String uploadDir;
    private final ThesisRepository repository;

    public ThesisServiceImpl(ThesisRepository inRepo){
        this.repository = inRepo;

    }

    @Override
    public Thesis create(Thesis thesis){
        return repository.save(thesis);
    }

    @Override
    public Optional<Thesis> getByID(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Thesis> getAll() {
        return repository.findAll();
    }
    @Override
    public Thesis update(Long id, Thesis updated) {
        return repository.findById(id)
                .map(th -> {
                    th.setTitle(updated.getTitle());
                    th.setText(updated.getText());
                    th.setUploadDate(updated.getUploadDate());
                    th.setApplication(updated.getApplication());
                    return repository.save(th);
                })
                .orElseThrow(() -> new RuntimeException("Thesis not found: " + id));
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveThesis(Thesis thesis, MultipartFile file) throws IOException {
        // store the file
        if (file != null && !file.isEmpty()) {
            // ensure upload dir exists
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            Path target = uploadPath.resolve(originalFilename);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            thesis.setFileName(originalFilename);
        }
        repository.save(thesis);
    }

    @Override
    @Transactional(readOnly = true)
    public Thesis getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Thesis not found: " + id));
    }

}
