package com.school.midland.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public record EventRequestDTO(
    @NotBlank String title,
    String description,
    LocalDateTime startTime,
    LocalDateTime endTime,
    List<String> sectionIds
) {}