package com.school.midland.user.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerStudentResult {
    private String admissionNumber;
    private String status; // CREATED, UPDATED, SKIPPED, FAILED
    private String message; // optional
}
