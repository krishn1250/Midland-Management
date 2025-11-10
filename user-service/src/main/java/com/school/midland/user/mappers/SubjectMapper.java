package com.school.midland.user.mappers;

import com.school.midland.user.dto.SubjectDto;
import com.school.midland.user.models.Subject;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SubjectMapper {

    public static Subject toEntity(SubjectDto dto) {
        if (dto == null) return null;

        return Subject.builder()
                .subjectName(dto.getSubjectName())
                .subjectCode(dto.getSubjectCode())
                .curriculumType(dto.getCurriculumType())
                .gradeLevel(dto.getGradeLevel())
                .teacherCode(dto.getTeacherCode())
                .schoolCode(dto.getSchoolCode())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public static SubjectDto toDto(Subject subject) {
        if (subject == null) return null;

        return SubjectDto.builder()
                .subjectName(subject.getSubjectName())
                .subjectCode(subject.getSubjectCode())
                .curriculumType(subject.getCurriculumType())
                .gradeLevel(subject.getGradeLevel())
                .teacherCode(subject.getTeacherCode())
                .schoolCode(subject.getSchoolCode())
                .createdAt(subject.getCreatedAt())
                .build();
    }

    public static List<SubjectDto> toDtoList(List<Subject> subjects) {
        if (subjects == null || subjects.isEmpty()) return Collections.emptyList();
        return subjects.stream()
                .filter(Objects::nonNull)
                .map(SubjectMapper::toDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<Subject> toEntityList(List<SubjectDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(SubjectMapper::toEntity)
                .collect(Collectors.toUnmodifiableList());
    }
}
