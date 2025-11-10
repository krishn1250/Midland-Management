package com.school.midland.user.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subjects",
        indexes = {
                @Index(name = "idx_subject_code", columnList = "subjectCode"),
                @Index(name = "idx_teacher_code", columnList = "teacherCode"),
                @Index(name = "idx_grade_level", columnList = "gradeLevel")
        })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(nullable = false)
    private String subjectName;

    @Column(nullable = false, unique = true)
    private String subjectCode;

    private String curriculumType;

    private String gradeLevel;

    @Column(name = "school_code", nullable = false)
    private String schoolCode;

    @Column(nullable = false)
    private String teacherCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String updatedBy;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
