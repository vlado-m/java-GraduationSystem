package org.informatics.graduation.service;

import jakarta.persistence.EntityNotFoundException;
import org.informatics.graduation.model.Application;
import org.informatics.graduation.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository repository;
    public ApplicationServiceImpl(ApplicationRepository inRepository){
        this.repository = inRepository;
    }

    @Override
    public Application create(Application application) {
        return repository.save(application);
    }

    @Override
    public Optional<Application> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Application> getAllApplications() {
        return repository.findAll();
    }

    @Override
    public Application update(Long id, Application updated) {
        return repository.findById(id)
                .map(app -> {
                    app.setTopic(updated.getTopic());
                    app.setObjective(updated.getObjective());
                    app.setTasks(updated.getTasks());
                    app.setTechnologies(updated.getTechnologies());
                    app.setStudent(updated.getStudent());
                    app.setSupervisor(updated.getSupervisor());
                    app.setApproved(updated.isApproved());
                    return repository.save(app);
                })
                .orElseThrow(() -> new RuntimeException("Application not found: " + id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Application> getAllApproved() {
        return repository.findByApprovedIsTrue();
    }

    @Override
    public List<Application> searchByTopic(String keyword) {
        return repository.findByApprovedTrueAndTopicContainingIgnoreCase(keyword);
    }

    @Override
    public List<Application> getApprovedBySupervisor(Long idSupervisor) {
        return repository.findBySupervisorIdAndApprovedTrue(idSupervisor);
    }

    @Override
    public Application approve(Long id) {
        return repository.findById(id)
                .map(app -> {
                    app.setApproved(true);
                    return repository.save(app);
                })
                .orElseThrow(() ->
                        new EntityNotFoundException("Application not found: " + id)
                );
    }

    @Override
    public Application reject(Long id) {
        return repository.findById(id)
                .map(app -> {
                    app.setApproved(false);
                    return repository.save(app);
                })
                .orElseThrow(() ->
                        new EntityNotFoundException("Application not found: " + id)
                );
    }
}
