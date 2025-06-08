package org.informatics.graduation.repository;

import org.informatics.graduation.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /* Get all students who graduated between start and end date
    *  graduated == Defense.Grade != null && Defense.Grade > 2  */
    @Query("SELECT DISTINCT s FROM Defense d JOIN d.students s " +
            "WHERE d.defenseDate BETWEEN :start AND :end " +
            "AND d.grade IS NOT NULL AND d.grade > 2")
    List<Student> getStudentsGraduatedBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
