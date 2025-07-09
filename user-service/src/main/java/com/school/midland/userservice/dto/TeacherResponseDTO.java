package com.school.midland.userservice.dto;

import java.util.List;

public record TeacherResponseDTO(String id, String name, String email, List<String> sectionNames) {}