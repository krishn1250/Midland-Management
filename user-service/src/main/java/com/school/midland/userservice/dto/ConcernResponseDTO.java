package com.school.midland.userservice.dto;

import java.time.LocalDateTime;

public record ConcernResponseDTO(
    String id,
    String description,
    LocalDateTime submittedAt,
    String status,
    StudentSummaryDTO student  // Nested summary to avoid full entity exposure
) {
    public record StudentSummaryDTO(String id, String name) {}
}
