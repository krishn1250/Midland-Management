package com.school.midland.adminservice.service.student;


import com.school.midland.adminservice.dto.StudentDto;
import com.school.midland.adminservice.models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {
    ResponseEntity<?> createStudent(StudentDto studentDto);
    ResponseEntity<?> getStudentById(Long id);
    ResponseEntity<?> getStudentByUid(UUID uid);
    ResponseEntity<?> getStudentByAdmissionNumber(String admissionNumber);
    ResponseEntity<?> updateStudent(String admissionNumber, StudentDto updatedDto);
    ResponseEntity<?> deleteStudentById(Long id);
    ResponseEntity<?> getAllStudents();
    ResponseEntity<?> getStudentsByGradeLevel(String gradeLevel);
    ResponseEntity<?> getStudentsByAcademicYear(String academicYear);
}
