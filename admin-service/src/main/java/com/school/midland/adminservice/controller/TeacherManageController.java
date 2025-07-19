package com.school.midland.adminservice.controller;


import com.school.midland.adminservice.service.student.StudentManageService;
import com.school.midland.adminservice.service.teacher.TeacherManageService;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.commonlib.dtos.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/midland/admin/teachers")
@RequiredArgsConstructor
public class TeacherManageController {
    private  final TeacherManageService teacherManageService;

    @PostMapping("/create")
    public TeacherDto createStudent(@RequestBody TeacherDto teacherDto){

        return teacherManageService.createTeacher(teacherDto);
    }


}
