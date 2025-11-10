package com.school.midland.user.repository;

import com.school.midland.user.models.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {
    List<Syllabus> findByGradeLevelAndSubjectCode(String gradeLevel, String subjectCode);
    List<Syllabus> getSyllabusByGradeLevelAndSubjectName(String gradeLevel, String subjectName);
    List<Syllabus> getSyllabusByGradeLevel(String gradeLevel);
}
