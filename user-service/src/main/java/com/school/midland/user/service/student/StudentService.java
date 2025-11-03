package com.school.midland.user.service.student;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.student.StudentDto;
import com.school.midland.user.dto.student.StudentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    boolean createStudent(StudentDto studentDto);
    StudentDto getStudentById(Long id);
    StudentDto getStudentByUid(UUID uid);
    StudentDto getStudentByAdmissionNumber(String admissionNumber);
    StudentDto getByStudentEmail(String email);
    StudentResponseDto updateStudent(String admissionNumber, StudentDto updatedDto);
    boolean deleteStudentByEmail(String  email);
    public PageResponse<StudentDto> getAllStudents(int page, int size, String sortBy);
    public PageResponse<StudentDto> getStudentsByGradeLevel(int page, int size, String sortBy,String gradeLevel);
    public PageResponse<StudentDto> getStudentsByAcademicYear(int page, int size, String sortBy,String academicYear);
    List<StudentDto> getStudentsGradeAndSection(String grade, String  section);
    StudentDto getCurrentStudent(String token);
}
