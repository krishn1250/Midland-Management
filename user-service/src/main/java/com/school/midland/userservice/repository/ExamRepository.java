package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExamRepository extends JpaRepository<Exams, Long> {
    List<Exams> findByGradeLevel(String gradeLevel);
}
