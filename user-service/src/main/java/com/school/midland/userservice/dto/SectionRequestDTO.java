package com.school.midland.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * DTO for creating or updating a Section.
 * The client provides the IDs of the subjects and teachers to be associated with this section.
 */
public record SectionRequestDTO(
    @NotBlank(message = "Section name cannot be blank")
    String name,

    // List of Subject IDs to associate with this section
    List<String> subjectIds,

    // List of Teacher IDs to associate with this section
    List<String> teacherIds
) {}