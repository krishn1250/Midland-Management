package com.school.midland.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCardDto {
    private String admissionNumber;
    private Long examId;
    private Integer totalMarks;
    private Integer maxMarks;
    private String overallGrade;
    private String remarks;
    private String approvedByTeacherCode;
}