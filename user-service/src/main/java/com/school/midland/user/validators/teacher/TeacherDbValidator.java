package com.school.midland.user.validators.teacher;

import com.school.midland.user.exception.UserException;
import com.school.midland.user.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherDbValidator {

    private final TeacherRepository teacherRepository;

    public void validateEmail(String email) {
        if (teacherRepository.findBySchoolEmail(email).isPresent()) {
            throw new UserException("Teacher email already exists: " + email, HttpStatus.BAD_REQUEST);
        }
    }

    public void validateTeacherCode(String teacherCode) {
        if (teacherRepository.findByTeacherCode(teacherCode).isPresent()) {
            throw new UserException("Teacher code already exists: " + teacherCode, HttpStatus.BAD_REQUEST);
        }
    }
}
