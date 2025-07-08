package com.school.midland.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TeacherRequestDTO(
        @NotBlank String name,
        @Email @NotBlank String email,
        List<String> sectionIds // List of IDs to assign the teacher to
) {}