package com.school.midland.userservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "report_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String admissionNumber;
    private Long examId;
    private Integer totalMarks;
    private Integer maxMarks;
    private String overallGrade;
    private String remarks;
    private LocalDate generatedOn = LocalDate.now();
    private String approvedByTeacherCode;
}
