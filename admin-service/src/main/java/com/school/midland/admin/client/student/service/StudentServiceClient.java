package com.school.midland.admin.client.student.service;

import com.school.midland.admin.client.student.dto.StudentDto;
import com.school.midland.admin.client.student.dto.StudentResponseDto;
import com.school.midland.admin.client.utils.ClientExceptionResponse;
import com.school.midland.admin.dtos.PageResponse;
import com.school.midland.admin.exception.AuthException;
import com.school.midland.admin.exception.UserException;
import com.school.midland.admin.service.student.StudentManageServiceImpl;
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

@Component
@RequiredArgsConstructor
public class StudentServiceClient {
    private static final Logger log = LoggerFactory.getLogger(StudentManageServiceImpl.class);

    private final WebClient webClient;
    private static final String STUDENT_BASE_URL = "http://localhost:8083/midland";

    // Create Student Method
    public boolean createStudent(StudentDto studentDto, String token) {
        try {
            return webClient.post()
                    .uri(STUDENT_BASE_URL + "/users/student/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", token != null ? token : "")
                    .bodyValue(studentDto)
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })

                    .bodyToMono(Boolean.class)
                    .blockOptional()
                    .orElse(false);
        } catch (WebClientResponseException e) {
            log.error("Error creating student: " + e.getMessage(), e);
            return false; // or you can throw UserException here if preferred
        }
    }

    // Get All Students Method with Pagination
    public PageResponse<StudentResponseDto> getAllStudents(int page, int size, String sortBy, String token) {
        String uri = String.format("%s/users/student/all?page=%d&size=%d&sortBy=%s",
                STUDENT_BASE_URL, page, size, sortBy);

        try {
            return webClient.get()
                    .uri(uri)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })
                    .bodyToMono(new ParameterizedTypeReference<PageResponse<StudentResponseDto>>() {})
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching all students: " + e.getMessage(), e);
            return PageResponse.<StudentResponseDto>builder()
                    .content(Collections.emptyList())
                    .pageNumber(page)
                    .pageSize(size)
                    .totalElements(0)
                    .totalPages(0)
                    .last(true)
                    .build();
        }
    }
    public PageResponse<StudentResponseDto> getByAcademicYear(int page, int size, String sortBy, String academicyear){
        String uri=String.format("%s/users/student/year/%s?page=%d&size=%d&sortBy=%s",
                STUDENT_BASE_URL,academicyear, page, size, sortBy);
        try{
            return  webClient.get()
                    .uri(uri)
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })
                    .bodyToMono(new ParameterizedTypeReference<PageResponse<StudentResponseDto>>() {})
                    .block();
        }
        catch (WebClientResponseException e) {
            log.error("Error fetching students: " + e.getMessage(), e);
            return PageResponse.<StudentResponseDto>builder()
                    .content(Collections.emptyList())
                    .pageNumber(page)
                    .pageSize(size)
                    .totalElements(0)
                    .totalPages(0)
                    .last(true)
                    .build();
        }
    }

    // Get Student by Admission Number
    public StudentResponseDto getByAdmissionNumber(String admissionNumber, String token) {
        try {
            return webClient.get()
                    .uri(STUDENT_BASE_URL + "/users/student/admission/" + admissionNumber)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })
                    .bodyToMono(StudentResponseDto.class)
                    .block();  // block to wait for the result synchronously
        } catch (WebClientResponseException e) {
            // Catch any WebClient response errors (including 4xx/5xx)
            // Here we can extract the message from the exception body (the actual error message from the student-service)
            throw new UserException("Error calling student service: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public PageResponse<StudentResponseDto> getByGradeLevel(int page, int size, String sortBy, String gradeLevel){
        String uri=String.format("%s/users/student/grade/%s?page=%d&size=%d&sortBy=%s",
                STUDENT_BASE_URL,gradeLevel,page,size,sortBy);
        try{
            return  webClient.get()
                    .uri(uri)
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })
                    .bodyToMono(new ParameterizedTypeReference<PageResponse<StudentResponseDto>>(){})
                    .block();


        }
        catch (WebClientResponseException e){
            log.error("Error fetching students: " + e.getMessage(), e);
            return PageResponse.<StudentResponseDto>builder()
                    .content(Collections.emptyList())
                    .pageNumber(page)
                    .pageSize(size)
                    .totalElements(0)
                    .totalPages(0)
                    .last(true)
                    .build();
        }
    }

public List<StudentResponseDto> getByGradeSectionRest(String grade, String section ){
        String uri=String.format("%s/users/student/class?grade=%s&section=%s",
                STUDENT_BASE_URL,grade,section);
    try {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })
                .bodyToMono(new ParameterizedTypeReference<List<StudentResponseDto>>() {
                })
                .block();  // block to wait for the result synchronously
    } catch (WebClientResponseException e) {
        // Catch any WebClient response errors (including 4xx/5xx)
        // Here we can extract the message from the exception body (the actual error message from the student-service)
        throw new UserException("Error calling student service: " + e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    public StudentResponseDto getByStudentEmail(String email, String token) {
        try {
            return webClient.get()
                    .uri(STUDENT_BASE_URL + "/users/student/email/" + email)
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })

                    .bodyToMono(StudentResponseDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error fetching student by email: " + e.getMessage(), e);
            throw new UserException("Error calling student service: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public StudentResponseDto updateStudent(String admissionNumber, StudentDto updatedDto, String token) {
        try {
            return webClient.put()
                    .uri(STUDENT_BASE_URL + "/users/student/update/" + admissionNumber)
                    .header("Authorization", token != null ? token : "")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updatedDto)
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })
                    .bodyToMono(StudentResponseDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Error updating student: " + e.getMessage(), e);
            return null; // or throw UserException
        }
    }

    // Delete Student
    public boolean deleteStudent(String email, String token) {
        try {
            return webClient.delete()
                    .uri(STUDENT_BASE_URL + "/users/student/delete/" + email)
                    .header("Authorization", token != null ? token : "")
                    .retrieve()
                    .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                        return  clientResponse.bodyToMono(String.class)
                                .flatMap(responseBody->{

                                    String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                    HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                    return Mono.error(new AuthException(errorMessage, errorStatus));
                                });
                    })
                    .bodyToMono(Boolean.class)
                    .blockOptional()
                    .orElse(false);
        } catch (WebClientResponseException e) {
            log.error("Error deleting student: " + e.getMessage(), e);
            return false; // or throw UserException
        }
    }
}
