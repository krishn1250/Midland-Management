package com.school.midland.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Events{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventsId;

    @Column(nullable = false)
    private String title;

    private String description;
    private String eventType;
    private String targetAudience;

    @Column(nullable = false)
    private LocalDate eventDate;

    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    @Column(nullable = false)
    private String createdByTeacherCode;

    private LocalDateTime createdAt = LocalDateTime.now();
}