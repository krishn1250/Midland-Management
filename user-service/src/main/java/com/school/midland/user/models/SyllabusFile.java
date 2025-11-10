package com.school.midland.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "syllabus_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SyllabusFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "syllabus_id", nullable = false)
    private Syllabus syllabus;

    private String fileUrl;
    private String fileName;
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
