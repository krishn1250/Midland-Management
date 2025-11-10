package com.school.midland.user.controller;

import com.school.midland.user.dto.SyllabusDto;
import com.school.midland.user.mappers.SyllabusMapper;
import com.school.midland.user.models.Syllabus;
import com.school.midland.user.security.JwtTokenValidator;
import com.school.midland.user.service.syllabus.SyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/users/syllabus")
@RequiredArgsConstructor
public class SyllabusController {

    private final SyllabusService syllabusService;
    private final JwtTokenValidator jwtTokenValidator;

    // 🟢 Add new syllabus (JWT authenticated teacher)
    @PostMapping("/add")
    public ResponseEntity<SyllabusDto> addSyllabus(
            @RequestBody SyllabusDto syllabusDto,
            @RequestHeader("Authorization") String authHeader) {

        String token = extractToken(authHeader);
        String teacherCode = jwtTokenValidator.extractAssociateIdentifier(token);
        syllabusDto.setUploadedByTeacherCode(teacherCode);

        Syllabus syllabusEntity = SyllabusMapper.mapToEntity(syllabusDto);
        SyllabusDto savedSyllabus = syllabusService.addSyllabus(syllabusEntity);
        return ResponseEntity.ok(savedSyllabus);
    }

    // 🟢 Get syllabus by grade & subject code
    @GetMapping("/by-subject-code")
    public ResponseEntity<List<SyllabusDto>> getByGradeAndSubjectCode(
            @RequestParam String gradeLevel,
            @RequestParam String subjectCode) {
        return ResponseEntity.ok(syllabusService.getSyllabusByGradeLevelAndSubjectCode(gradeLevel, subjectCode));
    }

    // 🟢 Get syllabus by grade & subject name
    @GetMapping("/by-subject-name")
    public ResponseEntity<List<SyllabusDto>> getByGradeAndSubjectName(
            @RequestParam String gradeLevel,
            @RequestParam String subjectName) {
        return ResponseEntity.ok(syllabusService.getSyllabusByGradeLevelAndSubjectName(gradeLevel, subjectName));
    }

    // 🟢 Get all syllabus by grade level
    @GetMapping("/by-grade")
    public ResponseEntity<List<SyllabusDto>> getByGradeLevel(@RequestParam String gradeLevel) {
        return ResponseEntity.ok(syllabusService.getSyllabusByGradeLevel(gradeLevel));
    }

    // 🟢 Get syllabus by ID
    @GetMapping("/{id}")
    public ResponseEntity<SyllabusDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(syllabusService.getSyllabusById(id));
    }

    // 🔴 Delete syllabus by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSyllabus(@PathVariable Long id) {
        syllabusService.deleteSyllabus(id);
        return ResponseEntity.noContent().build();
    }

    // 🔒 Helper method
    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("Invalid Authorization header");
    }
}
