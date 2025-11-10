package com.school.midland.user.mappers;


import com.school.midland.user.dto.MarksDto;
import com.school.midland.user.models.Marks;

import java.time.format.DateTimeFormatter;

public class MarksMapper {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_DATE_TIME;

    public static MarksDto toDTO(Marks marks) {
        if (marks == null) return null;
        return MarksDto.builder()
                .id(marks.getId())
                .admissionNumber(marks.getAdmissionNumber())
                .subjectCode(marks.getSubjectCode())
                .subjectName(marks.getSubjectName())
                .examId(marks.getExamId())
                .maxMarks(marks.getMaxMarks())
                .obtainedMarks(marks.getObtainedMarks())
                .grade(marks.getGrade())
                .remarks(marks.getRemarks())
                .recordedByTeacherCode(marks.getRecordedByTeacherCode())
                .recordedAt(marks.getRecordedAt() != null ? marks.getRecordedAt().format(ISO) : null)
                .build();
    }

    public static Marks toEntity(MarksDto dto) {
        if (dto == null) return null;
        return Marks.builder()
                .id(dto.getId())
                .admissionNumber(dto.getAdmissionNumber())
                .subjectCode(dto.getSubjectCode())
                .subjectName(dto.getSubjectName())
                .examId(dto.getExamId())
                .maxMarks(dto.getMaxMarks())
                .obtainedMarks(dto.getObtainedMarks())
                .grade(dto.getGrade())
                .remarks(dto.getRemarks())
                .recordedByTeacherCode(dto.getRecordedByTeacherCode())
                .build();
    }
}
