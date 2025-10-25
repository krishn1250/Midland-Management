package com.school.midland.userservice.controller;

import com.school.midland.commonlib.dtos.TeacherDto;
import com.school.midland.userservice.dto.teacher.TeacherResponseDto;
import com.school.midland.userservice.models.Teacher;
import com.school.midland.userservice.service.teacher.TeacherService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/midland/users/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    // Create teacher
    @PostMapping("/create")
    public ResponseEntity<Boolean> createTeacher(@RequestBody TeacherDto teacherDto) {
        boolean saved = teacherService.createTeacher(teacherDto);
        return ResponseEntity.ok(saved);
    }

    // Get teacher by DB id
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable Long id) {
        TeacherDto teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    // Get teacher by UID
    @GetMapping("/email/{email}")
    public ResponseEntity<TeacherDto> getByUid(@PathVariable String email) {
        TeacherDto teacher = teacherService.getTeacherByEmail(email);
        return ResponseEntity.ok(teacher);
    }

    // Get teacher by teacher code
    @GetMapping("/code/{code}")
    public ResponseEntity<TeacherDto> getByCode(@PathVariable String code) {
        TeacherDto teacher = teacherService.getTeacherByCode(code);
        return ResponseEntity.ok(teacher);
    }

    // Get all teachers
    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> list = teacherService.getAllTeachers();
        return ResponseEntity.ok(list);
    }

    // Update teacher by teacher code
    @PutMapping("/update/{email}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable String email, @RequestBody TeacherDto dto) {
        TeacherResponseDto updated = teacherService.updateTeacher(email, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete teacher by id
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String email) {
        System.out.println("hello bros");
        Boolean deleted = teacherService.deleteTeacher(email);
        return ResponseEntity.ok(deleted ? "Deleted Successfully" : "Not Found or Not Deleted");
    }

    // Find by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<TeacherDto>> getByDepartment(@PathVariable String department) {
        List<TeacherDto> list = teacherService.findByDepartment(department);
        return ResponseEntity.ok(list);
    }

    // Find by designation
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<TeacherDto>> getByDesignation(@PathVariable String designation) {
        List<TeacherDto> list = teacherService.findByDesignation(designation);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/username/{username}")
    public  ResponseEntity<TeacherDto> getByUsername(@PathVariable String username){
        TeacherDto byUsername = teacherService.getByUsername(username);
        return ResponseEntity.ok(byUsername);
    }
}
