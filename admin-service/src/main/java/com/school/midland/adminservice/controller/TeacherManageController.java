package com.school.midland.adminservice.controller;


import com.school.midland.adminservice.service.student.StudentManageService;
import com.school.midland.adminservice.service.teacher.TeacherManageService;
import com.school.midland.commonlib.dtos.StudentDto;
import com.school.midland.commonlib.dtos.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/admin/teachers")
@RequiredArgsConstructor
public class TeacherManageController {
    private  final TeacherManageService teacherManageService;

    @PostMapping("/create")
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(teacherManageService.createTeacher(teacherDto));
    }


    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherManageService.getAllTeachers());
    }


    @GetMapping("/code/{teacherCode}")
    public ResponseEntity<TeacherDto> getTeacherByCode(@PathVariable String teacherCode) {
        return ResponseEntity.ok(teacherManageService.getTeacherByCode(teacherCode));
    }


    @PutMapping("/update/{teacherCode}")
    public ResponseEntity<TeacherDto> updateTeacher(
            @PathVariable String teacherCode,
            @RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(teacherManageService.updateTeacher(teacherCode, teacherDto));
    }


    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String username) {
        boolean deleted = teacherManageService.deleteTeacher(username);
        return ResponseEntity.ok(deleted ? "Deleted Successfully" : "Not Found or Not Deleted");
    }


    @GetMapping("/department/{department}")
    public ResponseEntity<List<TeacherDto>> getByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(teacherManageService.getTeacherByDepartment(department));
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<TeacherDto> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(teacherManageService.getTeacherByUsername(username));
    }


}
