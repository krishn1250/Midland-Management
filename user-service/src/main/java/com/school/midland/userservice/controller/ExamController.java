package com.school.midland.userservice.controller;


import com.school.midland.commonlib.dtos.ExamDto;
import com.school.midland.userservice.service.exam.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto) {
        return ResponseEntity.ok(examService.createExam(examDto));
    }

    @GetMapping
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{examId}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable Integer examId) {
        return ResponseEntity.ok(examService.getExamById(examId));
    }

    @PutMapping("/{examId}")
    public ResponseEntity<ExamDto> updateExam(@PathVariable Integer examId,
                                              @RequestBody ExamDto examDto) {
        return ResponseEntity.ok(examService.updateExam(examId, examDto));
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable Integer examId) {
        examService.deleteExam(examId);
        return ResponseEntity.noContent().build();
    }
}