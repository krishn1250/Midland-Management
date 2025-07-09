// --- File: dto/TimetableSlotResponseDTO.java ---
package com.school.midland.userservice.dto;

import com.school.midland.userservice.model.TimetableSlot;
import java.time.DayOfWeek;

public record TimetableSlotResponseDTO(
    String id,
    DayOfWeek dayOfWeek,
    String startTime,
    String endTime,
    SectionSummaryDTO section,
    TeacherSummaryDTO teacher,
    SubjectSummaryDTO subject
) {
    /**
     * Factory method to create a DTO from a TimetableSlot entity.
     * This handles potential nulls and formats the data correctly.
     */
    public static TimetableSlotResponseDTO fromEntity(TimetableSlot slot) {
        return new TimetableSlotResponseDTO(
            slot.getId(),
            slot.getDayOfWeek(),
            slot.getStartTime().toString(),
            slot.getEndTime().toString(),
            new SectionSummaryDTO(slot.getSection().getId(), slot.getSection().getName()),
            new TeacherSummaryDTO(slot.getTeacher().getId(), slot.getTeacher().getName()),
            new SubjectSummaryDTO(slot.getSubject().getId(), slot.getSubject().getName())
        );
    }

    // Nested records for clean JSON structure
    public record SectionSummaryDTO(String id, String name) {}
    public record TeacherSummaryDTO(String id, String name) {}
    public record SubjectSummaryDTO(String id, String name) {}
}