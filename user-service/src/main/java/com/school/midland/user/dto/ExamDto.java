package com.school.midland.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    private Long examId;
    private UUID examCode;
    private UUID createdByAdmin;
    private String name;
    private String academicYear;
    private String examType;
    private LocalDateTime startTime;
    private String isActive;
    private LocalDateTime endTime;
    private String gradeLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
