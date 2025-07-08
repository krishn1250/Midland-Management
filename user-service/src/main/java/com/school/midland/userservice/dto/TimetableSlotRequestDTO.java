package com.school.midland.userservice.dto;// in package com.school.midland.userservice.dto
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TimetableSlotRequestDTO(
    @NotBlank(message = "Section ID cannot be blank")
    String sectionId,

    @NotBlank(message = "Subject ID cannot be blank")
    String subjectId,

    @NotBlank(message = "Teacher ID cannot be blank")
    String teacherId,

    @NotBlank(message = "Start time cannot be blank")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Start time must be in HH:mm format")
    String startTime,

    @NotBlank(message = "End time cannot be blank")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "End time must be in HH:mm format")
    String endTime,

    @NotBlank(message = "Day of week cannot be blank")
    // You can also create a custom validator to ensure it's a valid DayOfWeek
    String dayOfWeek
) {}