// --- File: dto/AttendanceRequestDTO.java ---
package com.school.midland.userservice.dto;

import com.school.midland.userservice.model.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AttendanceRequestDTO(
    @NotBlank(message = "Timetable Slot ID cannot be blank")
    String timetableSlotId,

    @NotBlank(message = "Student ID cannot be blank")
    String studentId,
    
    // ADD THIS FIELD
    @NotBlank(message = "Class date cannot be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Class date must be in YYYY-MM-DD format")
    String classDate,

    @NotNull(message = "Attendance status cannot be null")
    AttendanceStatus status,

    String remarks
) {}