package com.school.midland.admin.service.student;

import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.client.student.dto.StudentDto;
import com.school.midland.admin.client.student.dto.StudentResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentManageService {

    RegisterResponse createStudent(StudentDto studentDto, String token);
    boolean deleteStudent(String email,String token);
    StudentResponseDto updateStudent(String admissionNumber, StudentDto updatedDto, String token);
    StudentResponseDto getByEmail(String email,String token);
    StudentResponseDto getByAdmissionNumber(String admissionNumber,String token);
    List<StudentResponseDto> getAllStudents(int page,int size,String sortBy, String token);
    List<StudentResponseDto> getByAcademicYear(int page,int size,String sortBy, String academicYear);
    List<StudentResponseDto> getByGradeLevel(int page,int size,String sortBy, String academicYear);
    List<StudentResponseDto> getByGradeAndSection(String grade,String section);
}
