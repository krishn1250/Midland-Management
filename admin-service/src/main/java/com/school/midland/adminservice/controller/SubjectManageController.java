package com.school.midland.adminservice.controller;

import com.school.midland.adminservice.service.subject.SubjectManageService;
import com.school.midland.commonlib.dtos.SubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/admin/subjects")
@RequiredArgsConstructor
public class SubjectManageController {

    private final SubjectManageService subjectManageService;

    @PostMapping("/create")
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto dto) {
        return ResponseEntity.ok(subjectManageService.createSubject(dto));
    }

    @PostMapping("/list/create")
    public ResponseEntity<List<SubjectDto>> createSubjectList(@RequestBody List<SubjectDto> dto) {
        return ResponseEntity.ok(subjectManageService.createSubjects(dto));
    }

    @PutMapping("/update/{subjectCode}")
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable String subjectCode, @RequestBody SubjectDto dto) {
        return ResponseEntity.ok(subjectManageService.updateSubject(subjectCode, dto));
    }

    @DeleteMapping("/delete/{subjectCode}")
    public ResponseEntity<Boolean> deleteSubject(@PathVariable String subjectCode) {
        return ResponseEntity.ok(subjectManageService.deleteSubject(subjectCode));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        System.out.println("hitt");
        return ResponseEntity.ok(subjectManageService.getAllSubjects());
    }

    @GetMapping("/grade/{gradeLevel}")
    public ResponseEntity<List<SubjectDto>> getSubjectsByGrade(@PathVariable String gradeLevel) {
        return ResponseEntity.ok(subjectManageService.getSubjectsByGrade(gradeLevel));
    }

    @GetMapping("/teacher/{teacherCode}")
    public ResponseEntity<List<SubjectDto>> getSubjectsByTeacher(@PathVariable String teacherCode) {
        return ResponseEntity.ok(subjectManageService.getSubjectsByTeacher(teacherCode));
    }

    @GetMapping("/subject/{subjectCode}")
    public ResponseEntity<SubjectDto> getSubjectByCode(@PathVariable String subjectCode) {
        return ResponseEntity.ok(subjectManageService.getSubjectByCode(subjectCode));
    }
}