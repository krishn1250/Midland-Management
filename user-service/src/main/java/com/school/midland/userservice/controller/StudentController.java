package com.school.midland.userservice.controller;


import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/midland/users/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<StudentDto>  createStudent(@RequestBody StudentDto studentDto) {
        System.out.println("API HIT");
        final var student = studentService.createStudent(studentDto);

        return ResponseEntity.ok(student);

    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
//        return studentService.getStudentById(id);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/uid/{studentUid}")
//    public ResponseEntity<?> getStudentByUid(@PathVariable UUID studentUid) {
//        return studentService.getStudentByUid(studentUid);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/admission/{admissionNumber}")
//    public ResponseEntity<?> getByAdmissionNumber(@PathVariable String admissionNumber) {
//        return studentService.getStudentByAdmissionNumber(admissionNumber);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/update/{admissionNumber}")
//    public ResponseEntity<?> updateStudent(@PathVariable String admissionNumber, @RequestBody StudentDto updatedDto) {
//        return studentService.updateStudent(admissionNumber, updatedDto);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
//        return studentService.deleteStudentById(id);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/all")
//    public ResponseEntity<?> getAllStudents() {
//        return studentService.getAllStudents();
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/grade/{gradeLevel}")
//    public ResponseEntity<?> getStudentsByGradeLevel(@PathVariable String gradeLevel) {
//        return studentService.getStudentsByGradeLevel(gradeLevel);
//    }
//
//    @GetMapping("/students/class")
//    public ResponseEntity<List<Student>> getStudentsByGradeAndSection(
//            @RequestParam String grade,
//            @RequestParam String section
//    ) {
//        return  studentService.findByGradeAndSection(grade,section);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/year/{academicYear}")
//    public ResponseEntity<?> getStudentsByAcademicYear(@PathVariable String academicYear) {
//        return studentService.getStudentsByAcademicYear(academicYear);
//    }
}

