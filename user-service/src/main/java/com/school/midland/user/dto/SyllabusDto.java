package com.school.midland.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyllabusDto {
    private Long id;
    private String subjectCode;
    private String subjectName;
    private String gradeLevel;
    private String curriculum;
    private String topicTitle;
    private String description;
    private String uploadedByTeacherCode;
    private LocalDateTime createdAt;
}
