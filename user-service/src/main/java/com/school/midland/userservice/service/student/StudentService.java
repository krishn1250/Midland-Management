package com.school.midland.userservice.service.student;

import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
//    StudentDto getStudentById(Long id);
//    StudentDto getStudentByUid(UUID uid);
//    StudentDto getStudentByAdmissionNumber(String admissionNumber);
//    StudentDto updateStudent(String admissionNumber, StudentDto updatedDto);
//    StudentDto deleteStudentById(Long id);
//    StudentDto getAllStudents();
//    StudentDto getStudentsByGradeLevel(String gradeLevel);
//    StudentDto getStudentsByAcademicYear(String academicYear);
//    Student findByGradeAndSection(String grade, String  section);
}
