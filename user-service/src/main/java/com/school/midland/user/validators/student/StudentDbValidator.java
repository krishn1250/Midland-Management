package com.school.midland.user.validators.student;

import com.school.midland.user.exception.UserException;
import com.school.midland.user.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class StudentDbValidator {

    private final StudentRepository studentRepository;

    public StudentDbValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Validate admission number
    public void validateAdmissionNumber(String admissionNumber) {
        if (admissionNumber != null && studentRepository.findByAdmissionNumber(admissionNumber).isPresent()) {
            throw new UserException("Student admission number already exists", HttpStatus.BAD_REQUEST);
        }
    }

    // Validate email
    public void validateEmail(String schoolEmail) {
        if (schoolEmail != null && studentRepository.findBySchoolEmail(schoolEmail).isPresent()) {
            throw new UserException("Student email already exists", HttpStatus.BAD_REQUEST);
        }
    }

}
