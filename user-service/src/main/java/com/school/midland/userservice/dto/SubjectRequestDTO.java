package com.school.midland.userservice.dto;

import jakarta.validation.constraints.NotBlank;

public record SubjectRequestDTO(@NotBlank String name) {}