package com.school.midland.commonlib.dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private String title;
    private String description;
    private String eventType;
    private String targetAudience;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String createdByTeacherCode;
}
