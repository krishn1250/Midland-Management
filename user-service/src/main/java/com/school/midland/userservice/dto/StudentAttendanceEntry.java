package com.school.midland.userservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public  class StudentAttendanceEntry {
    private UUID studentUid;
    private String admissionNumber;
//    private String rollNo;
//    private String fullName;
    private String status;
    private String remarks;
}