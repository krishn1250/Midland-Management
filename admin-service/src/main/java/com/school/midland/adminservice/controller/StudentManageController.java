package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.client.dtos.UserCreationResponse;
import com.school.midland.adminservice.client.service.student.dto.StudentResponseDto;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.adminservice.service.student.StudentManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/admin/students")
@RequiredArgsConstructor
public class StudentManageController {

    private  final StudentManageService studentManageService;

    @PostMapping("/create")
    public UserCreationResponse createStudent(@RequestBody StudentDto studentDto, @RequestHeader("Authorization")String token){
        System.out.println(studentDto);
return studentManageService.createStudent(studentDto,token);
    }
    @DeleteMapping("/delete/{email}")
    public boolean deleteStudent(@PathVariable String email,@RequestHeader("Authorization")String token) {
        return studentManageService.deleteStudent(email,token);
    }

    @PutMapping("/update/{email}")
    public StudentResponseDto updateStudent(@PathVariable String email, @RequestBody StudentDto updatedDto,
                                            @RequestHeader("Authorization") String token) {
        return studentManageService.updateStudent(email, updatedDto,token);
    }

    @GetMapping("/{admissionNumber}")
    public StudentDto getByAdmissionNumber(@PathVariable String admissionNumber) {
        return studentManageService.getByAdmissionNumber(admissionNumber);
    }

    @GetMapping("/all")
    public List<StudentDto> getAllStudents() {
        return studentManageService.getAllStudents();
    }
}
