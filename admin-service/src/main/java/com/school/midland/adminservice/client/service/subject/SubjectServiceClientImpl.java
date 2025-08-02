package com.school.midland.adminservice.client.service.subject;

import com.school.midland.commonlib.dtos.SubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectServiceClientImpl implements SubjectServiceClient {

    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;

    @Override
    public SubjectDto createSubject(SubjectDto dto) {
        String url = userServiceBaseUrl + "users/subjects/create";
        ResponseEntity<SubjectDto> response = restTemplate.postForEntity(url, dto, SubjectDto.class);
        return response.getBody();
    }

    @Override
    public List<SubjectDto> createSubjects(List<SubjectDto> subjectDtos) {
        String url = userServiceBaseUrl + "users/subjects/list/create";
        ResponseEntity<SubjectDto[]> response=restTemplate.postForEntity(url,subjectDtos,SubjectDto[].class);
        return response.getBody()!=null?Arrays.asList(response.getBody()):new ArrayList<>();
    }

    @Override
    public SubjectDto updateSubject(String subjectCode, SubjectDto dto) {
        String url = userServiceBaseUrl + "users/subjects/update/" + subjectCode;
        HttpEntity<SubjectDto> entity = new HttpEntity<>(dto);
        ResponseEntity<SubjectDto> response = restTemplate.exchange(url, HttpMethod.PUT, entity, SubjectDto.class);
        return response.getBody();
    }

    @Override
    public boolean deleteSubject(String code) {
        String url = userServiceBaseUrl + "users/subjects/delete/" + code;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        return response.getBody() != null && response.getBody().contains("deleted");
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        String url = userServiceBaseUrl + "users/subjects/all";
        ResponseEntity<SubjectDto[]> response = restTemplate.getForEntity(url, SubjectDto[].class);
        return response.getBody()!=null?Arrays.asList(response.getBody()):new ArrayList<>();
    }

    @Override
    public List<SubjectDto> getSubjectsByGrade(String gradeLevel) {
        String url = userServiceBaseUrl + "users/subjects/grade/" + gradeLevel;
        ResponseEntity<SubjectDto[]> response = restTemplate.getForEntity(url, SubjectDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<SubjectDto> getSubjectsByTeacher(String teacherCode) {
        String url = userServiceBaseUrl + "users/subjects/teacher/" + teacherCode;
        ResponseEntity<SubjectDto[]> response = restTemplate.getForEntity(url, SubjectDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public SubjectDto getSubjectByCode(String code) {
        String url = userServiceBaseUrl + "users/subjects/subject/" + code;
        ResponseEntity<SubjectDto> response = restTemplate.getForEntity(url, SubjectDto.class);
        return response.getBody();
    }
}