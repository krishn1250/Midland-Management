package com.school.midland.userservice.controller;


import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.userservice.models.Student;
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
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto dto=studentService.getStudentById(id);
        return ResponseEntity.ok(dto);
    }
//
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/uid/{studentUid}")
    public ResponseEntity<StudentDto> getStudentByUid(@PathVariable UUID studentUid) {
        StudentDto dto=studentService.getStudentByUid(studentUid);
        return ResponseEntity.ok(dto);
    }
//
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admission/{admissionNumber}")
    public ResponseEntity<StudentDto> getByAdmissionNumber(@PathVariable String admissionNumber) {
        StudentDto dto=studentService.getStudentByAdmissionNumber(admissionNumber);
        return ResponseEntity.ok(dto);
    }
//
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{admissionNumber}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable String admissionNumber, @RequestBody StudentDto updatedDto) {
        StudentDto dto=studentService.updateStudent(admissionNumber,updatedDto);
        return ResponseEntity.ok(dto);
    }
//
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{adm_no}")
    public ResponseEntity<?> deleteStudent(@PathVariable("adm_no") String admissionNumber) {
        Boolean dto=studentService.deleteStudentByAdmissionNumber(admissionNumber);
        if(dto==false){
            return ResponseEntity.badRequest().body("unable to delete user ");
        }
        return ResponseEntity.ok("deleted sucessfully"+dto);
    }
//
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> dto=studentService.getAllStudents();
        return ResponseEntity.ok(dto);
    }
//
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/grade/{gradeLevel}")
    public ResponseEntity<List<StudentDto>> getStudentsByGradeLevel(@PathVariable String gradeLevel) {
        List<StudentDto> dto=studentService.getStudentsByGradeLevel(gradeLevel);
        return ResponseEntity.ok(dto);
    }

//    @GetMapping("/grade/{gradeLevel}")
//    public ResponseEntity<List<StudentDto>> getStudentsByGradeLevelAndSection(@PathVariable String gradeLevel,@PathVariable String section) {
//        List<StudentDto> dto=studentService.getStudentsGradeAndSection(gradeLevel,section);
//        return ResponseEntity.ok(dto);
//    }
//
    @GetMapping("/class")
    public ResponseEntity<List<StudentDto>> getStudentsByGradeAndSection(
            @RequestParam String grade,
            @RequestParam String section
    ) {
        List<StudentDto> dto=studentService.getStudentsGradeAndSection(grade,section);
        return  ResponseEntity.ok(dto);
    }
//
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/year/{academicYear}")
    public ResponseEntity<List<StudentDto>> getStudentsByAcademicYear(@PathVariable String academicYear) {
        List<StudentDto> dto=studentService.getStudentsByAcademicYear(academicYear);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/current")
    public ResponseEntity<StudentDto> getCurrentStudent(@RequestHeader("Authorization") String token) {
        StudentDto dto = studentService.getCurrentStudent(token);
        return ResponseEntity.ok(dto);
    }
}

