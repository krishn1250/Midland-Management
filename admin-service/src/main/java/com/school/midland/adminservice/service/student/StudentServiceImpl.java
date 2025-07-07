package com.school.midland.adminservice.service.student;

import com.school.midland.adminservice.dto.StudentDto;
import com.school.midland.adminservice.models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentServiceImpl implements  StudentService{

    @Override
    public ResponseEntity<?> createStudent(StudentDto studentDto) {

        return null;
    }

    @Override
    public ResponseEntity<?> getStudentById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getStudentByUid(UUID uid) {
        return null;
    }

    @Override
    public ResponseEntity<?> getStudentByAdmissionNumber(String admissionNumber) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateStudent(String admissionNumber, StudentDto updatedDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteStudentById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllStudents() {
        return null;
    }

    @Override
    public ResponseEntity<?> getStudentsByGradeLevel(String gradeLevel) {
        return null;
    }

    @Override
    public ResponseEntity<?> getStudentsByAcademicYear(String academicYear) {
        return null;
    }
}
