package com.school.midland.userservice.controller;

import com.school.midland.commonlib.dtos.SyllabusDto;
import com.school.midland.userservice.mappers.SyllabusMapper;
import com.school.midland.userservice.models.Syllabus;
import com.school.midland.userservice.security.JwtTokenValidator;
import com.school.midland.userservice.service.syllabus.SyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/syllabus")
@RequiredArgsConstructor
public class SyllabusController {

    private final SyllabusService syllabusService;
    private final JwtTokenValidator jwtTokenValidator;

    // 🟢 Add Syllabus (JWT Authenticated Teacher)
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

    // 🟢 Get Syllabus by GradeLevel & SubjectCode
    @GetMapping("/get")
    public ResponseEntity<List<SyllabusDto>> getSyllabusByGradeAndSubject(
            @RequestParam String gradeLevel,
            @RequestParam String subjectCode) {

        List<SyllabusDto> syllabusList = syllabusService.getSyllabusByGradeLevelAndSubjectCode(gradeLevel, subjectCode);
        return ResponseEntity.ok(syllabusList);
    }

    // 🟢 Get Syllabus by GradeLevel & SubjectName
    @GetMapping("/get/subject")
    public ResponseEntity<List<SyllabusDto>> getSyllabusByGradeAndSubjectName(
            @RequestParam String gradeLevel,
            @RequestParam String subjectName) {

        List<SyllabusDto> syllabusList = syllabusService.getSyllabusByGradeLevelAndSubjectName(gradeLevel, subjectName);
        return ResponseEntity.ok(syllabusList);
    }

    // 🟢 Get Syllabus by GradeLevel (All subjects)
    @GetMapping("/get/grade")
    public ResponseEntity<List<SyllabusDto>> getSyllabusByGradeLevel(
            @RequestParam String gradeLevel) {

        List<SyllabusDto> syllabusList = syllabusService.getSyllabusByGradeLevel(gradeLevel);
        return ResponseEntity.ok(syllabusList);
    }

    // 🟢 Get Syllabus by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<SyllabusDto> getSyllabusById(@PathVariable Long id) {
        SyllabusDto syllabus = syllabusService.getSyllabusById(id);
        return ResponseEntity.ok(syllabus);
    }

    // 🔴 Delete Syllabus by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSyllabus(@PathVariable Long id) {
        syllabusService.deleteSyllabus(id);
        return ResponseEntity.noContent().build();
    }

    // 🔒 Helper Method to Extract Token
    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            throw new RuntimeException("Invalid Authorization header");
        }
    }
}