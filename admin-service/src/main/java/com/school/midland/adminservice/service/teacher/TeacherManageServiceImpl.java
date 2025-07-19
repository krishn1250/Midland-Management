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
        || teacherDto.getPhoneNumber()==null){
            throw new UserException("fill the necessary details ", HttpStatus.BAD_REQUEST);
        }
        UserCreationRequest userCreationRequest=UserCreationRequest.builder()
                .role("TEACHER")
                .email(teacherDto.getSchoolEmail())
                .associatedIdentifier(teacherDto.getTeacherCode())
                .username(teacherDto.getUsername())
                .password(teacherDto.getPassword())
                .fullName(teacherDto.getFirstName()+" "+teacherDto.getLastName())
                .phoneNumber(teacherDto.getPhoneNumber())
                .build();
        final UserCreationResponse auth_user = authServiceClient.createUser(userCreationRequest);
        if(auth_user.getUserUid()==null){
            throw new UserException("failed to create user ", HttpStatus.BAD_REQUEST);
        }
        teacherDto.setTeacherUid(auth_user.getUserUid());
        return teacherServiceClient.createUserRest(teacherDto);
    }

    @Override
    public TeacherDto getTeacherById(Long id) {
        return null;
    }

    @Override
    public TeacherDto getTeacherByUid(UUID uid) {
        return null;
    }

    @Override
    public TeacherDto getTeacherByCode(String teacherCode) {
        return null;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return List.of();
    }

    @Override
    public List<TeacherDto> updateTeacher(String teacherCode, TeacherDto teacherDto) {
        return List.of();
    }

    @Override
    public boolean deleteTeacher(Long id) {
        return false;
    }
}
