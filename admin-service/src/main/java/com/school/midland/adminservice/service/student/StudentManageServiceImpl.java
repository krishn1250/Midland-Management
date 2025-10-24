package com.school.midland.adminservice.service.student;

import com.school.midland.adminservice.client.dtos.UserCreationRequest;
import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.auth.AuthServiceClient;
import com.school.midland.adminservice.client.service.student.StudentServiceClient;
import com.school.midland.adminservice.exception.AdminException;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.commonlib.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentManageServiceImpl implements  StudentManageService{

    private final StudentServiceClient studentServiceClient;
    private final AuthServiceClient authServiceClient;

    @Override
    public UserCreationResponse createStudent(StudentDto studentDto, String token) {
        UserCreationResponse auth_user=null;

        if(studentDto==null || studentDto.getAdmissionNumber()==null ||
                studentDto.getSchoolEmail()==null || studentDto.getAcademicYear()==null){
            throw new AdminException("fill the necessary details ", HttpStatus.BAD_REQUEST);
        }
        try{
            UserCreationRequest registerRequest=UserCreationRequest.builder()
                    .role("STUDENT")
                    .email(studentDto.getSchoolEmail())
                    .username(studentDto.getUsername())
                    .password(studentDto.getPassword())
                    .fullName(studentDto.getFullName())
                    .phoneNumber(studentDto.getPhoneNumber())
                    .build();
            auth_user = authServiceClient.createUser(registerRequest);
            if(auth_user.getUserUid()==null){

                authServiceClient.deleteUser(auth_user.getEmail(),token);
                throw new UserException("failed to create user ", HttpStatus.BAD_REQUEST);
            }
            System.out.println(auth_user);
            studentDto.setStudentUid(auth_user.getUserUid());
            System.out.println(auth_user.getUserUid());
            System.out.println(studentDto.getStudentUid());
            boolean response=studentServiceClient.createUserRest(studentDto);
            if(response==false){
                authServiceClient.deleteUser(auth_user.getEmail(),token);
            }
        }

        catch (Exception e){
            if(auth_user != null && auth_user.getUserUid() != null) {
                try {
                    authServiceClient.deleteUser(auth_user.getEmail(), token);
                } catch (Exception ex) {
                    // log only; do not throw
                    System.err.println("Failed to rollback user creation: " + ex.getMessage());
                }
            }
            throw e;
        }

        return  auth_user;
    }
    @Override
    public boolean deleteStudent(String email, String token) {
        boolean userDeleted = false;

        try {
            boolean studentDeleted = studentServiceClient.deleteStudent(email);
            userDeleted = authServiceClient.deleteUser(email, token);


            if (!studentDeleted) {
                throw new AdminException("Failed to delete student in student-service", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return true;
        } catch (Exception e) {
            if (userDeleted) {
                System.err.println("⚠️ Student delete failed after user delete for email: " + email);
            }
            throw new AdminException("Delete operation failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public StudentDto updateStudent(String admissionNumber, StudentDto updatedDto) {
        return studentServiceClient.updateStudent(admissionNumber, updatedDto);
    }

    @Override
    public StudentDto getByAdmissionNumber(String admissionNumber) {

        return studentServiceClient.getByAdmissionNumber(admissionNumber);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentServiceClient.getAllStudents();
    }
}
