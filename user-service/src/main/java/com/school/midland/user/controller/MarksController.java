package com.school.midland.user.controller;


import com.school.midland.user.dto.MarksDto;
import com.school.midland.user.exception.UserException;
import com.school.midland.user.service.marks.MarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/midland/users/marks")
@RequiredArgsConstructor
public class MarksController {

    private final MarksService marksService;

    @PostMapping("/add")
    public ResponseEntity<MarksDto> addMarks(@RequestBody MarksDto dto) {
        if (dto.getObtainedMarks() != null && dto.getMaxMarks() != null && dto.getObtainedMarks() > dto.getMaxMarks()) {
            throw new UserException("Obtained marks cannot exceed maximum marks", HttpStatus.BAD_REQUEST);
        }
        MarksDto saved = marksService.addMarks(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarksDto> updateMarks(@PathVariable Long id, @RequestBody MarksDto dto) {
        if (dto.getObtainedMarks() != null && dto.getMaxMarks() != null && dto.getObtainedMarks() > dto.getMaxMarks()) {
            throw new UserException("Obtained marks cannot exceed maximum marks", HttpStatus.BAD_REQUEST);
        }
        MarksDto updated = marksService.updateMarks(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarks(@PathVariable Long id) {
        marksService.deleteMarks(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarksDto> getMarksById(@PathVariable Long id) {
        return ResponseEntity.ok(marksService.getMarksById(id));
    }

    @GetMapping("/student/{admissionNumber}/exam/{examId}")
    public ResponseEntity<List<MarksDto>> getStudentMarks(
            @PathVariable String admissionNumber,
            @PathVariable Long examId) {
        return ResponseEntity.ok(marksService.getStudentMarks(admissionNumber, examId));
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<MarksDto>> getExamMarks(@PathVariable Long examId) {
        return ResponseEntity.ok(marksService.getExamMarks(examId));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<MarksDto>> getAllMarks() {
        return ResponseEntity.ok(marksService.getAllMarks());
    }
}
