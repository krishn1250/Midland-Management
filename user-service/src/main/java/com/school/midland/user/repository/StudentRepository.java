package com.school.midland.user.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.school.midland.user.models.Student;
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
    Page<Student> findAll(Pageable pageable);
    Page<Student> findByGradeLevel(String gradeLevel, Pageable pageable);
    Page<Student> findByAcademicYear(String academicYear,Pageable pageable);
    List<Student> findByGradeLevelAndSection(String grade, String section);
    List<Student> findByCity(String city);
Student findByUsernameAndAdmissionNumber(String username,String studentCode);
boolean deleteByAdmissionNumber(String admissionNumber);
boolean deleteBySchoolEmail(String email);

}
