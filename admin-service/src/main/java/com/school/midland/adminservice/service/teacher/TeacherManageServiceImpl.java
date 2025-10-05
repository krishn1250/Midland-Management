package com.school.midland.adminservice.service.teacher;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.auth.AuthServiceClient;
import com.school.midland.adminservice.client.service.teacher.TeacherServiceClient;
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
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        System.out.println(teacherDto);
        if(teacherDto==null || teacherDto.getTeacherCode()==null
                || teacherDto.getUsername()==null || teacherDto.getSchoolEmail()==null
        || teacherDto.getPhoneNumber()==null){            throw new UserException("fill the necessary details ", HttpStatus.BAD_REQUEST);
        }
        UserCreationRequest userCreationRequest=UserCreationRequest.builder()
                .role("TEACHER")
                .email(teacherDto.getSchoolEmail())
                .associatedIdentifier(teacherDto.getTeacherCode())
                .username(teacherDto.getUsername())
                .password(teacherDto.getPassword())
                .fullName(teacherDto.getFirstName()+" "+teacherDto.getLastName())
//                .phoneNumber(teacherDto.getPhoneNumber())
                .build();
        final UserCreationResponse auth_user = authServiceClient.createUser(userCreationRequest);
        if(auth_user.getUserUid()==null){
            throw new UserException("failed to create user ", HttpStatus.BAD_REQUEST);
        }
        teacherDto.setTeacherUid(auth_user.getUserUid());
        return teacherServiceClient.createUserRest(teacherDto);
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
    public boolean deleteTeacher(String username) {
        if (username== null) {
            throw new UserException("Teacher ID cannot be null for deletion", HttpStatus.BAD_REQUEST);
        }
        return teacherServiceClient.deleteTeacher(username);
    }
}
