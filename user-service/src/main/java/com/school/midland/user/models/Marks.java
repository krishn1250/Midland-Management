package com.school.midland.user.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "marks",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"admission_number", "subject_code", "exam_id"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admission_number", nullable = false)
    private String admissionNumber;

    @Column(name = "subject_code", nullable = false)
    private String subjectCode;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "exam_id", nullable = false)
    private Long examId;

    @Column(name = "max_marks", nullable = false)
    private Integer maxMarks;

    @Column(name = "obtained_marks", nullable = false)
    private Integer obtainedMarks;

    @Column(name = "grade")
    private String grade;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "recorded_by_teacher_code", nullable = false)
    private String recordedByTeacherCode;

    @Column(name = "recorded_at", nullable = false, updatable = false)
    private LocalDateTime recordedAt = LocalDateTime.now();
}
