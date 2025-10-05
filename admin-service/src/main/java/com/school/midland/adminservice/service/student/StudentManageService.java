package com.school.midland.adminservice.service.student;

import com.school.midland.commonlib.dtos.StudentDto;

import java.util.List;

public interface StudentManageService {

    StudentDto createStudent(StudentDto studentDto,String token);
    boolean deleteStudent(String admissionNumber);
    StudentDto updateStudent(String admissionNumber, StudentDto updatedDto);
    StudentDto getByAdmissionNumber(String admissionNumber);
    List<StudentDto> getAllStudents();
}
