package com.school.midland.user.mappers;



import com.school.midland.user.dto.ExamDto;
import com.school.midland.user.models.Exams;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExamMapper {

    public static ExamDto toDto(Exams exam) {
        if (exam == null) return null;

        return ExamDto.builder()
                .examId(exam.getExamId())
                .examCode(exam.getExamCode())
                .createdByAdmin(exam.getCreatedByAdmin())
                .name(exam.getName())
                .academicYear(exam.getAcademicYear())
                .examType(exam.getExamType())
                .startTime(exam.getStartTime())
                .endTime(exam.getEndTime())
                .gradeLevel(exam.getGradeLevel())
                .createdAt(exam.getCreatedAt())
                .updatedAt(exam.getUpdatedAt())
                .build();
    }

    public static Exams toEntity(ExamDto dto) {
        if (dto == null) return null;

        return Exams.builder()
                .examId(dto.getExamId())
                .examCode(dto.getExamCode() != null ? dto.getExamCode() : UUID.randomUUID())
                .createdByAdmin(dto.getCreatedByAdmin())
                .name(dto.getName())
                .academicYear(dto.getAcademicYear())
                .examType(dto.getExamType())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .gradeLevel(dto.getGradeLevel())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
