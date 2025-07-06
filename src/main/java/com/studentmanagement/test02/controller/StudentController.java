package com.studentmanagement.test02.controller;

import com.studentmanagement.test02.dto.StudentDTO;
import com.studentmanagement.test02.model.Student;
import com.studentmanagement.test02.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    // This class will handle HTTP requests related to students
    // You can define endpoints here to manage student data, such as adding, updating, deleting, and retrieving students.
    // For example:
     @GetMapping("/students")
     public List<StudentDTO> getAllStudents() {
         return studentService.getAllStudents();
     }
}
