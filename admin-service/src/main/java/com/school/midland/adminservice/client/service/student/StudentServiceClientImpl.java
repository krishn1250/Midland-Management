package com.school.midland.adminservice.client.service.student;

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
    public StudentDto createUserRest(StudentDto studentDto) {

        return restTemplate.postForObject(userServiceBaseUrl + "users/student/create", studentDto, StudentDto.class);
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

    public StudentDto updateStudent(String admissionNumber, StudentDto updatedDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StudentDto> request = new HttpEntity<>(updatedDto, headers);

        ResponseEntity<StudentDto> response = restTemplate.exchange(
                userServiceBaseUrl + "users/student/update/" + admissionNumber,
                HttpMethod.PUT,
                request,
                StudentDto.class
        );

        return response.getBody();
    }

    public boolean deleteStudent(String admissionNumber) {
        ResponseEntity<Boolean> response = restTemplate.exchange(
                userServiceBaseUrl + "users/student/delete/" + admissionNumber,
                HttpMethod.DELETE,
                null,
                Boolean.class
        );
        return Boolean.TRUE.equals(response.getBody());
    }

}
