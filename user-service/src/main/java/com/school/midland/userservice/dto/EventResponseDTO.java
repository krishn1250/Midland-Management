package com.school.midland.userservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public record EventResponseDTO(
    String id,
    String title,
    String description,
    LocalDateTime startTime,
    LocalDateTime endTime,
    List<String> sectionIds
) {}
