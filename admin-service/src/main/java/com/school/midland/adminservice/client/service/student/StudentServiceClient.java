package com.school.midland.adminservice.client.service.student;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;

import com.school.midland.commonlib.dtos.StudentDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentServiceClient {
    StudentDto createUserRest(@RequestBody StudentDto userCreationRequest);
    public boolean deleteStudent(String admissionNumber);
    public StudentDto updateStudent(String admissionNumber, StudentDto updatedDto);
    public StudentDto getByAdmissionNumber(String admissionNumber);
    public List<StudentDto> getAllStudents();

}
