// --- File: controller/SectionController.java ---
package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.SectionRequestDTO;
import com.school.midland.userservice.dto.SectionResponseDTO;
import com.school.midland.userservice.model.Section;
import com.school.midland.userservice.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {
    private final SectionService sectionService;

    // ... (POST and GET methods are unchanged) ...
    @PostMapping
    public ResponseEntity<SectionResponseDTO> createSection(@Valid @RequestBody SectionRequestDTO request) {
        SectionResponseDTO createdSection = sectionService.createSection(request);
        return new ResponseEntity<>(createdSection, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> getSectionById(@PathVariable String id) {
        // ... (unchanged)
        Section section = sectionService.getSectionById(id);
        SectionResponseDTO sectionResponse = new SectionResponseDTO(section);
        return ResponseEntity.ok(sectionResponse);
    }

    @GetMapping
    public ResponseEntity<List<SectionResponseDTO>> getAllSections() {
        // ... (unchanged)
        List<SectionResponseDTO> sections = sectionService.getAllSections();
        return ResponseEntity.ok(sections);
    }
    
    // --- ADD THIS NEW @PutMapping METHOD ---
    @PutMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> updateSection(
            @PathVariable String id,
            @Valid @RequestBody SectionRequestDTO request) {
        SectionResponseDTO updatedSection = sectionService.updateSection(id, request);
        return ResponseEntity.ok(updatedSection);
    }
}