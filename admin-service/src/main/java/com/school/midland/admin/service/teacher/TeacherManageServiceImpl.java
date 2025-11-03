package com.school.midland.admin.service.teacher;

import com.school.midland.admin.client.auth.AuthServiceClient;
import com.school.midland.admin.client.auth.dto.RegisterRequest;
import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.client.teacher.dto.TeacherDto;
import com.school.midland.admin.client.teacher.dto.TeacherResponseDto;
import com.school.midland.admin.client.teacher.service.TeacherServiceClient;
import com.school.midland.admin.exception.AdminException;
import com.school.midland.admin.exception.UserException;
import com.school.midland.admin.validator.teacher.TeacherValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class TeacherManageServiceImpl implements TeacherManageService {

    private static final Logger log = LoggerFactory.getLogger(TeacherManageServiceImpl.class);

    private final TeacherServiceClient teacherServiceClient;
    private final AuthServiceClient authServiceClient;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public RegisterResponse createTeacher(TeacherDto teacherDto, String token) {

        TeacherValidator.validateTeacherData(teacherDto);

        RegisterRequest registerRequest = RegisterRequest.builder()
                .role("TEACHER")
                .email(teacherDto.getSchoolEmail())

                .username(teacherDto.getUsername())
                .password(teacherDto.getPassword())
                .fullName(teacherDto.getFullName())
                .phoneNumber(teacherDto.getPhoneNumber())
                .build();

        long start = System.currentTimeMillis();
        RegisterResponse authUser = null;
        boolean authCreated = false;
        try {

            authUser = authServiceClient.register(registerRequest, token);
            if (authUser == null || authUser.getUserUid() == null) {
                throw new UserException("Failed to create teacher user", HttpStatus.BAD_REQUEST);
            }
            authCreated = true;

            teacherDto.setTeacherUid(authUser.getUserUid());
            Boolean teacherCreated = teacherServiceClient.createTeacher(teacherDto, token);
            if (!teacherCreated ) {
                if (authCreated) authServiceClient.deleteUser(authUser.getEmail(), token);
                throw new UserException("Failed to create teacher in user-service", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            long end = System.currentTimeMillis();
            log.info("Teacher creation completed in {} ms", (end - start));
            return authUser;

        } catch (UserException e) {

            log.error("Teacher creation failed: {}", e.getMessage());
            throw e;

        } catch (Exception e) {

            if (authCreated) {
                try {
                    authServiceClient.deleteUser(authUser.getEmail(), token);
                } catch (Exception rollbackEx) {
                    log.warn("Rollback failed for teacher user {}", teacherDto.getSchoolEmail());
                }
            }
            log.error("Teacher creation failed unexpectedly: {}", e.getMessage(), e);
            throw new UserException("Failed to create teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public boolean deleteTeacher(String email, String token) {
        boolean userDeleted = false;
        try {
            boolean teacherDeleted = teacherServiceClient.deleteTeacher(email, token);
            userDeleted = authServiceClient.deleteUser(email, token);

            if (!teacherDeleted) {
                throw new AdminException("Failed to delete teacher in user-service", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("Deleted teacher and user successfully: {}", email);
            return true;

        } catch (Exception e) {
            if (userDeleted) {
                log.warn("Teacher deletion failed after user deletion for email: {}", email);
            }
            throw new AdminException("Delete operation failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TeacherResponseDto updateTeacher(String email, TeacherDto updatedDto, String token) {
        TeacherResponseDto teacherResponseDto;
        try {
            RegisterRequest updatedUser = RegisterRequest.builder()
                    .fullName(updatedDto.getFullName())
                    .phoneNumber(updatedDto.getPhoneNumber())
                    .password(updatedDto.getPassword())
                    .email(updatedDto.getSchoolEmail())
                    .build();

            if(updatedUser.getFullName()!=null || updatedUser.getPhoneNumber()!= null || updatedUser.getPassword()!=null || updatedUser.getEmail()!=null)
            authServiceClient.updateUser(email, updatedUser, token);

            // Update teacher info in User Service
            teacherResponseDto = teacherServiceClient.updateTeacher(email, updatedDto, token);

        } catch (RestClientException e) {
            log.error("Auth service update failed for teacher {}: {}", email, e.getMessage());
            throw new AdminException("Failed to update teacher: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return teacherResponseDto;
    }

    @Override
    public TeacherResponseDto getById(Long id, String token) {
        return teacherServiceClient.getTeacherById(id,token);
    }

    @Override
    public TeacherResponseDto getByUid(UUID uid, String token) {
        return teacherServiceClient.getTeacherByUid(uid, token);
    }

    @Override
    public TeacherResponseDto getByCode(String code, String token) {
        return teacherServiceClient.getTeacherByCode(code, token);
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers(int page, int size, String sortBy, String token) {
        return teacherServiceClient.getAllTeachers(page, size, sortBy, token).getContent();
    }

    @Override
    public List<TeacherResponseDto> getByDepartment(String department, String token) {
        return teacherServiceClient.findByDepartment(department, token);
    }

    @Override
    public List<TeacherResponseDto> getByDesignation(String designation, String token) {
        return teacherServiceClient.findByDesignation(designation, token);
    }

    @Override
    public TeacherResponseDto getByUsername(String username, String token) {
        return teacherServiceClient.getByUsername(username, token);
    }
}
