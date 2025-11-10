package com.school.midland.user.controller;

import com.school.midland.user.dto.ExamDto;
import com.school.midland.user.service.exam.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // Create exam
    @PostMapping("/create")
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto) {
        return ResponseEntity.ok(examService.createExam(examDto));
    }

    // Get all exams with pagination & sorting
    @GetMapping
    public ResponseEntity<Page<ExamDto>> getAllExams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        Page<ExamDto> exams = examService.getAllExams(page, size, sortBy, sortDir);
        return ResponseEntity.ok(exams);
    }

    // Get single exam by ID
    @GetMapping("/{examId}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable Long examId) {
        return ResponseEntity.ok(examService.getExamById(examId));
    }

    // Update exam
    @PutMapping("/{examId}")
    public ResponseEntity<ExamDto> updateExam(
            @PathVariable Long examId,
            @RequestBody ExamDto examDto
    ) {
        return ResponseEntity.ok(examService.updateExam(examId, examDto));
    }

    // Soft-delete exam
    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long examId) {
        examService.deleteExam(examId);
        return ResponseEntity.noContent().build();
    }

    // Get exams by grade
    @GetMapping("/grade/{gradeLevel}")
    public ResponseEntity<List<ExamDto>> getExamsByGrade(@PathVariable String gradeLevel) {
        List<ExamDto> exams = examService.getExamsByGrade(gradeLevel);
        return ResponseEntity.ok(exams);
    }
}
