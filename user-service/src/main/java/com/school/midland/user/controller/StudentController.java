package com.school.midland.user.controller;


import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.student.StudentDto;
import com.school.midland.user.dto.student.StudentResponseDto;
import com.school.midland.user.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/midland/users/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createStudent(@RequestBody StudentDto studentDto,
                                                    @RequestHeader("Authorization") String authHeader) {
        boolean dto = studentService.createStudent(studentDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/uid/{studentUid}")
    public ResponseEntity<StudentDto> getStudentByUid(@PathVariable UUID studentUid) {
        return ResponseEntity.ok(studentService.getStudentByUid(studentUid));
    }

    @GetMapping("/admission/{admissionNumber}")
    public ResponseEntity<StudentDto> getStudentByAdmissionNumber(@PathVariable String admissionNumber) {
        return ResponseEntity.ok(studentService.getStudentByAdmissionNumber(admissionNumber));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDto> getBystudentEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getByStudentEmail(email));
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable String email,
                                                            @RequestBody StudentDto updatedDto) {
        return ResponseEntity.ok(studentService.updateStudent(email, updatedDto));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable String email) {
        studentService.deleteStudentByEmail(email);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponse<StudentDto>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studentId") String sortBy
    ) {
        return ResponseEntity.ok(studentService.getAllStudents(page, size, sortBy));
    }

    @GetMapping("/grade/{gradeLevel}")
    public ResponseEntity<PageResponse<StudentDto>> getStudentsByGradeLevel(@PathVariable String gradeLevel, @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "studentId") String sortBy
    ) {
        return ResponseEntity.ok(studentService.getStudentsByGradeLevel(page,size,sortBy,gradeLevel));
    }

    @GetMapping("/class")
    public ResponseEntity<List<StudentDto>> getStudentsByGradeAndSection(
            @RequestParam String grade,
            @RequestParam String section) {
        return ResponseEntity.ok(studentService.getStudentsGradeAndSection(grade, section));
    }

    @GetMapping("/year/{academicYear}")
    public ResponseEntity<PageResponse<StudentDto>> getStudentsByAcademicYear(@PathVariable String academicYear,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size,
                                                                              @RequestParam(defaultValue = "studentId") String sortBy) {
        return ResponseEntity.ok(studentService.getStudentsByAcademicYear(page,size,sortBy,academicYear));
    }

    @GetMapping("/current")
    public ResponseEntity<StudentDto> getCurrentStudent(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.getCurrentStudent(token));
    }
}

