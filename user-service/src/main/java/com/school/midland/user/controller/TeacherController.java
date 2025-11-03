package com.school.midland.user.controller;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.teacher.TeacherDto;
import com.school.midland.user.mappers.PageMapper;
import com.school.midland.user.service.teacher.TeacherService;
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
        Boolean saved = teacherService.createTeacher(teacherDto);
        return ResponseEntity.ok(saved);
    }

    // Get teacher by DB id
    @GetMapping("/id/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable Long id) {
        TeacherDto teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    // Get teacher by UID
    @GetMapping("/uid/{uid}")
    public ResponseEntity<TeacherDto> getByUid(@PathVariable UUID uid) {
        TeacherDto teacher = teacherService.getTeacherByUid(uid);
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
    public ResponseEntity<PageResponse<TeacherDto>> getAllTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studentId") String sortBy
    ) {
        PageResponse<TeacherDto> list = teacherService.getAllTeachers(page,size,sortBy);
        return ResponseEntity.ok(list);
    }

    // Update teacher by teacher code
    @PutMapping("/update/{email}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable String email, @RequestBody TeacherDto dto) {
        TeacherDto updated = teacherService.updateTeacher(email, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete teacher by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String schoolEmail) {
        Boolean deleted = teacherService.deleteTeacher(schoolEmail);
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
