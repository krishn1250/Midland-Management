package com.school.midland.adminservice.client.service.attendance;

import com.school.midland.commonlib.dtos.AttendanceDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceClientImpl implements AttendanceServiceClient {

    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;

    @Override
    public List<AttendanceDto> getAttendanceForTeacher(String teacherCode, LocalDate date) {
        String url = UriComponentsBuilder.fromHttpUrl(userServiceBaseUrl + "users/attendance/teacher")
                .queryParam("teacherCode", teacherCode)
                .queryParam("date", date)
                .toUriString();
        ResponseEntity<AttendanceDto[]> response = restTemplate.getForEntity(url, AttendanceDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<AttendanceDto> getAttendanceForStudent(String admissionNumber, String academicYear) {
        String url = UriComponentsBuilder.fromHttpUrl(userServiceBaseUrl + "users/attendance/student")
                .queryParam("admissionNumber", admissionNumber)
                .queryParam("academicYear", academicYear)
                .toUriString();
        ResponseEntity<AttendanceDto[]> response = restTemplate.getForEntity(url, AttendanceDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<AttendanceDto> getAttendanceByClassAndDate(String gradeLevel, String section, String academicYear, LocalDate date) {
        String url = UriComponentsBuilder.fromHttpUrl(userServiceBaseUrl + "users/attendance/class")
                .queryParam("gradeLevel", gradeLevel)
                .queryParam("section", section)
                .queryParam("academicYear", academicYear)
                .queryParam("date", date)
                .toUriString();
        ResponseEntity<AttendanceDto[]> response = restTemplate.getForEntity(url, AttendanceDto[].class);
        return Arrays.asList(response.getBody());
    }
}
