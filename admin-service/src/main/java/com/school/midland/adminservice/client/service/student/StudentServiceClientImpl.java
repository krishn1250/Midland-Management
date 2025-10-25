package com.school.midland.adminservice.client.service.student;

import com.school.midland.adminservice.client.service.student.dto.StudentResponseDto;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.commonlib.exception.AdminException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
@RequiredArgsConstructor
public class StudentServiceClientImpl implements  StudentServiceClient{

    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;

    @Override
    public boolean createUserRest(StudentDto studentDto) {
    HttpHeaders header=new HttpHeaders();
    header.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<StudentDto> entity=new HttpEntity<>(studentDto,header);
        return restTemplate.postForObject(userServiceBaseUrl + "users/student/create", studentDto, Boolean.class);
    }
    public List<StudentDto> getAllStudents() {
        ResponseEntity<StudentDto[]> response = restTemplate.getForEntity(
                userServiceBaseUrl + "users/student/all",
                StudentDto[].class
        );
        System.out.println("hiited");
        return response.getBody() != null ? Arrays.asList(response.getBody()) : new ArrayList<>();
    }

    public StudentDto getByAdmissionNumber(String admissionNumber) {
        return restTemplate.getForObject(
                userServiceBaseUrl + "users/student/admission/" + admissionNumber,
                StudentDto.class
        );
    }

    public StudentResponseDto updateStudent(String email, StudentDto updatedDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StudentDto> request = new HttpEntity<>(updatedDto, headers);

        ResponseEntity<StudentResponseDto> response = restTemplate.exchange(
                userServiceBaseUrl + "users/student/update/" + email,
                HttpMethod.PUT,
                request,
                StudentResponseDto.class
        );

        return response.getBody();
    }

    public boolean deleteStudent(String email) {
        ResponseEntity<Boolean> response = restTemplate.exchange(
                userServiceBaseUrl + "users/student/delete/" + email,
                HttpMethod.DELETE,
                null,
                Boolean.class
        );
        return Boolean.TRUE.equals(response.getBody());
    }

}
