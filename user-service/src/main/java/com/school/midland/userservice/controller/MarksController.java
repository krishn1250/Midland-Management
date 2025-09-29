package com.school.midland.userservice.controller;



import com.school.midland.commonlib.dtos.MarksDto;
import com.school.midland.commonlib.exception.UserException;
import com.school.midland.userservice.service.marks.MarksService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
@RequiredArgsConstructor
public class MarksController {

    private final MarksService marksService;

    @PostMapping("/add")
    public ResponseEntity<MarksDto> addMarks( @RequestBody MarksDto dto) {
        // Basic validation also in service; this is just early feedback
        if (dto.getObtainedMarks() != null && dto.getMaxMarks() != null && dto.getObtainedMarks() > dto.getMaxMarks()) {
            throw new UserException("obtainedMarks cannot be greater than maxMarks", HttpStatus.BAD_REQUEST);
        }
        MarksDto saved = marksService.addMarks(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarksDto> updateMarks(@PathVariable Long id,  @RequestBody MarksDto dto) {
        if (dto.getObtainedMarks() != null && dto.getMaxMarks() != null && dto.getObtainedMarks() > dto.getMaxMarks()) {
            throw new UserException("obtainedMarks cannot be greater than maxMarks",HttpStatus.BAD_REQUEST);
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
        MarksDto dto = marksService.getMarksById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/student/{admissionNumber}/exam/{examId}")
    public ResponseEntity<List<MarksDto>> getStudentMarks(
            @PathVariable String admissionNumber,
            @PathVariable Long examId) {

        List<MarksDto> list = marksService.getStudentMarks(admissionNumber, examId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<MarksDto>> getExamMarks(@PathVariable Long examId) {
        List<MarksDto> list = marksService.getExamMarks(examId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<MarksDto>> getAllMarks() {
        List<MarksDto> list = marksService.getAllMarks();
        return ResponseEntity.ok(list);
    }
}