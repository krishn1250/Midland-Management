package com.school.midland.adminservice.controller;

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
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
return studentManageService.createStudent(studentDto);
    }
    @DeleteMapping("/delete/{admissionNumber}")
    public boolean deleteStudent(@PathVariable String admissionNumber) {
        return studentManageService.deleteStudent(admissionNumber);
    }

    @PutMapping("/update/{admissionNumber}")
    public StudentDto updateStudent(@PathVariable String admissionNumber, @RequestBody StudentDto updatedDto) {
        return studentManageService.updateStudent(admissionNumber, updatedDto);
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
