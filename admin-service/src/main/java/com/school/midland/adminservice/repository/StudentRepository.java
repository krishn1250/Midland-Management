package com.school.midland.adminservice.repository;

import com.school.midland.adminservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByStudentId(Long studentId);
    Optional<Student> findByStudentUid(UUID studentUid);
    Optional<Student> findByAdmissionNumber(String admissionNumber);
    List<Student> findByGradeLevel(String gradeLevel);
    List<Student> findByAcademicYear(String academicYear);
}
