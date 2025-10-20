package com.school.midland.adminservice.service.student;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.commonlib.dtos.StudentDto;
import org.apache.catalina.User;

import java.util.List;

public interface StudentManageService {

    UserCreationResponse createStudent(StudentDto studentDto, String token);
    boolean deleteStudent(String email,String token);
    StudentDto updateStudent(String admissionNumber, StudentDto updatedDto);
    StudentDto getByAdmissionNumber(String admissionNumber);
    List<StudentDto> getAllStudents();
}
