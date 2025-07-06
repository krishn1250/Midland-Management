package com.studentmanagement.test02.dto;

public record SectionDTO(
    Long sectionId,
    String grade,
    String sectionName,
    String academicYear
) {}