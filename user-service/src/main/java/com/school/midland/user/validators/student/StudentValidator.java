package com.school.midland.user.validators.student;

import com.school.midland.user.dto.student.StudentDto;
import com.school.midland.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class StudentValidator {

    public static void validateAdmissionNumber(String admissionNumber) {
        if (StringUtils.isEmpty(admissionNumber) || admissionNumber.isBlank()) {
            throw new UserException("Admission number is required", HttpStatus.BAD_REQUEST);
        }
    }

    // Validate the student email
    public static void validateEmail(String email) {
        if (StringUtils.isEmpty(email) || email.isBlank()) {
            throw new UserException("Email is required", HttpStatus.BAD_REQUEST);
        }

    }
    public static void ValidateData(String value){
        if (StringUtils.isEmpty(value) || value.isBlank()) {
            throw new UserException("provide  required value", HttpStatus.BAD_REQUEST);
        }
    }



    public static void validateStudentData(StudentDto studentDto) {

        if (studentDto == null) {
            throw new UserException("Student details are missing", HttpStatus.BAD_REQUEST);
        }
        validateAdmissionNumber(studentDto.getAdmissionNumber());
        validateEmail(studentDto.getSchoolEmail());


    }
}
