package com.school.midland.user.validators.teacher;

import com.school.midland.user.dto.teacher.TeacherDto;
import com.school.midland.user.exception.UserException;
import org.springframework.http.HttpStatus;

import java.util.regex.Pattern;

public class TeacherValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validateTeacherData(TeacherDto dto) {
        if (dto == null)
            throw new UserException("Teacher details cannot be null", HttpStatus.BAD_REQUEST);


        validateData(dto.getTeacherCode());
        validateEmail(dto.getSchoolEmail());
        validateData(dto.getPassword());
        validateData(dto.getSchoolCode());
        validateData(dto.getUsername());
        validateData(dto.getPhoneNumber());
    }

    public static void validateTeacherCode(String teacherCode) {
        if (teacherCode == null || teacherCode.isEmpty())
            throw new UserException("Teacher code cannot be empty", HttpStatus.BAD_REQUEST);
    }

    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new UserException("Invalid email format", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateData(String value) {
        if (value == null || value.isBlank()) {
            throw new UserException("Field cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }
}
