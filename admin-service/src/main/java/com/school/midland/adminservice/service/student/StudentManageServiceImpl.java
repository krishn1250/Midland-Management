package com.school.midland.adminservice.service.student;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.auth.AuthServiceClient;
import com.school.midland.adminservice.client.service.student.StudentServiceClient;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.commonlib.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentManageServiceImpl implements  StudentManageService{

    private final StudentServiceClient studentServiceClient;
    private final AuthServiceClient authServiceClient;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

            if(studentDto==null || studentDto.getAdmissionNumber()==null ||
                    studentDto.getSchoolEmail()==null || studentDto.getAcademicYear()==null){
                    throw new UserException("fill the necessary details ", HttpStatus.BAD_REQUEST);
            }
        UserCreationRequest userCreationRequest=UserCreationRequest.builder()
                .role("STUDENT")
                .email(studentDto.getSchoolEmail())
                .associatedIdentifier(studentDto.getAdmissionNumber())
                .username(studentDto.getUsername())
                .password(studentDto.getPassword())
                .fullName(studentDto.getFullName())
                .phoneNumber(studentDto.getPhoneNumber())
                .build();
        final UserCreationResponse auth_user = authServiceClient.createUser(userCreationRequest);
        if(auth_user.getUserUid()==null){
            throw new UserException("failed to create user ", HttpStatus.BAD_REQUEST);
        }
        System.out.println(auth_user);
            studentDto.setStudentUid(auth_user.getUserUid());
        System.out.println(auth_user.getUserUid());
        System.out.println(studentDto.getStudentUid());
        return  studentServiceClient.createUserRest(studentDto);
    }
}
