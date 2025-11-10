package com.school.midland.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarksDto {
    private Long id;
    private String admissionNumber;
    private String subjectCode;
    private String subjectName;
    private Long examId;
    private Integer maxMarks;
    private Integer obtainedMarks;
    private String grade;
    private String remarks;
    private String recordedByTeacherCode;
    private String recordedAt;
}
