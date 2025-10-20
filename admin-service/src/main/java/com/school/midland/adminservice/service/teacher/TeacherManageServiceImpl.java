package com.school.midland.adminservice.service.teacher;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.auth.AuthServiceClient;
import com.school.midland.adminservice.client.service.teacher.TeacherServiceClient;
import com.school.midland.adminservice.exception.AdminException;
import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.commonlib.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherManageServiceImpl implements  TeacherManageService{
    private  final AuthServiceClient authServiceClient;
    private  final TeacherServiceClient teacherServiceClient;

    @Override
    public UserCreationResponse createTeacher(TeacherDto teacherDto, String token) {
        if (teacherDto == null || teacherDto.getTeacherCode() == null
                || teacherDto.getUsername() == null || teacherDto.getSchoolEmail() == null
                || teacherDto.getPhoneNumber() == null) {
            throw new UserException("Fill the necessary details", HttpStatus.BAD_REQUEST);
        }

        UserCreationResponse authUser = null;

        try {
            // Step 1: Build user creation request for Auth Service
            UserCreationRequest userCreationRequest = UserCreationRequest.builder()
                    .role("TEACHER")
                    .email(teacherDto.getSchoolEmail())
                    .associatedIdentifier(teacherDto.getTeacherCode())
                    .username(teacherDto.getUsername())
                    .password(teacherDto.getPassword())
                    .fullName(teacherDto.getFirstName() + " " + teacherDto.getLastName())
                    .phoneNumber(teacherDto.getPhoneNumber())
                    .build();

            // Step 2: Create user in Auth Service
            authUser = authServiceClient.createUser(userCreationRequest);
            if (authUser.getUserUid() == null) {
                throw new UserException("Failed to create user in Auth Service", HttpStatus.BAD_REQUEST);
            }

            // Step 3: Link Teacher UID and create in Teacher Service
            teacherDto.setTeacherUid(authUser.getUserUid());
            boolean teacherCreated = teacherServiceClient.createUserRest(teacherDto);

            if (!teacherCreated) {
                // Step 4: Rollback user creation in Auth Service
                authServiceClient.deleteUser(authUser.getEmail(), token);
                throw new UserException("Failed to create teacher in Teacher Service", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return authUser;
        } catch (Exception e) {
            // Step 5: Handle partial failure cleanup
            if (authUser != null && authUser.getEmail() != null) {
                try {
                    authServiceClient.deleteUser(authUser.getEmail(), token);
                } catch (Exception rollbackEx) {
                    System.err.println("⚠️ Rollback failed for user: " + authUser.getEmail());
                }
            }

            throw new UserException("Teacher creation failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TeacherDto> getTeacherByDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new UserException("Teacher desiognation cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        return teacherServiceClient.getTeacherByDepartment(department);
    }



    @Override
    public TeacherDto getTeacherByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new UserException("Teacher desiognation cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        return teacherServiceClient.findByUsername(username);
    }

//    @Override
//    public TeacherDto getTeacherById(Long id) {
//        if (id == null) {
//            throw new UserException("Teacher ID cannot be null", HttpStatus.BAD_REQUEST);
//        }
//        return teacherServiceClient.;
//    }

//    @Override
//    public TeacherDto getTeacherByUid(UUID uid) {
//        if (uid == null) {
//            throw new UserException("Teacher UID cannot be null", HttpStatus.BAD_REQUEST);
//        }
//        return teacherServiceClient.getTeacherByUid(uid);
//    }

    @Override
    public TeacherDto getTeacherByCode(String teacherCode) {
        if (teacherCode == null || teacherCode.trim().isEmpty()) {
            throw new UserException("Teacher Code cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        return teacherServiceClient.getTeacherByCodeRest(teacherCode);
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherServiceClient.getAllTeachersRest();
    }

    @Override
    public TeacherDto updateTeacher(String teacherCode, TeacherDto teacherDto) {
        if (teacherCode == null || teacherCode.isBlank()) {
            throw new UserException("Teacher code must be provided for update", HttpStatus.BAD_REQUEST);
        }
        return teacherServiceClient.updateTeacherRest(teacherCode, teacherDto);
    }

    @Override
    public boolean deleteTeacher(String email, String token) {
        boolean userDeleted = false;

        if (email == null) {
            throw new UserException("Teacher email cannot be null for deletion", HttpStatus.BAD_REQUEST);
        }

        try {
            // Step 1: Delete teacher from teacher-service
            boolean teacherDeleted = teacherServiceClient.deleteTeacher(email);

            // Step 2: Delete corresponding user from auth-service
            userDeleted = authServiceClient.deleteUser(email, token);

            if (!teacherDeleted) {
                throw new AdminException("Failed to delete teacher in teacher-service", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return true;
        } catch (Exception e) {
            if (userDeleted) {
                System.err.println("⚠️ Teacher delete failed after user delete for email: " + email);
            }
            throw new AdminException("Delete operation failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
