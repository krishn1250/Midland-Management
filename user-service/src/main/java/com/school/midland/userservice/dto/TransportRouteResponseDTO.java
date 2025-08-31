package com.school.midland.userservice.dto;

import java.util.List;

public record TransportRouteResponseDTO(
    String id,
    String routeName,
    String driverName,
    String vehicleNumber,
    List<StudentSummaryDTO> students  // Nested summaries of assigned students
) {
    public record StudentSummaryDTO(String id, String name) {}
}
