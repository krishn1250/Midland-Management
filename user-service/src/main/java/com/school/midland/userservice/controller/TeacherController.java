package com.school.midland.userservice.controller;

import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.userservice.models.Teacher;
import com.school.midland.userservice.service.teacher.TeacherService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/midland/users/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto){
        final TeacherDto teacher = teacherService.createTeacher(teacherDto);
        return ResponseEntity.ok(teacher);
    }
}
