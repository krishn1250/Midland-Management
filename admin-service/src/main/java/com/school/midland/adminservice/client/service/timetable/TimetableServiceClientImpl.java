package com.school.midland.adminservice.client.service.timetable;

import com.school.midland.commonlib.dtos.TimetableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableServiceClientImpl implements TimetableServiceClient {

    private final RestTemplate restTemplate;

    @Value("${user-service.base-url}")
    private String userServiceBaseUrl;

    @Override
    public TimetableDto createTimetable(TimetableDto timetableDto) {

        String url = userServiceBaseUrl + "users/timetables/create";
        return restTemplate.postForObject(userServiceBaseUrl+url,timetableDto,TimetableDto.class);
    }

    @Override
    public List<TimetableDto> createTimetables(List<TimetableDto> timetableDtos) {
        String url = userServiceBaseUrl + "users/timetables/list/create";
        ResponseEntity<TimetableDto[]> response = restTemplate.postForEntity(url, timetableDtos, TimetableDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<TimetableDto> getTimetableByTeacherCode(String teacherCode) {
        String url = UriComponentsBuilder
                .fromHttpUrl(userServiceBaseUrl + "users/timetables/teacher/"+teacherCode)
                .toUriString();

        ResponseEntity<TimetableDto[]> response = restTemplate.getForEntity(url, TimetableDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<TimetableDto> getTimetableForClass(String gradeLevel, String section, String dayOfWeek) {
        String url = UriComponentsBuilder.fromHttpUrl(userServiceBaseUrl + "users/timetables/class")
                .queryParam("gradeLevel", gradeLevel)
                .queryParam("section", section)
                .queryParam("dayOfWeek", dayOfWeek)
                .toUriString();

        ResponseEntity<TimetableDto[]> response = restTemplate.getForEntity(url, TimetableDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<TimetableDto> getAllTimetables() {
        String url = userServiceBaseUrl + "users/timetables/all";
        ResponseEntity<TimetableDto[]> response = restTemplate.getForEntity(url, TimetableDto[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public String deleteTimetableById(Long id) {
        String url = UriComponentsBuilder.fromHttpUrl(userServiceBaseUrl + "users/timetables/delete/" + id)
                .toUriString();

        restTemplate.delete(url);
        return "Deleted timetable with ID: " + id;
    }
}