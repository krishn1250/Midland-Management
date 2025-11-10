package com.school.midland.user.repository;

import com.school.midland.user.models.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findBySubjectCode(String subjectCode);
    boolean existsBySubjectCode(String subjectCode);

    List<Subject> findByGradeLevelAndTeacherCode(String gradeLevel, String teacherCode);

    @Query("SELECT s FROM Subject s WHERE " +
            "(:gradeLevel IS NULL OR s.gradeLevel = :gradeLevel) AND " +
            "(:teacherCode IS NULL OR s.teacherCode = :teacherCode) AND " +
            "(:curriculumType IS NULL OR s.curriculumType = :curriculumType) AND " +
            "(:schoolCode IS NULL OR s.schoolCode = :schoolCode)")
    Page<Subject> searchSubjects(String gradeLevel, String teacherCode, String curriculumType, String schoolCode, Pageable pageable);

    boolean existsBySubjectCodeAndGradeLevel(String subjectCode, String gradeLevel);
}
