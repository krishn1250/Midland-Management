package com.school.midland.admin.service.student;

import com.school.midland.admin.client.auth.dto.RegisterRequest;
import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.client.auth.AuthServiceClient;
import com.school.midland.admin.client.auth.dto.UserDto;
import com.school.midland.admin.client.student.dto.StudentResponseDto;
import com.school.midland.admin.client.student.service.StudentServiceClient;
import com.school.midland.admin.client.student.dto.StudentDto;
import com.school.midland.admin.exception.AdminException;
import com.school.midland.admin.exception.UserException;
import com.school.midland.admin.validator.student.StudentValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class StudentManageServiceImpl implements  StudentManageService{

    private static final Logger log = LoggerFactory.getLogger(StudentManageServiceImpl.class);


    private final StudentServiceClient studentServiceClient;
    private final AuthServiceClient authServiceClient;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public RegisterResponse createStudent(StudentDto studentDto, String token) {
        // 1️⃣ Validate student DTO
        StudentValidator.validateStudent(studentDto);

        RegisterRequest registerRequest = RegisterRequest.builder()
                .role("STUDENT")
                .email(studentDto.getSchoolEmail())
                .username(studentDto.getUsername())
                .password(studentDto.getPassword())
                .fullName(studentDto.getFullName())
                .phoneNumber(studentDto.getPhoneNumber())
                .build();

        long start = System.currentTimeMillis();

        // 2️⃣ Register user in auth-service
        RegisterResponse authUser = authServiceClient.register(registerRequest, token);

        if (authUser == null || authUser.getUserUid() == null) {
            throw new AdminException("Failed to create user", HttpStatus.BAD_REQUEST);
        }

        // 3️⃣ Create student in student-service
        studentDto.setStudentUid(authUser.getUserUid());
        boolean studentCreated = studentServiceClient.createStudent(studentDto, token);

        if (!studentCreated) {
            // Rollback auth-service if student creation fails
            authServiceClient.deleteUser(authUser.getEmail(), token);
            throw new AdminException("Failed to create student", HttpStatus.BAD_REQUEST);
        }

        long end = System.currentTimeMillis();
        log.info("Student creation completed in {} ms", (end - start));

        return authUser;
    }

    @Override
    public boolean deleteStudent(String email, String token) {
        boolean userDeleted = false;
        try {
            boolean studentDeleted = studentServiceClient.deleteStudent(email,token);
            System.out.println(studentDeleted);
            userDeleted = authServiceClient.deleteUser(email, token);
            System.out.println(userDeleted);

            if (!studentDeleted) {
                throw new AdminException("Failed to delete student in student-service", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("Deleted student and user successfully: {}", email);
            return true;

        } catch (Exception e) {
            if (userDeleted) {
                log.warn("Student deletion failed after user deletion for email: {}", email);
            }
            throw new AdminException("Delete operation failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public StudentResponseDto updateStudent(String email, StudentDto updatedDto, String token) {
        StudentResponseDto studentResponseDto;
        try {
            // Only update fields that are present in updatedDto
            RegisterRequest updatedUser = RegisterRequest.builder()
                    .fullName(updatedDto.getFullName())
                    .phoneNumber(updatedDto.getPhoneNumber())
                    .password(updatedDto.getPassword())
                    .email(updatedDto.getSchoolEmail())
                    .build();
            if(updatedUser.getFullName()!=null || updatedUser.getPhoneNumber()!= null || updatedUser.getPassword()!=null || updatedUser.getEmail()!=null)
                authServiceClient.updateUser(email, updatedUser, token);
            studentResponseDto = studentServiceClient.updateStudent(email, updatedDto, token);

        } catch (RestClientException e) {
            log.error("Auth service update failed for user {}: {}", email, e.getMessage());
            // Do NOT delete the user automatically—could lose data
            throw new AdminException("Failed to update user: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return studentResponseDto;
    }

    @Override
    public StudentResponseDto getByEmail(String email,String token) {
        return studentServiceClient.getByStudentEmail(email,token);
    }

    @Override
    public StudentResponseDto getByAdmissionNumber(String admissionNumber,String token) {
        return studentServiceClient.getByAdmissionNumber(admissionNumber,token);
    }

    @Override
    public List<StudentResponseDto> getAllStudents(int page,int size,String sortBy, String token) {
        return studentServiceClient.getAllStudents(page,size,sortBy,token).getContent();
    }

    @Override
    public List<StudentResponseDto> getByAcademicYear(int page, int size, String sortBy, String academicYear) {
return studentServiceClient.getByAcademicYear(page,size,sortBy,academicYear).getContent();
    }

    @Override
    public List<StudentResponseDto> getByGradeLevel(int page, int size, String sortBy, String academicYear) {
        return studentServiceClient.getByGradeLevel(page,size,sortBy,academicYear).getContent();
    }

    @Override
    public List<StudentResponseDto> getByGradeAndSection(String grade, String section) {
        return studentServiceClient.getByGradeSectionRest(grade,section);
    }


}
