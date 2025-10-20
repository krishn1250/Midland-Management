package com.school.midland.adminservice.client.service.teacher;

import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.commonlib.dtos.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherServiceClientImpl implements TeacherServiceClient{

    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;
    
    @Override
    public Boolean createUserRest(TeacherDto teacherDto) {
        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TeacherDto> entity= new HttpEntity<>(teacherDto,header);
        return restTemplate.postForObject(userServiceBaseUrl + "users/teachers/create", entity, Boolean.class);
    }

    @Override
    public TeacherDto updateTeacherRest(String teacherCode,TeacherDto teacherDto) {
        restTemplate.put(userServiceBaseUrl + "users/teachers/update/" + teacherCode, teacherDto);
        return  teacherDto;
    }

    @Override
    public List<TeacherDto> getAllTeachersRest() {
        ResponseEntity<TeacherDto[]> getTeachers=restTemplate.getForEntity(
          userServiceBaseUrl+"users/teachers/all",
                TeacherDto[].class
        );
        return getTeachers.getBody()!=null ? Arrays.asList(getTeachers.getBody()):new ArrayList<>();
    }

    @Override
    public TeacherDto getTeacherByCodeRest(String teacherCode) {

        return restTemplate.getForObject(userServiceBaseUrl+"users/teachers/code/"+teacherCode, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> getTeacherByDepartment(String department) {
        ResponseEntity<TeacherDto[]> response=restTemplate.getForEntity(
                userServiceBaseUrl+"users/teachers/department/"+department,TeacherDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    @Override
    public TeacherDto getTeacherBySubjectCode(String subjectCode) {
        return restTemplate.getForObject(
                userServiceBaseUrl + "users/teachers/subject/" + subjectCode,
                TeacherDto.class
        );
    }

    @Override
    public boolean deleteTeacher(String email) {
        System.out.println("hii");
        restTemplate.delete(userServiceBaseUrl + "users/teachers/delete/" + email);
        return true;
    }

    @Override
    public TeacherDto findByUsername(String username) {
        return restTemplate.getForObject(
                userServiceBaseUrl + "users/teachers/username/" + username, // ⚠️ See note below
                TeacherDto.class
        );
    }


}
