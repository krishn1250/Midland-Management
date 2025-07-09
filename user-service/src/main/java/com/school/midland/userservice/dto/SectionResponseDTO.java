package com.school.midland.userservice.dto;

import com.school.midland.userservice.model.Section;

import java.util.List;

public record SectionResponseDTO(
    String id,
    String name,
    List<SubjectSummaryDTO> subjects,
    List<TeacherSummaryDTO> teachers
) {

    public SectionResponseDTO(Section section) {
        this(
            section.getId(),
            section.getName(),
            section.getSubjects().stream()
                .map(subject -> new SubjectSummaryDTO(subject.getId(), subject.getName()))
                .toList(),
            section.getTeachers().stream()
                .map(teacher -> new TeacherSummaryDTO(teacher.getId(), teacher.getName(), teacher.getEmail()))
                .toList()
        );
    }

    /**
     * A lightweight summary of a Subject.
     */
    public record SubjectSummaryDTO(String id, String name) {}

    /**
     * A lightweight summary of a Teacher.
     */
    public record TeacherSummaryDTO(String id, String name, String email) {}
}