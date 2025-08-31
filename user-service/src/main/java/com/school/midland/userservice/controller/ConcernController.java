package com.school.midland.userservice.controller;

import com.school.midland.userservice.dto.ConcernRequestDTO;
import com.school.midland.userservice.dto.ConcernResponseDTO;
import com.school.midland.userservice.service.ConcernService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concerns")
@RequiredArgsConstructor
public class ConcernController {
    private final ConcernService concernService;

    @PostMapping
    public ResponseEntity<ConcernResponseDTO> createConcern(@Valid @RequestBody ConcernRequestDTO request) {
        ConcernResponseDTO created = concernService.createConcern(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConcernResponseDTO>> getAllConcerns() {
        List<ConcernResponseDTO> concerns = concernService.getAllConcerns();
        return ResponseEntity.ok(concerns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcernResponseDTO> getConcernById(@PathVariable String id) {
        ConcernResponseDTO concern = concernService.getConcernById(id);
        return ResponseEntity.ok(concern);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcernResponseDTO> updateConcern(@PathVariable String id, @Valid @RequestBody ConcernRequestDTO request) {
        ConcernResponseDTO updated = concernService.updateConcern(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcern(@PathVariable String id) {
        concernService.deleteConcern(id);
        return ResponseEntity.noContent().build();
    }
}
