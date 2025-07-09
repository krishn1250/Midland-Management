package com.school.midland.userservice.dto;

// In dto/StudentResponseDTO.java
// This allows you to return the section name instead of the whole object
public record StudentResponseDTO(String id, String name, String email, String sectionId, String sectionName) {}