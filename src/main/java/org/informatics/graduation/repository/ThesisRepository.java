package org.informatics.graduation.repository;

import org.informatics.graduation.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // <- needed? to be tested
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
}
