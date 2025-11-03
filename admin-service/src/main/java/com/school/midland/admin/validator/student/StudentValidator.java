package com.school.midland.admin.validator.student;

import com.school.midland.admin.client.student.dto.StudentDto;
import com.school.midland.admin.exception.AdminException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class StudentValidator {
    public static void validateStudent(StudentDto studentDto) {
        if (studentDto == null) {
            throw new AdminException("Student details cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(studentDto.getAdmissionNumber())) {
            throw new AdminException("Admission number is required", HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(studentDto.getSchoolEmail())) {
            throw new AdminException("School email is required", HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(studentDto.getFullName())) {
            throw new AdminException("Full name is required", HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(studentDto.getUsername())) {
            throw new AdminException("Username is required", HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(studentDto.getPassword())) {
            throw new AdminException("Password is required", HttpStatus.BAD_REQUEST);
        }

        if (studentDto.getAcademicYear() == null) {
            throw new AdminException("Academic year is required", HttpStatus.BAD_REQUEST);
        }

        // Optional: validate phone number format
//        if (StringUtils.hasText(studentDto.getPhoneNumber()) &&
//                !studentDto.getPhoneNumber().matches("\\+?[0-9]{10,15}")) {
//            throw new AdminException("Invalid phone number format", HttpStatus.BAD_REQUEST);
//        }

        // Optional: validate email format
        if (!studentDto.getSchoolEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new AdminException("Invalid school email format", HttpStatus.BAD_REQUEST);
        }
    }
}
