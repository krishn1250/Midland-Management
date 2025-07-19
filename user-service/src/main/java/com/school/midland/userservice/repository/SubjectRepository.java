package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Optional<Subject> findByCode(String code);

    List<Subject> findByGradeLevel(String gradeLevel);

    List<Subject> findByTeacherCode(String teacherCode);
    Optional<Subject> findBySubjectName(String subjectName);
}
