package com.school.midland.userservice.service.student;

import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.dto.student.StudentResponseDto;
import com.school.midland.userservice.models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    boolean createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long id);
    StudentDto getStudentByUid(UUID uid);
    StudentDto getStudentByAdmissionNumber(String admissionNumber);
    StudentResponseDto updateStudent(String admissionNumber, StudentDto updatedDto);
    boolean deleteStudentBySchoolEmail(String  email);
    List<StudentDto> getAllStudents();
    List<StudentDto> getStudentsByGradeLevel(String gradeLevel);
    List<StudentDto> getStudentsByAcademicYear(String academicYear);
    List<StudentDto> getStudentsGradeAndSection(String grade, String  section);
    StudentDto getCurrentStudent(String token);
}
