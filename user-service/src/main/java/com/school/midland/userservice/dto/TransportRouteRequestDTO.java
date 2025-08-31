package com.school.midland.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record TransportRouteRequestDTO(
    @NotBlank(message = "Route name cannot be blank") String routeName,
    @NotBlank(message = "Driver name cannot be blank") String driverName,
    @NotBlank(message = "Vehicle number cannot be blank") String vehicleNumber,
    List<String> studentIds  // List of student IDs to assign to this route
) {}
