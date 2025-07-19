package com.school.midland.adminservice.client.service.teacher;

import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.commonlib.dtos.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherServiceClientImpl implements TeacherServiceClient{

    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;
    
    @Override
    public TeacherDto createUserRest(TeacherDto teacherDto) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", teacherDto.getUsername());
        payload.put("teacherUid", teacherDto.getTeacherUid());
        payload.put("teacherCode", teacherDto.getTeacherCode());
        payload.put("firstName", teacherDto.getFirstName());
        payload.put("lastName", teacherDto.getLastName());
        payload.put("password", teacherDto.getPassword());
        payload.put("personalEmail", teacherDto.getPersonalEmail());
        payload.put("schoolEmail", teacherDto.getSchoolEmail());
        payload.put("phoneNumber", teacherDto.getPhoneNumber());
        payload.put("qualification", teacherDto.getQualification());
        payload.put("department", teacherDto.getDepartment());
        payload.put("designation", teacherDto.getDesignation());
        payload.put("profileImage", teacherDto.getProfileImage());
        payload.put("joinDate", teacherDto.getJoinDate());

        return restTemplate.postForObject(userServiceBaseUrl + "users/teacher/create", payload, TeacherDto.class);
    }


}
