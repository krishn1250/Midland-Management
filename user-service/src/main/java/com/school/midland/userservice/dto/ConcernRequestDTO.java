package com.school.midland.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record ConcernRequestDTO(
    @NotBlank(message = "Description cannot be blank") String description,
    @NotBlank(message = "Student ID cannot be blank") String studentId,
    String status,  // e.g., "OPEN", "RESOLVED" (optional, defaults in service)
    String remarks  // Optional additional notes
) {}
