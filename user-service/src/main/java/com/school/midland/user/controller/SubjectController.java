package com.school.midland.user.controller;

import com.school.midland.user.dto.PageResponse;
import com.school.midland.user.dto.SubjectDto;
import com.school.midland.user.service.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/users/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Boolean> createSubject(@RequestBody SubjectDto dto) {
        return ResponseEntity.ok(subjectService.createSubject(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<SubjectDto>> createSubjects(@RequestBody List<SubjectDto> dtos) {
        return ResponseEntity.ok(subjectService.createSubjects(dtos));
    }

    @GetMapping("/{code}")
    public ResponseEntity<SubjectDto> getSubjectByCode(@PathVariable String code) {
        return ResponseEntity.ok(subjectService.getSubjectByCode(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable String code, @RequestBody SubjectDto dto) {
        return ResponseEntity.ok(subjectService.updateSubject(code, dto));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Boolean> deleteSubject(@PathVariable String code) {
        return ResponseEntity.ok(subjectService.deleteSubject(code));
    }

    @GetMapping
    public ResponseEntity<PageResponse<SubjectDto>> getAllSubjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "subjectName") String sortBy) {
        return ResponseEntity.ok(subjectService.getAllSubjects(page, size, sortBy));
    }

    // ✅ Universal search endpoint
    @GetMapping("/search")
    public ResponseEntity<PageResponse<SubjectDto>> searchSubjects(
            @RequestParam(required = false) String gradeLevel,
            @RequestParam(required = false) String teacherCode,
            @RequestParam(required = false) String schoolCode,
            @RequestParam(required = false) String curriculumType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "subjectName") String sortBy) {
        return ResponseEntity.ok(subjectService.searchSubjects(gradeLevel, teacherCode, schoolCode, curriculumType, page, size, sortBy));
    }

    @GetMapping("/grade-teacher")
    public ResponseEntity<List<SubjectDto>> getSubjectsByGradeAndTeacher(
            @RequestParam String gradeLevel,
            @RequestParam String teacherCode) {
        return ResponseEntity.ok(subjectService.getSubjectsByGradeAndTeacher(gradeLevel, teacherCode));
    }
}
