package com.school.midland.user.dto;

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
    private String subjectCode;
    private String curriculumType;
    private String gradeLevel;
    private String teacherCode;
    private  String schoolCode;
    private LocalDateTime createdAt;
}