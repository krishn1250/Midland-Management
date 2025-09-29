package com.school.midland.userservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data @Builder
@Setter @Getter  @AllArgsConstructor
@Entity
@Table(name = "marks",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"admission_number", "subject_code", "exam_id"})
        })
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

    @Column(name = "max_marks")
    private Integer maxMarks;

    @Column(name = "obtained_marks")
    private Integer obtainedMarks;

    @Column(name = "grade")
    private String grade;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "recorded_by_teacher_code", nullable = false)
    private String recordedByTeacherCode;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt = LocalDateTime.now();

    // Constructors
    public Marks() {}

    // Getters & Setters
    // ...
}