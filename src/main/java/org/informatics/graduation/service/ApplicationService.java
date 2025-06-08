package org.informatics.graduation.service;

import org.informatics.graduation.model.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Application create(Application application);
    Optional<Application> getById(Long id);
    List<Application> getAllApplications();
    Application update(Long id, Application application);
    void delete(Long id);

    List<Application> getAllApproved();
    List<Application> searchByTopic(String keyword);
    List<Application> getApprovedBySupervisor(Long idSupervisor);

    Application approve(Long id);
    Application reject(Long id);
}
