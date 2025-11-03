package com.school.midland.admin.controller;



import com.school.midland.admin.client.auth.dto.RegisterResponse;
import com.school.midland.admin.client.teacher.dto.TeacherDto;
import com.school.midland.admin.client.teacher.dto.TeacherResponseDto;
import com.school.midland.admin.service.teacher.TeacherManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/midland/admin/teachers")
@RequiredArgsConstructor
public class TeacherManageController {

    private final TeacherManageService teacherManageService;

    // ✅ Create Teacher
    @PostMapping("/create")
    public RegisterResponse createTeacher(@RequestBody TeacherDto teacherDto,
                                          @RequestHeader("Authorization") String token) {
        System.out.println(teacherDto);
        return teacherManageService.createTeacher(teacherDto, token);
    }

    // ✅ Delete Teacher by Email
    @DeleteMapping("/delete/{email}")
    public boolean deleteTeacher(@PathVariable String email,
                                 @RequestHeader("Authorization") String token) {
        return teacherManageService.deleteTeacher(email, token);
    }

    // ✅ Update Teacher by Code
    @PutMapping("/update/{email}")
    public TeacherResponseDto updateTeacher(@PathVariable String email,
                                            @RequestBody TeacherDto updatedDto,
                                            @RequestHeader("Authorization") String token) {
        TeacherResponseDto responseDto = teacherManageService.updateTeacher(email, updatedDto, token);
        System.out.println("Updated Teacher: " + responseDto);
        return responseDto;
    }

    // ✅ Get Teacher by Database ID
    @GetMapping("id//{id}")
    public ResponseEntity<TeacherResponseDto> getById(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(teacherManageService.getById(id, token));
    }

    // ✅ Get Teacher by UID
    @GetMapping("/uid/{uid}")
    public ResponseEntity<TeacherResponseDto> getByUid(@PathVariable UUID uid,
                                                       @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(teacherManageService.getByUid(uid, token));
    }

    // ✅ Get Teacher by Code
    @GetMapping("/code/{code}")
    public ResponseEntity<TeacherResponseDto> getByCode(@PathVariable String code,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(teacherManageService.getByCode(code, token));
    }

    // ✅ Get All Teachers (Paged)
    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "teacherId") String sortBy,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(teacherManageService.getAllTeachers(page, size, sortBy, token));
    }

    // ✅ Get Teachers by Department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<TeacherResponseDto>> getByDepartment(@PathVariable String department,
                                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(teacherManageService.getByDepartment(department, token));
    }

    // ✅ Get Teachers by Designation
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<TeacherResponseDto>> getByDesignation(@PathVariable String designation,
                                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(teacherManageService.getByDesignation(designation, token));
    }

    // ✅ Get Teacher by Username
    @GetMapping("/username/{username}")
    public ResponseEntity<TeacherResponseDto> getByUsername(@PathVariable String username,
                                                            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(teacherManageService.getByUsername(username, token));
    }
}
