package com.school.midland.admin.controller;

import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.client.student.dto.StudentResponseDto;
import com.school.midland.admin.service.student.StudentManageService;
import com.school.midland.admin.client.student.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/admin/students")
@RequiredArgsConstructor
public class StudentManageController {

    private  final StudentManageService studentManageService;

    @PostMapping("/create")
    public RegisterResponse createStudent(@RequestBody StudentDto studentDto, @RequestHeader("Authorization")String token){
        System.out.println(studentDto);
        return studentManageService.createStudent(studentDto,token);
    }
    @DeleteMapping("/delete/{email}")
    public boolean deleteStudent(@PathVariable String email,@RequestHeader("Authorization")String token) {
        return studentManageService.deleteStudent(email,token);
    }

    @PutMapping("/update/{email}")
    public  StudentResponseDto updateStudent(@PathVariable String email, @RequestBody StudentDto updatedDto,
                                            @RequestHeader("Authorization") String token) {
        StudentResponseDto responseDto=studentManageService.updateStudent(email, updatedDto,token);
        System.out.println("yoyo "+responseDto);
        return responseDto;
    }

    @GetMapping("/admission/{admissionNumber}")
    public ResponseEntity<StudentResponseDto> getByAdmissionNumber(@PathVariable String admissionNumber, @RequestHeader("Authorization")String token) {

        return ResponseEntity.ok(studentManageService.getByAdmissionNumber(admissionNumber,token));
    }

    @GetMapping("/email/{email}")
    public StudentResponseDto getBystudentEmail(@PathVariable String email,@RequestHeader("Authorization") String token){
        return studentManageService.getByEmail(email,token);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studentId") String sortBy,
             @RequestHeader("Authorization")String token
    ) {
        return ResponseEntity.ok(studentManageService.getAllStudents(page, size, sortBy,token));
    }


    @GetMapping("/year/{academicYear}")
    public ResponseEntity<List<StudentResponseDto>> getByAcademicYear(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studentId") String sortBy,
            @PathVariable String academicYear
//            @RequestHeader("Authorization")String token

    ){
return  ResponseEntity.ok(studentManageService.getByAcademicYear(page,size,sortBy,academicYear));
    }

    @GetMapping("/gradeLevel/{gradeLevel}")
    public ResponseEntity<List<StudentResponseDto>> getByGradeLevel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studentId") String sortBy,
            @PathVariable String gradeLevel
    ){
return  ResponseEntity.ok(studentManageService.getByGradeLevel(page,size,sortBy,gradeLevel));
    }

    @GetMapping("/class")
    public ResponseEntity<List<StudentResponseDto>> getByGradeAndSection( @RequestParam String grade,
                                                                          @RequestParam String section){
        return ResponseEntity.ok(studentManageService.getByGradeAndSection(grade,section));
    }
}
