package com.school.midland.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Concern {
    @Id
    private String id;
    private String description;
    private LocalDateTime submittedAt;
    private String status;  // e.g., "OPEN", "RESOLVED"

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;  // Concern raised by/for a student
}
