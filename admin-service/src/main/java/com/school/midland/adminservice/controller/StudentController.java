package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.dto.StudentDto;

import com.school.midland.adminservice.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/midland/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // ✅ Create a new student
    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    // ✅ Get student by DB ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // ✅ Get student by UUID
    @GetMapping("/uid/{studentUid}")
    public ResponseEntity<?> getStudentByUid(@PathVariable UUID studentUid) {
        return studentService.getStudentByUid(studentUid);
    }

    // ✅ Get student by admission number
    @GetMapping("/admission/{admissionNumber}")
    public ResponseEntity<?> getByAdmissionNumber(@PathVariable String admissionNumber) {
        return studentService.getStudentByAdmissionNumber(admissionNumber);
    }

    // ✅ Update student
    @PutMapping("/update/{admissionNumber}")
    public ResponseEntity<?> updateStudent(@PathVariable String admissionNumber, @RequestBody StudentDto updatedDto) {
        return studentService.updateStudent(admissionNumber, updatedDto);
    }

    // ✅ Delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

    // ✅ Get all students
    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ✅ Get students by grade level
    @GetMapping("/grade/{gradeLevel}")
    public ResponseEntity<?> getStudentsByGradeLevel(@PathVariable String gradeLevel) {
        return studentService.getStudentsByGradeLevel(gradeLevel);
    }

    // ✅ Get students by academic year
    @GetMapping("/year/{academicYear}")
    public ResponseEntity<?> getStudentsByAcademicYear(@PathVariable String academicYear) {
        return studentService.getStudentsByAcademicYear(academicYear);
    }
}
