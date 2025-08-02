package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.SubjectDto;
import com.school.midland.userservice.models.Subject;

import java.util.List;
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
                .createdAt(subject.getCreatedAt())
                .build();
    }
    public static List<SubjectDto> toDtoList(List<Subject> subjects) {
        return subjects.stream().map(SubjectMapper::toDto).collect(Collectors.toList());
    }

    public static List<Subject> toEntityList(List<SubjectDto> dtos) {
        return dtos.stream().map(SubjectMapper::toEntity).collect(Collectors.toList());
    }
}
