package com.school.midland.userservice.repository;

import com.school.midland.userservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository  extends JpaRepository<Student,Long> {
    Optional<Student> findByStudentId(Long studentId);
    Optional<Student> findByStudentUid(UUID studentUid);
    Optional<Student> findByAdmissionNumber(String admissionNumber);
    Optional<Student> findByUsername(String username);
    Optional<Student> findBySchoolEmail(String schoolEmail);
    List<Student> findByGradeLevel(String gradeLevel);
    List<Student> findByAcademicYear(String academicYear);
    List<Student> findByGradeLevelAndSection(String grade, String section);
    List<Student> findByCity(String city);
}
