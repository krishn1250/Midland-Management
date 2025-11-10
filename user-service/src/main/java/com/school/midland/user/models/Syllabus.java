package com.school.midland.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "syllabus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Syllabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_code", nullable = false)
    private String subjectCode;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "grade_level", nullable = false)
    private String gradeLevel;

    @Column(columnDefinition = "TEXT")
    private String curriculum;

    private String topicTitle;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "uploaded_by_teacher_code", nullable = false)
    private String uploadedByTeacherCode;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
