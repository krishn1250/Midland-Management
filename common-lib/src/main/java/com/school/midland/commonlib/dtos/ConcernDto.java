package com.school.midland.commonlib.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcernDto {
    private String raisedByRole;
    private String raisedByIdentifier;
    private String admissionNumber;
    private String concernType;
    private String description;
    private String status;
    private String assignedToTeacherCode;
    private String response;
}