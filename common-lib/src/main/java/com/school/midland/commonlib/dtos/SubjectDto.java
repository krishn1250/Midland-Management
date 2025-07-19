package com.school.midland.commonlib.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private String subjectName;
    private String code;
    private String curriculumType;
    private String gradeLevel;
    private String teacherCode;
    private LocalDateTime createdAt;
}