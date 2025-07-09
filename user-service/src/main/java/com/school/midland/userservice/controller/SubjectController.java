package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.SubjectRequestDTO;
import com.school.midland.userservice.dto.SubjectResponseDTO;
import com.school.midland.userservice.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <-- Make sure this import is present

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // This method handles the creation of a subject (POST request)
    @PostMapping
    public ResponseEntity<SubjectResponseDTO> createSubject(@Valid @RequestBody SubjectRequestDTO subjectRequestDTO) {
        SubjectResponseDTO createdSubject = subjectService.createSubject(subjectRequestDTO);
        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }

    // --- THIS IS THE MISSING METHOD ---
    // This method handles fetching all subjects (GET request)
    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> getAllSubjects() {
        List<SubjectResponseDTO> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }
}