package org.informatics.graduation.repository;

import org.informatics.graduation.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApprovedIsTrue();
    List<Application> findByApprovedTrueAndTopicContainingIgnoreCase(String topic);
    List<Application> findBySupervisorIdAndApprovedTrue(Long supervisorId);
}
