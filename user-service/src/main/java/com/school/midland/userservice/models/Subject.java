package com.school.midland.userservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subjects")
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
    private String code;

    private String curriculumType;

    private String gradeLevel;

    @Column(nullable = false)
    private String teacherCode;

    private LocalDateTime createdAt;

}

