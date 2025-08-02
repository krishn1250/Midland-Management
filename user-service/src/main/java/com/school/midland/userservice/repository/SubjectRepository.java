package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Optional<Subject> findBySubjectCode(String subjectCode);
boolean existsBySubjectCode(String subjectCode);
    List<Subject> findByGradeLevel(String gradeLevel);
    List<Subject> findByTeacherCode(String teacherCode);
    List<Subject> findBySubjectName(String subjectName);
}
