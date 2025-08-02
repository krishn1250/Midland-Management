package com.school.midland.userservice.controller;


import com.school.midland.commonlib.dtos.SubjectDto;
import com.school.midland.userservice.service.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/users/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/create")
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.createSubject(subjectDto));
    }

    @PostMapping("/list/create")
    public ResponseEntity<List<SubjectDto>> createSubjects(@RequestBody List<SubjectDto> subjectDto) {
        return ResponseEntity.ok(subjectService.createSubjects(subjectDto));
    }

    @PutMapping("/update/{subjectCode}")
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable String subjectCode,
                                                    @RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.updateSubject(subjectCode, subjectDto));
    }

    @DeleteMapping("/delete/{subjectCode}")
    public ResponseEntity<String> deleteSubject(@PathVariable String subjectCode) {
        boolean deleted = subjectService.deleteSubject(subjectCode);
        return ResponseEntity.ok(deleted ? "Subject deleted successfully" : "Subject not found");
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/grade/{gradeLevel}")
    public ResponseEntity<List<SubjectDto>> getSubjectsByGrade(@PathVariable String gradeLevel) {
        return ResponseEntity.ok(subjectService.getSubjectsByGrade(gradeLevel));
    }

    @GetMapping("/teacher/{teacherCode}")
    public ResponseEntity<List<SubjectDto>> getSubjectsByTeacher(@PathVariable String teacherCode) {
        return ResponseEntity.ok(subjectService.getSubjectsByTeacher(teacherCode));
    }

    @GetMapping("/subject/{subjectCode}")
    public ResponseEntity<SubjectDto> getSubjectByCode(@PathVariable String subjectCode) {
        return ResponseEntity.ok(subjectService.getSubjectByCode(subjectCode));
    }
}