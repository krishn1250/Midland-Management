// --- File: controller/TeacherController.java ---
package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.TeacherRequestDTO;
import com.school.midland.userservice.dto.TeacherResponseDTO;
import com.school.midland.userservice.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@Valid @RequestBody TeacherRequestDTO request) {
        TeacherResponseDTO createdTeacher = teacherService.createTeacher(request);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @GetMapping
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
    
    // It's good practice to have other endpoints too
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable String id) {
        return ResponseEntity.ok(teacherService.getTeacherResponseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}