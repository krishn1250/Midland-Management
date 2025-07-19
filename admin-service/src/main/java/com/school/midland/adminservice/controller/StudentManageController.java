package com.school.midland.adminservice.controller;

import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.adminservice.service.student.StudentManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/midland/admin/students")
@RequiredArgsConstructor
public class StudentManageController {

    private  final StudentManageService studentManageService;

    @PostMapping("/create")
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
return studentManageService.createStudent(studentDto);
    }
}
