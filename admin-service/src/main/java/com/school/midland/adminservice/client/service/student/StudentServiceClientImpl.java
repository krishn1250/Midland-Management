package com.school.midland.adminservice.client.service.student;

import com.school.midland.commonlib.dtos.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class StudentServiceClientImpl implements  StudentServiceClient{

    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;

    @Override
    public StudentDto createUserRest(StudentDto studentDto) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", studentDto.getUsername());
        payload.put("studentUid",studentDto.getStudentUid());
        payload.put("password", studentDto.getPassword());
        payload.put("firstName", studentDto.getFirstName());
        payload.put("lastName", studentDto.getLastName());
        payload.put("fullName", studentDto.getFullName());
        payload.put("admissionNumber", studentDto.getAdmissionNumber());
        payload.put("rollNo", studentDto.getRollNo());
        payload.put("gender", studentDto.getGender());
        payload.put("dateOfBirth", studentDto.getDateOfBirth());
        payload.put("bloodGroup", studentDto.getBloodGroup());
        payload.put("nationality", studentDto.getNationality());
        payload.put("motherTongue", studentDto.getMotherTongue());
        payload.put("languagePreference", studentDto.getLanguagePreference());
        payload.put("gradeLevel", studentDto.getGradeLevel());
        payload.put("section", studentDto.getSection());
        payload.put("academicYear", studentDto.getAcademicYear());
        payload.put("admissionDate", studentDto.getAdmissionDate());
        payload.put("status", studentDto.getStatus());
        payload.put("profileImage", studentDto.getProfileImage());
        payload.put("address", studentDto.getAddress());
        payload.put("city", studentDto.getCity());
        payload.put("state", studentDto.getState());
        payload.put("country", studentDto.getCountry());
        payload.put("pinCode", studentDto.getPinCode());
        payload.put("phoneNumber", studentDto.getPhoneNumber());
        payload.put("schoolEmail", studentDto.getSchoolEmail());
        payload.put("personalEmail", studentDto.getPersonalEmail());
        payload.put("guardianName", studentDto.getGuardianName());
        payload.put("guardianRelation", studentDto.getGuardianRelation());
        payload.put("guardianContact", studentDto.getGuardianContact());

        return restTemplate.postForObject(userServiceBaseUrl + "users/student/create", payload, StudentDto.class);

    }

}
