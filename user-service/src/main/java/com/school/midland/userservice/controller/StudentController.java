
// --- File: controller/StudentController.java ---
package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.StudentRequestDTO;
import com.school.midland.userservice.model.Student;
import com.school.midland.userservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequestDTO studentDTO) {
        Student createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@RequestParam String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}