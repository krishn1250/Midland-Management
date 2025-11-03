package com.school.midland.admin.client.teacher.service;

import com.school.midland.admin.client.teacher.dto.TeacherDto;
import com.school.midland.admin.client.teacher.dto.TeacherResponseDto;
import com.school.midland.admin.client.utils.ClientExceptionResponse;
import com.school.midland.admin.dtos.PageResponse;
import com.school.midland.admin.exception.AdminException;
import com.school.midland.admin.exception.AuthException;
import com.school.midland.admin.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TeacherServiceClient {

    private static final Logger log = LoggerFactory.getLogger(TeacherServiceClient.class);

    private final WebClient webClient;
    private static final String TEACHER_BASE_URL = "http://localhost:8083/midland/users";

    // ✅ Create Teacher
    public Boolean createTeacher(TeacherDto teacherDto, String token) {
        try {
            return webClient.post()
                    .uri(TEACHER_BASE_URL + "/teachers/create")
                    .header("Authorization", token != null ? token : "")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(teacherDto)
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error creating teacher: {}", e.getMessage(), e);
            throw new UserException("Teacher creation failed: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Get All Teachers (Paged)
    public PageResponse<TeacherResponseDto> getAllTeachers(int page, int size, String sortBy, String token) {
        String uri = String.format("%s/teachers/all?page=%d&size=%d&sortBy=%s", TEACHER_BASE_URL, page, size, sortBy);

        try {
            return webClient.get()
                    .uri(uri)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(new ParameterizedTypeReference<PageResponse<TeacherResponseDto>>() {})
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching teachers: {}", e.getMessage(), e);
            return PageResponse.<TeacherResponseDto>builder()
                    .content(Collections.emptyList())
                    .pageNumber(page)
                    .pageSize(size)
                    .totalElements(0)
                    .totalPages(0)
                    .last(true)
                    .build();
        }
    }


    public TeacherResponseDto getTeacherById(Long id, String token) {
        try {
            return webClient.get()
                    .uri(TEACHER_BASE_URL + "/teachers/id" + id)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(TeacherResponseDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching teacher by ID: {}", e.getMessage(), e);
            throw new UserException("Teacher fetch failed: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Get Teacher by UID
    public TeacherResponseDto getTeacherByUid(UUID uid, String token) {
        try {
            return webClient.get()
                    .uri(TEACHER_BASE_URL + "/teachers/uid/" + uid)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(TeacherResponseDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching teacher by UID: {}", e.getMessage(), e);
            throw new UserException("Teacher fetch failed: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Get Teacher by Code
    public TeacherResponseDto getTeacherByCode(String code, String token) {
        try {
            return webClient.get()
                    .uri(TEACHER_BASE_URL + "/teachers/code/" + code)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(TeacherResponseDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching teacher by code: {}", e.getMessage(), e);
            throw new UserException("Error fetching teacher: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Update Teacher
    public TeacherResponseDto updateTeacher(String email, TeacherDto updatedDto, String token) {
        try {
            return webClient.put()
                    .uri(TEACHER_BASE_URL + "/teachers/update/" + email)
                    .header("Authorization", token != null ? token : "")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updatedDto)
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(TeacherResponseDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error updating teacher: {}", e.getMessage(), e);
            throw new UserException("Teacher update failed: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Delete Teacher by Email
    public boolean deleteTeacher(String schoolEmail, String token) {
        try {
            return webClient.delete()
                    .uri(TEACHER_BASE_URL + "/teachers/delete/" + schoolEmail)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(Boolean.class)
                    .blockOptional()
                    .orElse(false);
        } catch (WebClientResponseException e) {
            log.error("Error deleting teacher: {}", e.getMessage(), e);
            return false;
        }
    }

    // ✅ Find by Department
    public List<TeacherResponseDto> findByDepartment(String department, String token) {
        try {
            return webClient.get()
                    .uri(TEACHER_BASE_URL + "/teachers/department/" + department)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(new ParameterizedTypeReference<List<TeacherResponseDto>>() {})
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching teachers by department: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // ✅ Find by Designation
    public List<TeacherResponseDto> findByDesignation(String designation, String token) {
        try {
            return webClient.get()
                    .uri(TEACHER_BASE_URL + "/teachers/designation/" + designation)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(new ParameterizedTypeReference<List<TeacherResponseDto>>() {})
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching teachers by designation: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // ✅ Get Teacher by Username
    public TeacherResponseDto getByUsername(String username, String token) {
        try {
            return webClient.get()
                    .uri(TEACHER_BASE_URL + "/username/" + username)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(HttpStatus->HttpStatus.isError(), response -> response.bodyToMono(String.class)
                            .flatMap(body -> Mono.error(new AdminException(
                                    ClientExceptionResponse.extractErrorMessageFromResponse(body),
                                    ClientExceptionResponse.extractErrorStatusFromResponse(body)))))

                    .bodyToMono(TeacherResponseDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching teacher by username: {}", e.getMessage(), e);
            throw new UserException("Error fetching teacher: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
