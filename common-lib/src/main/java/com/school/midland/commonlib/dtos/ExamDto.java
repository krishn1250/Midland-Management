package com.school.midland.commonlib.dtos;

import lombok.*;
import java.time.LocalDate;
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
    private LocalDateTime endTime;
    private String gradeLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
