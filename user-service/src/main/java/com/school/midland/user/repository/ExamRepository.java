package com.school.midland.user.repository;

import com.school.midland.user.models.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exams, Long> {

    @Query("""
        SELECT e FROM Exams e 
        WHERE e.gradeLevel = :gradeLevel 
          AND e.isActive = true
          AND ((e.startTime < :endTime AND e.endTime > :startTime))
    """)
    List<Exams> findActiveExamsByGradeLevel(String gradeLevel, LocalDateTime startTime, LocalDateTime endTime);

    List<Exams> findByGradeLevel(String gradeLevel);
}
